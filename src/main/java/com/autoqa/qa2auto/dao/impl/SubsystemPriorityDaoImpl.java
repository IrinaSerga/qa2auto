package com.autoqa.qa2auto.dao.impl;

import com.autoqa.qa2auto.dao.SubsystemPriorityDao;
import com.autoqa.qa2auto.entity.SubsystemPriorityEntity;
import com.autoqa.qa2auto.exception.daoException.SubsystemPriorityDaoException;
import com.autoqa.qa2auto.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubsystemPriorityDaoImpl implements SubsystemPriorityDao {

    private static SubsystemPriorityDaoImpl instance;

    private static final String ID = "id";
    private static final String NAME = "name";

    private static final String FIND_ALL_SQL = """
            SELECT id, name
            FROM subsystem_priority
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO subsystem_priority (name)
            VALUES (?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE subsystem_priority
            SET name = ?
            WHERE id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM subsystem_priority
            WHERE id = ?
            """;

    private SubsystemPriorityDaoImpl() {}

    public static synchronized SubsystemPriorityDaoImpl getInstance() {
        if (instance == null) {
            instance = new SubsystemPriorityDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<SubsystemPriorityEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {

            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            SubsystemPriorityEntity entity = null;

            if (resultSet.next()) {
                entity = buildPriority(resultSet);
            }
            return Optional.ofNullable(entity);
        } catch (SQLException e) {
            throw new SubsystemPriorityDaoException("Can't find subsystem priority by id", e);
        }
    }

    @Override
    public List<SubsystemPriorityEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {

            var resultSet = preparedStatement.executeQuery();
            List<SubsystemPriorityEntity> priorities = new ArrayList<>();

            while (resultSet.next()) {
                priorities.add(buildPriority(resultSet));
            }

            return priorities;
        } catch (SQLException e) {
            throw new SubsystemPriorityDaoException("Can't find all subsystem priorities", e);
        }
    }

    @Override
    public SubsystemPriorityEntity save(SubsystemPriorityEntity priority) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, priority.getName());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                priority.setId(generatedKeys.getInt(ID));
            }

            return priority;
        } catch (SQLException e) {
            throw new SubsystemPriorityDaoException("Can't save subsystem priority", e);
        }
    }

    @Override
    public SubsystemPriorityEntity update(SubsystemPriorityEntity priority) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {

            preparedStatement.setString(1, priority.getName());
            preparedStatement.setInt(2, priority.getId());
            preparedStatement.executeUpdate();

            return priority;
        } catch (SQLException e) {
            throw new SubsystemPriorityDaoException("Can't update subsystem priority", e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new SubsystemPriorityDaoException("Can't delete subsystem priority by id", e);
        }
    }

    private static SubsystemPriorityEntity buildPriority(ResultSet resultSet) {
        try {
            return SubsystemPriorityEntity.builder()
                    .id(resultSet.getInt(ID))
                    .name(resultSet.getString(NAME))
                    .build();
        } catch (SQLException e) {
            throw new SubsystemPriorityDaoException("Can't build subsystem priority from result set", e);
        }
    }
}
