package com.autoqa.qa2auto.service;

import com.autoqa.qa2auto.dto.SubsystemDto;

import java.util.List;

public interface SubsystemService {

    SubsystemDto findById(Integer id);
    List<SubsystemDto> findAll();
    SubsystemDto save(SubsystemDto dto);
    SubsystemDto update(SubsystemDto dto);
    boolean deleteById(Integer id);
}
