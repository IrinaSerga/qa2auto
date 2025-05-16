package com.autoqa.qa2auto.dao.impl;

import com.autoqa.qa2auto.dao.UsersDao;
import com.autoqa.qa2auto.entity.TestGroupEntity;
import com.autoqa.qa2auto.entity.UsersEntity;
import com.autoqa.qa2auto.exception.daoException.UsersDaoException;
import com.autoqa.qa2auto.util.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UsersDaoImpl implements UsersDao {

    private static UsersDaoImpl instance;

    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String TEST_GROUP_ID = "tg_id";
    private static final String TEST_GROUP_NAME = "tg_name";

    private static final String FIND_ALL_SQL = """
        SELECT u.id, u.username, u.email, u.password, tg.id AS tg_id, tg.name AS tg_name
        FROM users u
        JOIN test_group tg ON u.test_group_id = tg.id
        """;

    private static final String INSERT_SQL = """
        INSERT INTO users (username, email, password, test_group_id)
        VALUES (?, ?, ?, ?)
        """;

    private UsersDaoImpl() {}

    public static synchronized UsersDaoImpl getInstance() {
        if (instance == null) {
            instance = new UsersDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<UsersEntity> findById(Integer id) {
        return findAll().stream().filter(u -> u.getId().equals(id)).findFirst();
    }


    @Override
    public List<UsersEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var ps = connection.prepareStatement(FIND_ALL_SQL)) {
            var rs = ps.executeQuery();
            List<UsersEntity> users = new ArrayList<>();
            while (rs.next()) {
                users.add(UsersEntity.builder()
                        .id(rs.getInt(ID))
                        .username(rs.getString(USERNAME))
                        .email(rs.getString(EMAIL))
                        .password(rs.getString(PASSWORD))
                        .testGroupId(TestGroupEntity.builder()
                                .id(rs.getInt(TEST_GROUP_ID))
                                .name(rs.getString(TEST_GROUP_NAME))
                                .build())
                        .build());
            }
            return users;
        } catch (SQLException e) {
            throw new UsersDaoException("Failed to load users", e);
        }
    }

    @Override
    public UsersEntity save(UsersEntity user) {
        try (var connection = ConnectionManager.get();
             var ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setLong(4, user.getTestGroupId().getId());
            ps.executeUpdate();
            var rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(ID));
            }
            return user;
        } catch (SQLException e) {
            throw new UsersDaoException("Failed to save user", e);
        }
    }

    @Override
    public UsersEntity update(UsersEntity entity) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

}
