package ru.otus.homework.src.main.java.ru.otus.jdbc.mapper.impl;

import ru.otus.homework.src.main.java.ru.otus.jdbc.mapper.EntityClassMetaData;
import ru.otus.homework.src.main.java.ru.otus.jdbc.mapper.EntitySQLMetaData;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData<T> {
    private final EntityClassMetaData<T> entityClassMetaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public String getSelectAllSql() {
        return "SELECT * FROM " + entityClassMetaData.getName();
    }

    @Override
    public String getSelectByIdSql() {
        Field idField = entityClassMetaData.getIdField();
        if (idField == null) {
            throw new IllegalStateException("Entity does not have an Id field");
        }

        return "SELECT * FROM " + entityClassMetaData.getName().toLowerCase() + " WHERE " +
                idField.getName().toLowerCase() + " = ?";
    }

    @Override
    public String getInsertSql() {
        List<Field> fields = entityClassMetaData.getFieldsWithoutId();
        String columnNames = fields.stream().map(Field::getName).collect(Collectors.joining(", "));
        String placeholders = fields.stream().map(f -> "?").collect(Collectors.joining(", "));

        return "INSERT INTO " + entityClassMetaData.getName().toLowerCase() +
                " (" + columnNames + ") VALUES (" + placeholders + ")";
    }

    @Override
    public String getUpdateSql() {
        Field idField = entityClassMetaData.getIdField();
        if (idField == null) {
            throw new IllegalStateException("Entity does not have an Id field");
        }

        List<Field> fieldsWithoutId = entityClassMetaData.getFieldsWithoutId();
        String setClause = fieldsWithoutId.stream()
                .map(f -> f.getName().toLowerCase() + " = ?")
                .collect(Collectors.joining(", "));

        return "UPDATE " + entityClassMetaData.getName().toLowerCase() +
                " SET " + setClause + " WHERE " + idField.getName().toLowerCase() + " = ?";
    }
}