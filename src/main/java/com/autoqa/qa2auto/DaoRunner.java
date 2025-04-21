package com.autoqa.qa2auto;

import com.autoqa.qa2auto.dao.impl.ProductDaoImpl;
import com.autoqa.qa2auto.entity.ProductEntity;

public class DaoRunner {

    public static void main(String[] args) {
        saveTest();
        deleteTest();
        updateTest();
        findAllTest();
    }

    private static void findAllTest() {
        var productDao = ProductDaoImpl.getInstance();
        var productEntityList = productDao.findAll();
        System.out.println(productEntityList);
    }

    private static void updateTest() {
        var productDao = ProductDaoImpl.getInstance();
        var maybeProduct = productDao.findById(1);
        System.out.println(maybeProduct);


        maybeProduct.ifPresent(product -> {
            product.setCode("USS");
            productDao.update(product);
        });
    }

    private static void deleteTest() {
        var productDao = ProductDaoImpl.getInstance();
        var deleteResult = productDao.deleteById(5);
        System.out.println(deleteResult);
    }

    private static void saveTest() {
        var productDao = ProductDaoImpl.getInstance();
        var productEntity = new ProductEntity();
        productEntity.setCode("AAA");
        productEntity.setName("Auto Auto Auto");

        var savedTicket = productDao.save(productEntity);
        System.out.println(savedTicket);
    }


}
