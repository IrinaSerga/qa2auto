package com.autoqa.qa2auto.dao.impl;

import com.autoqa.qa2auto.dao.TestCasePriorityDao;
import com.autoqa.qa2auto.entity.TestCasePriorityEntity;
import com.autoqa.qa2auto.exception.TestCasePriorityDaoException;
import com.autoqa.qa2auto.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestCasePriorityDaoImpl implements TestCasePriorityDao {

private static TestCasePriorityDaoImpl instance;
    private static final String ID = "id";
    private static final String NAME = "name";

    private static final String FIND_ALL_SQL = """
            SELECT id,
                   name
            FROM test_case_priority
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO test_case_priority (name)
            VALUES ( ?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE test_case_priority
            SET name=?
            WHERE id = ?
            """;
    private static final String DELETE_SQL = """
            DELETE FROM test_case_priority
            WHERE id = ?
            """;
    private TestCasePriorityDaoImpl() {
        super();
    }

    public static synchronized TestCasePriorityDaoImpl getInstance() {
        if (instance == null) {
            instance = new TestCasePriorityDaoImpl();
        }
        return instance;
    }
    @Override
    public Optional<TestCasePriorityEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery(); //вернет ResultSet
            TestCasePriorityEntity testCasePriority = null;
            if (resultSet.next()) {
                testCasePriority = buildTestCasePriority(resultSet);

            }
            return Optional.ofNullable(testCasePriority);
        } catch (SQLException e) {
            throw new TestCasePriorityDaoException("Can`t find testCasePriority by id ", e);
        }
    }

    @Override
    public List<TestCasePriorityEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery(); //вернет ResultSet
            List<TestCasePriorityEntity> testCasePriorities = new ArrayList<>();
            while (resultSet.next()) {
                testCasePriorities.add(buildTestCasePriority(resultSet));
            }
            return testCasePriorities;
        } catch (SQLException e) {
            throw new TestCasePriorityDaoException("Can`t find all testCasePriority ", e);
        }
    }

    @Override
    public TestCasePriorityEntity save(TestCasePriorityEntity testCasePriority) {
        try (var connection = ConnectionManager.get()) {
            var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, testCasePriority.getName());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                testCasePriority.setId(generatedKeys.getInt("id"));
            }
            return testCasePriority;

        } catch (SQLException e) {
            throw new TestCasePriorityDaoException("Can`t save testCasePriority ", e);
        }
    }

    @Override
    public TestCasePriorityEntity update(TestCasePriorityEntity testCasePriority) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, testCasePriority.getName());
            preparedStatement.setInt(2, testCasePriority.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new TestCasePriorityDaoException("Can`t update testCasePriority ", e);
        }
        return testCasePriority;
    }

    @Override
    public boolean deleteById(Integer id) {
        try (var connection = ConnectionManager.get()) {
            var preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new TestCasePriorityDaoException("Can`t delete testCasePriority by id ", e);
        }
    }


    private static TestCasePriorityEntity buildTestCasePriority(ResultSet resultSet) {
        try {
            return TestCasePriorityEntity
                    .builder()
                    .id(resultSet.getInt(ID))
                    .name(resultSet.getString(NAME))
                    .build();
        } catch (SQLException e) {
            throw new TestCasePriorityDaoException("Can`t build testCasePriority ", e);
        }
    }



}
