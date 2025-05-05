package com.autoqa.qa2auto.mapper.impl;

import com.autoqa.qa2auto.dto.ProductDto;
import com.autoqa.qa2auto.entity.ProductEntity;
import com.autoqa.qa2auto.mapper.ProductMapper;

public class ProductMapperImpl implements ProductMapper {
    private static ProductMapperImpl instance;

    private ProductMapperImpl() {
    }

    public static ProductMapperImpl getInstance() {
        if (instance == null) {
            instance = new ProductMapperImpl();
        }
        return instance;
    }


    @Override
    public ProductDto toDto(ProductEntity entity) {
        return ProductDto
                .builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .build();
    }

    @Override
    public ProductEntity toEntity(ProductDto dto) {
        return ProductEntity
                .builder()
                .id(dto.getId())
                .code(dto.getCode())
                .name(dto.getName())
                .build();
    }
}
