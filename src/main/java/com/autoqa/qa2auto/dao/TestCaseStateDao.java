package com.autoqa.qa2auto.dao;

import com.autoqa.qa2auto.entity.TestCaseStateEntity;

import java.util.Optional;

public interface TestCaseStateDao extends  BaseDao<Integer, TestCaseStateEntity>{
    Optional<TestCaseStateEntity> findByProductId(Integer id);
}
