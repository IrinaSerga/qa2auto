package com.autoqa.qa2auto.dao;

import com.autoqa.qa2auto.entity.TestCasePriorityEntity;

import java.util.Optional;

public interface TestCasePriorityDao extends BaseDao<Integer, TestCasePriorityEntity> {
    Optional<TestCasePriorityEntity> findByProductId(Integer id);
}
