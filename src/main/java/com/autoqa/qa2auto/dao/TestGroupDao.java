package com.autoqa.qa2auto.dao;

import com.autoqa.qa2auto.entity.TestGroupEntity;

import java.util.Optional;

public interface TestGroupDao extends BaseDao<Integer, TestGroupEntity>{
    Optional<TestGroupEntity> findByProductId(Integer id);
}
