package com.autoqa.qa2auto.mapper.impl;

import com.autoqa.qa2auto.dto.SubsystemDto;
import com.autoqa.qa2auto.entity.ProductEntity;
import com.autoqa.qa2auto.entity.SubsystemEntity;
import com.autoqa.qa2auto.entity.TestGroupEntity;
import com.autoqa.qa2auto.mapper.SubsystemMapper;

public class SubsystemMapperImpl implements SubsystemMapper {
    private static SubsystemMapperImpl instance;

    private SubsystemMapperImpl() {
    }

    public static SubsystemMapperImpl getInstance() {
        if (instance == null) {
            instance = new SubsystemMapperImpl();
        }
        return instance;
    }

    @Override
    public SubsystemDto toDto(SubsystemEntity entity) {
        return SubsystemDto
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .productId(entity.getProductId().getId())
                .testGroupId(entity.getTestGroupId().getId())
                .build();
    }

    @Override
    public SubsystemEntity toEntity(SubsystemDto dto) {
        return SubsystemEntity
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .productId(ProductEntity.builder().id(dto.getProductId()).build())
                .testGroupId(TestGroupEntity.builder().id(dto.getTestGroupId()).build())
                .build();
    }
}


