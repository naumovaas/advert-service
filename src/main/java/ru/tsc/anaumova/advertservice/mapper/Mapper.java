package ru.tsc.anaumova.advertservice.mapper;

import org.modelmapper.ModelMapper;

import java.util.Objects;

public class Mapper<E, D> {

    private final ModelMapper mapper;

    private final Class<D> dtoType;

    private final Class<E> entityType;

    public Mapper(final Class<D> dtoType, final Class<E> entityType) {
        this.dtoType = dtoType;
        this.entityType = entityType;
        this.mapper = new ModelMapper();
    }

    public E toEntity(D dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, entityType);
    }

    public D toDto(E entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, dtoType);
    }

}