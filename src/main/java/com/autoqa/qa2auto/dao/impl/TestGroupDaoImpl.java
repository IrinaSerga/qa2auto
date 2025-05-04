package com.autoqa.qa2auto.dao.impl;

import com.autoqa.qa2auto.dao.TestGroupDao;
import com.autoqa.qa2auto.entity.TestGroupEntity;

import java.util.List;
import java.util.Optional;

public class TestGroupDaoImpl implements TestGroupDao {
    @Override
    public Optional<TestGroupEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<TestGroupEntity> findAll() {
        return List.of();
    }

    @Override
    public TestGroupEntity save(TestGroupEntity entity) {
        return null;
    }

    @Override
    public TestGroupEntity update(TestGroupEntity entity) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
