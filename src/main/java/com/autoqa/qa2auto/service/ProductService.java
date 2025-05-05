package com.autoqa.qa2auto.service;

import com.autoqa.qa2auto.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto findById(Integer id);
    List<ProductDto> findAll();
}
