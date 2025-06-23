package com.autoqa.qa2auto.dao.impl;

import com.autoqa.qa2auto.dao.UsersDao;
import com.autoqa.qa2auto.entity.Role;
import com.autoqa.qa2auto.entity.TestGroupEntity;
import com.autoqa.qa2auto.entity.UsersEntity;
import com.autoqa.qa2auto.exception.daoException.UsersDaoException;
import com.autoqa.qa2auto.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;


public class UsersDaoImpl implements UsersDao {

    private static UsersDaoImpl instance;

    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String TEST_GROUP_ID = "tg_id";
    private static final String TEST_GROUP_NAME = "tg_name";
    private static final String ROLE = "role";

    private static final String FIND_ALL_SQL = """
            SELECT u.id, u.username, u.email, u.password, tg.id AS tg_id, tg.name AS tg_name, u.role AS role
            FROM users u
            JOIN test_group tg ON u.test_group_id = tg.id
            """;

    private static final String INSERT_SQL = """
            INSERT INTO users (username, email, password, test_group_id, role)
            VALUES (?, ?, ?, ?, ?)
            """;

    private static final String FIND_BY_EMAIL = """
            SELECT u.id, u.username, u.email, u.password, u.test_group_id, u.role
            FROM users u
            WHERE u.email = ?
            """;

    private UsersDaoImpl() {
    }

    public static synchronized UsersDaoImpl getInstance() {
        if (instance == null) {
            instance = new UsersDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<UsersEntity> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }


    @Override
    public Optional<UsersEntity> findByProductId(Integer id) {
        return findAll().stream().filter(user -> user.getId().equals(id)).findFirst();
    }


    @Override
    public Optional<UsersEntity> findById(Integer id) {
        return Optional.empty();
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
                        .role(Role.valueOf(rs.getString("role")))
                        .build());
            }
            return users;
        } catch (SQLException e) {
            throw new UsersDaoException("Failed to load users", e);
        }
    }

    @Override
    @SneakyThrows // для обработки ошибок на уровне сервлета
    public UsersEntity save(UsersEntity user) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(INSERT_SQL, RETURN_GENERATED_KEYS)) {
            prepareStatement.setObject(1, user.getUsername());
            prepareStatement.setObject(2, user.getEmail());
            prepareStatement.setObject(3, user.getPassword());
            prepareStatement.setObject(4, user.getTestGroupId().getId());
            prepareStatement.setObject(5, user.getRole().name());
            prepareStatement.executeUpdate();

            var generatedKeys = prepareStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getObject(ID, Integer.class));

            return user;
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
