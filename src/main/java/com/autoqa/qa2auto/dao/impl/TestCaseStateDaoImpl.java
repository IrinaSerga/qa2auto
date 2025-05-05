package com.autoqa.qa2auto.dao.impl;

import com.autoqa.qa2auto.dao.TestCaseStateDao;
import com.autoqa.qa2auto.entity.TestCaseStateEntity;
import com.autoqa.qa2auto.exception.TestCaseStateDaoException;
import com.autoqa.qa2auto.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/*
 * как справочник, только FIND_BY_ID_SQL и FIND_ALL_SQL
 * */
public class TestCaseStateDaoImpl implements TestCaseStateDao {

    private static TestCaseStateDaoImpl instance;
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


    private TestCaseStateDaoImpl() {
        super();
    }

    public static synchronized TestCaseStateDaoImpl getInstance() {
        if (instance == null) {
            instance = new TestCaseStateDaoImpl();
        }
        return instance;
    }

    @Override
    public List<TestCaseStateEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            var resultSet = preparedStatement.executeQuery();

            List<TestCaseStateEntity> testCaseStates = new ArrayList<>();
            while (resultSet.next()) {
                testCaseStates.add(buildTestCaseStateEntity(resultSet));
            }
            return testCaseStates;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<TestCaseStateEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            TestCaseStateEntity testCaseState = null;
            if (resultSet.next()) {
                testCaseState = buildTestCaseStateEntity(resultSet);
            }
            return Optional.ofNullable(testCaseState);
        } catch (SQLException e) {
            throw new TestCaseStateDaoException("Can`t find testCaseState by id ", e);
        }

    }


    @Override
    public TestCaseStateEntity save(TestCaseStateEntity entity) {
        return null;
    }

    @Override
    public TestCaseStateEntity update(TestCaseStateEntity entity) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    private TestCaseStateEntity buildTestCaseStateEntity(ResultSet resultSet) {
        try {
            return TestCaseStateEntity
                    .builder()
                    .id(resultSet.getInt(ID))
                    .name(resultSet.getString(NAME))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
