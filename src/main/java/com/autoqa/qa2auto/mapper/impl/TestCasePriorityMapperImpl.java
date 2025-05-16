package com.autoqa.qa2auto.mapper.impl;

import com.autoqa.qa2auto.dto.TestCasePriorityDto;
import com.autoqa.qa2auto.entity.TestCasePriorityEntity;
import com.autoqa.qa2auto.mapper.TestCasePriorityMapper;

public class TestCasePriorityMapperImpl implements TestCasePriorityMapper {

    private static TestCasePriorityMapperImpl instance;


    private TestCasePriorityMapperImpl() {
    }

    public static TestCasePriorityMapper getInstance() { if (instance == null) {
        instance = new TestCasePriorityMapperImpl();
    }
        return instance;
    }
    @Override
    public TestCasePriorityDto toDto(TestCasePriorityEntity entity) {
        return  TestCasePriorityDto
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public TestCasePriorityEntity toEntity(TestCasePriorityDto dto) {
        return TestCasePriorityEntity
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
