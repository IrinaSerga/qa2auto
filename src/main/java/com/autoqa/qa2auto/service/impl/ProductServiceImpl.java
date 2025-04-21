package com.autoqa.qa2auto.service.impl;

import com.autoqa.qa2auto.dao.ProductDao;
import com.autoqa.qa2auto.dao.impl.ProductDaoImpl;
import com.autoqa.qa2auto.dto.ProductDto;
import com.autoqa.qa2auto.mapper.ProductMapper;
import com.autoqa.qa2auto.mapper.impl.ProductMapperImpl;
import com.autoqa.qa2auto.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static ProductServiceImpl instance;

    private final ProductDao productDao = ProductDaoImpl.getInstance();
    private final ProductMapper productMapper = ProductMapperImpl.getInstance();

    private ProductServiceImpl() {
    }

    @Override
    public ProductDto findById(Integer id) {
        return productDao.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Product not found by Id"));
    }

    @Override
    public List<ProductDto> findAll() {
        return productDao.findAll().stream()
                .map(productMapper::toDto)
                .toList();
    }


    public static synchronized ProductServiceImpl getInstance() {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }
}

/*{
    private static ProductServiceImpl instance;
    private final ProductDao productDao;

    private ProductServiceImpl() {
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

    public static synchronized ProductService getInstance() {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }

}*/
