package com.autoqa.qa2auto.service;

import com.autoqa.qa2auto.dto.TestGroupDto;

import java.util.List;

public interface TestGroupService {
    List<TestGroupDto> findAll();
}
