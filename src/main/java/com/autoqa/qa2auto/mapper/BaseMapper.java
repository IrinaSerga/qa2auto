package com.autoqa.qa2auto.mapper;

public interface BaseMapper <E, D>{
    D toDto(E entity);
    E toEntity(D dto);
}
