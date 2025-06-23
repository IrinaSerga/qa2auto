package com.autoqa.qa2auto.service.impl;

import com.autoqa.qa2auto.dao.ProductDao;
import com.autoqa.qa2auto.dao.SubsystemDao;
import com.autoqa.qa2auto.dao.impl.ProductDaoImpl;
import com.autoqa.qa2auto.dao.impl.SubsystemDaoImpl;
import com.autoqa.qa2auto.dto.ProductDto;
import com.autoqa.qa2auto.dto.SubsystemDto;
import com.autoqa.qa2auto.entity.SubsystemEntity;
import com.autoqa.qa2auto.exception.serviceException.ProductServiceException;
import com.autoqa.qa2auto.mapper.ProductMapper;
import com.autoqa.qa2auto.mapper.SubsystemMapper;
import com.autoqa.qa2auto.mapper.impl.ProductMapperImpl;
import com.autoqa.qa2auto.mapper.impl.SubsystemMapperImpl;
import com.autoqa.qa2auto.service.ProductService;
import com.autoqa.qa2auto.service.SubsystemService;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private static ProductServiceImpl instance;

    private final ProductDao productDao = ProductDaoImpl.getInstance();
    private final SubsystemDao subsystemDao = SubsystemDaoImpl.getInstance();
    private final ProductMapper productMapper = ProductMapperImpl.getInstance();
    private final SubsystemMapper subsystemMapper = SubsystemMapperImpl.getInstance();
    private final SubsystemService subsystemService = SubsystemServiceImpl.getInstance();

    private ProductServiceImpl() {
    }

    public static synchronized ProductServiceImpl getInstance() {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }
    @Override
    public List<ProductDto> findAll() {
        try {
            return productDao.findAll().stream()
                    .map(productMapper::toDto)
                    .peek(product -> {
                        List<SubsystemDto> subsystems = subsystemService.findByProductId(Math.toIntExact(product.getId()));
                        product.setSubsystems(subsystems);
                    })
                    .toList();
        } catch (Exception e) {
            throw new ProductServiceException("Failed to retrieve product list", e);
        }
    }


  /*  @Override
    public List<ProductDto> findAll() {
        try {
            return productDao.findAll().stream()
                    .map(productMapper::toDto)
                    .toList();
        } catch (Exception e) {
            throw new ProductServiceException("Failed to retrieve product list", e);
        }
    }*/

    @Override
    public ProductDto findById(Integer id) {
        ProductDto product = productDao.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ProductServiceException("Product not found by Id: " + id));

        List<SubsystemEntity> subsystemEntities = subsystemDao.findAllByProductId(id);
        List<SubsystemDto> subsystems = subsystemEntities.stream()
                .map(subsystemMapper::toDto)
                .collect(Collectors.toList());

        product.setSubsystems(subsystems);

        return product;
    }



}

