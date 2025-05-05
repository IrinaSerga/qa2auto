package com.autoqa.qa2auto.dao.impl;

import com.autoqa.qa2auto.dao.ActionDirectoryDao;
import com.autoqa.qa2auto.entity.ActionDirectoryEntity;
import com.autoqa.qa2auto.exception.ActionDirectoryDaoException;
import com.autoqa.qa2auto.exception.TestCasePriorityDaoException;
import com.autoqa.qa2auto.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActionDirectoryDaoImpl implements ActionDirectoryDao {

    private static ActionDirectoryDaoImpl instance;
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";

    private static final String FIND_ALL_SQL = """
            SELECT id,
                   name,
                   description
            FROM action_directory
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    public ActionDirectoryDaoImpl() {
    }

    public static synchronized ActionDirectoryDaoImpl getInstance() {
        if (instance == null) {
            instance = new ActionDirectoryDaoImpl();
        }
        return instance;
    }



    @Override
    public Optional<ActionDirectoryEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            ActionDirectoryEntity actionDirectory = null;
            if (resultSet.next()) {
                actionDirectory = buildActionDirectory(resultSet);

            }
            return Optional.ofNullable(actionDirectory);
        } catch (SQLException e) {
            throw new ActionDirectoryDaoException("Can`t find actionDirectory by id ", e);
        }
    }

    @Override
    public List<ActionDirectoryEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery(); //
            List<ActionDirectoryEntity> actionDirectories = new ArrayList<>();
            while (resultSet.next()) {
                actionDirectories.add(buildActionDirectory(resultSet));
            }
            return actionDirectories;
        } catch (SQLException e) {
            throw new TestCasePriorityDaoException("Can`t find all actionDirectories ", e);
        }
    }

    @Override
    public ActionDirectoryEntity save(ActionDirectoryEntity entity) {
        return null;
    }

    @Override
    public ActionDirectoryEntity update(ActionDirectoryEntity entity) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }


    private static ActionDirectoryEntity buildActionDirectory(ResultSet resultSet) {
        try {
            return ActionDirectoryEntity
                    .builder()
                    .id(resultSet.getInt(ID))
                    .name(resultSet.getString(NAME))
                    .description(resultSet.getString(DESCRIPTION))
                    .build();
        } catch (SQLException e) {
            throw new ActionDirectoryDaoException("Can`t build actionDirectoryEntity ", e);
        }
    }
}
