package com.autoqa.qa2auto.mapper.impl;

import com.autoqa.qa2auto.dto.UsersDto;
import com.autoqa.qa2auto.entity.TestGroupEntity;
import com.autoqa.qa2auto.entity.UsersEntity;
import com.autoqa.qa2auto.mapper.UsersMapper;

public class UsersMapperImpl implements UsersMapper {
    private static final UsersMapperImpl INSTANCE = new UsersMapperImpl();

    private UsersMapperImpl() {
    }

    public static UsersMapperImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public UsersDto toDto(UsersEntity entity) {
        return UsersDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .testGroupId(entity.getTestGroupId().getId())
                .testGroupName(entity.getTestGroupId().getName())
                .build();
    }

    @Override
    public UsersEntity toEntity(UsersDto dto) {
        return UsersEntity.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .testGroupId(TestGroupEntity.builder().id(dto.getTestGroupId()).name(dto.getTestGroupName()).build())
                .build();
    }
}
