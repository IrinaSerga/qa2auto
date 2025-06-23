package com.autoqa.qa2auto.service;

import com.autoqa.qa2auto.dto.SubsystemDto;
import com.autoqa.qa2auto.entity.SubsystemEntity;

import java.util.List;

public interface SubsystemService {

    SubsystemDto findById(Integer id);
    List<SubsystemDto> findAll();
    SubsystemDto save(SubsystemDto dto);
    SubsystemDto update(SubsystemDto dto);
    boolean deleteById(Integer id);
    List<SubsystemDto> findByProductId(Integer productId);
    List<SubsystemEntity> findAllByProductId(Long productId);
}
