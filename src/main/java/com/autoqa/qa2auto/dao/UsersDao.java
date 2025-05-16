package com.autoqa.qa2auto.dao;

import com.autoqa.qa2auto.entity.UsersEntity;
import java.util.List;
import java.util.Optional;

public interface UsersDao extends BaseDao<Integer, UsersEntity> {
    Optional<UsersEntity> findById(Integer id);
    List<UsersEntity> findAll();
    UsersEntity save(UsersEntity user);
    boolean deleteById(Integer id);
}