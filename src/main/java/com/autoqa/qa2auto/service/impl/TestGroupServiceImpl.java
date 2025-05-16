package com.autoqa.qa2auto.service.impl;

import com.autoqa.qa2auto.dao.TestGroupDao;
import com.autoqa.qa2auto.dao.impl.TestGroupDaoImpl;
import com.autoqa.qa2auto.dto.TestGroupDto;
import com.autoqa.qa2auto.exception.serviceException.TestGroupServiceException;
import com.autoqa.qa2auto.mapper.TestGroupMapper;
import com.autoqa.qa2auto.mapper.impl.TestGroupMapperImpl;
import com.autoqa.qa2auto.service.TestGroupService;

import java.util.List;

public class TestGroupServiceImpl implements TestGroupService {
    private static TestGroupServiceImpl instance;

    private final TestGroupDao testGroupDao = TestGroupDaoImpl.getInstance();
    private final TestGroupMapper testGroupMapper = TestGroupMapperImpl.getInstance();

    private TestGroupServiceImpl() {
    }

    public static synchronized TestGroupServiceImpl getInstance() {
        if (instance == null) {
            instance = new TestGroupServiceImpl();
        }
        return instance;
    }

    @Override
    public List<TestGroupDto> findAll() {
        try {
            return testGroupDao.findAll().stream()
                    .map(testGroupMapper::toDto)
                    .toList();
        } catch (Exception e) {
            throw new TestGroupServiceException("Failed to retrieve test group list", e);
        }
    }


}
