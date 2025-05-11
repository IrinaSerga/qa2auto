package com.autoqa.qa2auto.dao.impl;

import com.autoqa.qa2auto.dao.ProductDao;
import com.autoqa.qa2auto.entity.ProductEntity;
import com.autoqa.qa2auto.exception.daoException.ProductDaoException;
import com.autoqa.qa2auto.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    private static ProductDaoImpl instance;

    private static final String ID = "id";
    private static final String CODE = "code";
    private static final String NAME = "name";
    private static final String FIND_ALL_SQL = """
            SELECT id,
                   code,
                   name
            FROM product
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO product (code, name)
            VALUES (?, ?)
            """;
    // изменение ОДНОЙ сущности
    // UPDATE на ВСЕ поля
    private static final String UPDATE_SQL = """
            UPDATE product
            SET code=?,
                name=?
            WHERE id = ?
            """;
    private static final String DELETE_SQL = """
            DELETE FROM product
            WHERE id = ?
            """;


    private ProductDaoImpl() {
        super();
    }

    public static synchronized ProductDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProductDaoImpl();
        }
        return instance;
    }

    @Override
    // Optional -  потому что может вернуть NULL
    public Optional<ProductEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery(); //вернет ResultSet
            ProductEntity product = null;
            if (resultSet.next()) {
                product = buildProduct(resultSet);

            }
            return Optional.ofNullable(product);
        } catch (SQLException e) {
            throw new ProductDaoException("Can`t find product by id ", e);
        }

    }

    @Override
    public List<ProductEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery(); //вернет ResultSet
            List<ProductEntity> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(buildProduct(resultSet));
            }
            return products;
        } catch (SQLException e) {
            throw new ProductDaoException("Can`t find all products ", e);
        }

    }

    private static ProductEntity buildProduct(ResultSet resultSet) {
        try {
            return ProductEntity
                    .builder()
                    .id(resultSet.getLong(ID))
                    .code(resultSet.getString(CODE))
                    .name(resultSet.getString(NAME))
                    .build();
        } catch (SQLException e) {
            throw new ProductDaoException("Can`t build product ", e);
        }
    }

    @Override
    public ProductEntity save(ProductEntity product) {
        try (var connection = ConnectionManager.get()) {
            var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getCode());
            preparedStatement.setString(2, product.getName());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong("id"));
            }
            return product;

        } catch (SQLException e) {
            throw new ProductDaoException("Can`t save product ", e);
        }
    }

    @Override
    public ProductEntity update(ProductEntity product) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, product.getCode());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setLong(3, product.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProductDaoException("Can`t update product ", e);
        }
        return product;
    }


    @Override
    public boolean deleteById(Integer id) {
        try (var connection = ConnectionManager.get()) {
            var preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new ProductDaoException("Can`t delete product by id ", e);
        }
    }


}
