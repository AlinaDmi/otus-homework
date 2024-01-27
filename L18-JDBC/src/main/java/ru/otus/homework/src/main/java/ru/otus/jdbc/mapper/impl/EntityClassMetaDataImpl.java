package ru.otus.homework.src.main.java.ru.otus.jdbc.mapper.impl;

import com.google.common.reflect.TypeToken;
import ru.otus.homework.src.main.java.ru.otus.annotation.Id;
import ru.otus.homework.src.main.java.ru.otus.jdbc.mapper.EntityClassMetaData;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private final Class<T> entityClass;

//    @SuppressWarnings("unchecked")
//    public EntityClassMetaDataImpl() {
//        TypeToken<?> typeToken = new TypeToken<EntityClassMetaDataImpl<T>>(getClass()) {
//        };
//        Type type = typeToken.resolveType(EntityClassMetaDataImpl.class.getTypeParameters()[0]).getType();
//        if (type instanceof Class) {
//            this.entityClass = (Class<T>) type;
//        } else {
//            throw new IllegalArgumentException("Unable to determine the entity class");
//        }
//    }


    public EntityClassMetaDataImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public String getName() {
        return entityClass.getSimpleName();
    }

    @Override
    public Constructor<T> getConstructor() {
        try {
            return entityClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Constructor not found for " + entityClass.getSimpleName(), e);
        }
    }

    @Override
    public Field getIdField() {
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        return null;
    }

    @Override
    public List<Field> getAllFields() {
        List<Field> allFields = new ArrayList<>();
        for (Field field : entityClass.getDeclaredFields()) {
            allFields.add(field);
        }
        return allFields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        List<Field> fieldsWithoutId = new ArrayList<>();
        for (Field field : entityClass.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Id.class)) {
                fieldsWithoutId.add(field);
            }
        }
        return fieldsWithoutId;
    }
}