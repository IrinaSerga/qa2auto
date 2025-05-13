package com.autoqa.qa2auto.dao.impl;

import com.autoqa.qa2auto.dao.TestGroupDao;
import com.autoqa.qa2auto.entity.TestGroupEntity;
import com.autoqa.qa2auto.exception.daoException.TestGroupDaoException;
import com.autoqa.qa2auto.util.ConnectionManager;

import java.sql.*;
import java.util.*;

public class TestGroupDaoImpl implements TestGroupDao {


    private static TestGroupDaoImpl instance;

    private static final String FIND_ALL_SQL = """
            SELECT id, name FROM test_group
            """;
    private static final String FIND_BY_ID_SQL = """
            SELECT id, name FROM test_group WHERE id = ?
            """;

    public static synchronized TestGroupDaoImpl getInstance() {
        if (instance == null) {
            instance = new TestGroupDaoImpl();
        }
        return instance;
    }


    @Override
    public Optional<TestGroupEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var ps = connection.prepareStatement(FIND_BY_ID_SQL)) {

            ps.setInt(1, id);
            var rs = ps.executeQuery();
            TestGroupEntity entity = null;
            if (rs.next()) {
                entity = TestGroupEntity.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build();
            }
            return Optional.ofNullable(entity);
        } catch (SQLException e) {
            throw new TestGroupDaoException("Failed to find test group by id", e);
        }
    }

    @Override
    public List<TestGroupEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var ps = connection.prepareStatement(FIND_ALL_SQL)) {
            var rs = ps.executeQuery();
            List<TestGroupEntity> result = new ArrayList<>();
            while (rs.next()) {
                result.add(TestGroupEntity.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build());
            }
            return result;
        } catch (SQLException e) {
            throw new TestGroupDaoException("Failed to load all test groups", e);
        }
    }

    @Override
    public TestGroupEntity save(TestGroupEntity entity) {
        throw new UnsupportedOperationException("Save not implemented");
    }

    @Override
    public TestGroupEntity update(TestGroupEntity entity) {
        throw new UnsupportedOperationException("Update not implemented");
    }

    @Override
    public boolean deleteById(Integer id) {
        throw new UnsupportedOperationException("Delete not implemented");
    }
}
