package ru.otus.homework.src.main.java.ru.otus.jdbc.mapper;

import ru.otus.demo.src.main.java.ru.otus.core.repository.DataTemplate;
import ru.otus.demo.src.main.java.ru.otus.core.repository.DataTemplateException;
import ru.otus.demo.src.main.java.ru.otus.core.repository.executor.DbExecutor;
import ru.otus.homework.src.main.java.ru.otus.annotation.Id;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
@SuppressWarnings("java:S1068")
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData<T> entitySQLMetaData;
    private final Class<T> entityClass;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData<T> entitySQLMetaData, Class<T> entityClass) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClass = entityClass;
    }


    @Override
    public Optional<T> findById(Connection connection, long id) {
        String sql = entitySQLMetaData.getSelectByIdSql();
        return dbExecutor.executeSelect(connection, sql, List.of(id), rs -> {
            try {
                if (rs.next()) {
                    return mapResultSetToEntity(rs);
                }
                return null;
            } catch (SQLException e) {
                throw new DataTemplateException(e);
            }
        });
    }

    @Override
    public List<T> findAll(Connection connection) {
        String sql = entitySQLMetaData.getSelectAllSql();

        return dbExecutor.executeSelect(connection, sql, Collections.emptyList(), resultSet -> {
            List<T> resultList = new ArrayList<>();
            try {
                while (resultSet.next()) {
                    T entity = mapResultSetToEntity(resultSet);
                    if (entity != null) {
                        resultList.add(entity);
                    }
                }
                return resultList;
            } catch (SQLException e) {
                throw new DataTemplateException(e);
            }
        })
                .orElseThrow(() -> new RuntimeException("Unexpected error"));
    }

    @Override
    public long insert(Connection connection, T entity) {
        String sql = entitySQLMetaData.getInsertSql();

        try {
            return dbExecutor.executeStatement(
                    connection, sql, getValues(entity));
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    @Override
    public void update(Connection connection, T entity) {
        String sql = entitySQLMetaData.getUpdateSql();

        try {
            dbExecutor.executeStatement(
                    connection, sql, getValues(entity));
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    // Метод для преобразования ResultSet в объект типа T
    public T mapResultSetToEntity(ResultSet resultSet) {
        try {
            T entity = entityClass.getConstructor().newInstance();

            for (Field field : entityClass.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = resultSet.getObject(field.getName());
                field.set(entity, value);
            }
            return entity;
        } catch (SQLException | ReflectiveOperationException e) {
            throw new RuntimeException("Error mapping ResultSet to entity", e);
        }
    }

    private List<Object> getValues(T entity) {
        List<Object> fieldValues = new ArrayList<>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(Id.class)) {
                field.setAccessible(true);
                try {
                    fieldValues.add(field.get(entity));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error accessing field value: " + field.getName(), e);
                }
            }
        }

        return fieldValues;
    }

}
