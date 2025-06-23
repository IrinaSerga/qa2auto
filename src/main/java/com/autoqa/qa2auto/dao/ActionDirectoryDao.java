package com.autoqa.qa2auto.dao;

import com.autoqa.qa2auto.entity.ActionDirectoryEntity;

import java.util.Optional;

public interface ActionDirectoryDao  extends BaseDao<Integer, ActionDirectoryEntity>{
    Optional<ActionDirectoryEntity> findByProductId(Integer id);
}
