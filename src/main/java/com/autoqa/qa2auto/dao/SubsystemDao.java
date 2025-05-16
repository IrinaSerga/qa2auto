package com.autoqa.qa2auto.dao;

import com.autoqa.qa2auto.entity.SubsystemEntity;

import java.util.List;

public interface SubsystemDao extends BaseDao<Integer, SubsystemEntity>{
    List<SubsystemEntity> findByProductId(Long productId);
}
