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

