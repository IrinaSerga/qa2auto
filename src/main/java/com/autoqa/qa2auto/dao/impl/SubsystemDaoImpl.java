package com.autoqa.qa2auto.dao.impl;

import com.autoqa.qa2auto.dao.SubsystemDao;
import com.autoqa.qa2auto.entity.ProductEntity;
import com.autoqa.qa2auto.entity.SubsystemEntity;
import com.autoqa.qa2auto.entity.TestGroupEntity;
import com.autoqa.qa2auto.exception.SubsystemDaoException;
import com.autoqa.qa2auto.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubsystemDaoImpl implements SubsystemDao {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRODUCT_ID = "product_id";
    private static final String TEST_GROUP_ID = "test_group_id";

    private static final String FIND_ALL_SQL = """
            SELECT id, product_id, name, test_group_id
            FROM subsystem
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO subsystem (product_id, name, test_group_id)
            VALUES (?, ?, ?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE subsystem
            SET product_id = ?, name = ?, test_group_id = ?
            WHERE id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM subsystem
            WHERE id = ?
            """;

    private static SubsystemDaoImpl instance;

    private SubsystemDaoImpl() {
        super();
    }

    public static synchronized SubsystemDaoImpl getInstance() {
        if (instance == null) {
            instance = new SubsystemDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<SubsystemEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {

            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            SubsystemEntity subsystem = null;
            if (resultSet.next()) {
                subsystem = buildSubsystem(resultSet);
            }
            return Optional.ofNullable(subsystem);

        } catch (SQLException e) {
            throw new SubsystemDaoException("Can't find subsystem by id", e);
        }
    }

    @Override
    public List<SubsystemEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {

            var resultSet = preparedStatement.executeQuery();
            List<SubsystemEntity> subsystems = new ArrayList<>();
            while (resultSet.next()) {
                subsystems.add(buildSubsystem(resultSet));
            }
            return subsystems;

        } catch (SQLException e) {
            throw new SubsystemDaoException("Can't find all subsystems", e);
        }
    }

    @Override
    public SubsystemEntity save(SubsystemEntity subsystem) {
        try (var connection = ConnectionManager.get()) {
            var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, subsystem.getProductId().getId());
            preparedStatement.setString(2, subsystem.getName());
            preparedStatement.setInt(3, subsystem.getTestGroupId().getId());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                subsystem.setId(generatedKeys.getInt(ID));
            }
            return subsystem;

        } catch (SQLException e) {
            throw new SubsystemDaoException("Can't save subsystem", e);
        }
    }

    @Override
    public SubsystemEntity update(SubsystemEntity subsystem) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {

            preparedStatement.setLong(1, subsystem.getProductId().getId());
            preparedStatement.setString(2, subsystem.getName());
            preparedStatement.setInt(3, subsystem.getTestGroupId().getId());
            preparedStatement.setInt(4, subsystem.getId());
            preparedStatement.executeUpdate();

            return subsystem;

        } catch (SQLException e) {
            throw new SubsystemDaoException("Can't update subsystem", e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new SubsystemDaoException("Can't delete subsystem by id", e);
        }
    }

    private static SubsystemEntity buildSubsystem(ResultSet resultSet) {
        try {
            return SubsystemEntity.builder()
                    .id(resultSet.getInt(ID))
                    .name(resultSet.getString(NAME))
                    .productId(
                            ProductEntity
                                    .builder()
                                    .id(resultSet.getLong(PRODUCT_ID)).build())
                    .testGroupId(
                            TestGroupEntity
                                    .builder()
                                    .id(resultSet.getInt(TEST_GROUP_ID)).build())
                    .build();
        } catch (SQLException e) {
            throw new SubsystemDaoException("Can't build subsystem", e);
        }
    }
}
