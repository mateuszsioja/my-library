package com.msioja.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface GenericMapper<E, D> {

    D toDto(E entity);

    E toEntity(D dto);

    default List<D> toDtoList(List<E> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
