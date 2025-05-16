package com.autoqa.qa2auto.mapper.impl;

import com.autoqa.qa2auto.dto.TestGroupDto;
import com.autoqa.qa2auto.entity.TestGroupEntity;
import com.autoqa.qa2auto.mapper.TestGroupMapper;

public class TestGroupMapperImpl implements TestGroupMapper {
    private static TestGroupMapperImpl instance;

    private TestGroupMapperImpl() {
    }

    public static synchronized TestGroupMapperImpl getInstance() {
        if (instance == null) {
            instance = new TestGroupMapperImpl();
        }
        return instance;
    }

    @Override
    public TestGroupDto toDto(TestGroupEntity entity) {
        return TestGroupDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public TestGroupEntity toEntity(TestGroupDto dto) {
        return TestGroupEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
