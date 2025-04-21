package com.autoqa.qa2auto.service.impl;

import com.autoqa.qa2auto.dao.ProductDao;
import com.autoqa.qa2auto.dao.impl.ProductDaoImpl;
import com.autoqa.qa2auto.entity.ProductEntity;
import com.autoqa.qa2auto.service.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl() {
        this.productDao = ProductDaoImpl.getInstance();
    }

    @Override
    public Optional<ProductEntity> findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid product ID");
        }
        return productDao.findById(id);
    }

    @Override
    public List<ProductEntity> findAll() {
        return productDao.findAll();
    }

    @Override
    public ProductEntity save(ProductEntity product) {
        if (product == null || product.getCode() == null || product.getName() == null) {
            throw new IllegalArgumentException("Product code and name must not be null");
        }
        return productDao.save(product);
    }

    @Override
    public ProductEntity update(ProductEntity product) {
        if (product == null || product.getId() == null) {
            throw new IllegalArgumentException("Product and ID must not be null");
        }
        return productDao.update(product);
    }

    @Override
    public boolean deleteById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID for deletion");
        }
        return productDao.deleteById(id);
    }
}
