package com.autoqa.qa2auto.service.impl;

import com.autoqa.qa2auto.dao.SubsystemDao;
import com.autoqa.qa2auto.dao.impl.SubsystemDaoImpl;
import com.autoqa.qa2auto.dto.SubsystemDto;
import com.autoqa.qa2auto.mapper.SubsystemMapper;
import com.autoqa.qa2auto.mapper.impl.SubsystemMapperImpl;
import com.autoqa.qa2auto.service.SubsystemService;

import java.util.List;

public class SubsystemServiceImpl implements SubsystemService {
    private static SubsystemServiceImpl instance;

    private final SubsystemDao subsystemDao = SubsystemDaoImpl.getInstance();
    private final SubsystemMapper subsystemMapper = SubsystemMapperImpl.getInstance();

    private SubsystemServiceImpl() {
    }

    public static synchronized SubsystemServiceImpl getInstance() {
        if (instance == null) {
            instance = new SubsystemServiceImpl();
        }
        return instance;
    }

    @Override
    public SubsystemDto findById(Integer id) {
        return subsystemDao.findById(id)
                .map(subsystemMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Subsystem not found by Id: " + id));
    }

    @Override
    public List<SubsystemDto> findAll() {
        return subsystemDao.findAll().stream()
                .map(subsystemMapper::toDto)
                .toList();
    }

    @Override
    public SubsystemDto save(SubsystemDto dto) {
        var entity = subsystemMapper.toEntity(dto);
        var saved = subsystemDao.save(entity);
        return subsystemMapper.toDto(saved);
    }

    @Override
    public SubsystemDto update(SubsystemDto dto) {
        var entity = subsystemMapper.toEntity(dto);
        var updated = subsystemDao.update(entity);
        return subsystemMapper.toDto(updated);
    }

    @Override
    public boolean deleteById(Integer id) {
        return subsystemDao.deleteById(id);
    }
}
