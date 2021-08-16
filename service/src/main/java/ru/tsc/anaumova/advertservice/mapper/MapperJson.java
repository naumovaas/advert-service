package ru.tsc.anaumova.advertservice.mapper;

import com.google.gson.Gson;

public class MapperJson<E> {

    private final Class<E> entityType;

    public MapperJson(Class<E> entityType) {
        this.entityType = entityType;
    }

    public String toJson(E entity) {
        return null;
    }

    public E toEntity(String jsonString) {
        final Gson gson = new Gson();
        return gson.fromJson(jsonString, entityType);
    }

}