package com.autoqa.qa2auto.service;

import com.autoqa.qa2auto.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface BaseService <K, E> {
    Optional<E> findById(K id);
    List<E> findAll();
    ProductEntity save(E product);
    ProductEntity update(E product);
    boolean deleteById(K id);
}
