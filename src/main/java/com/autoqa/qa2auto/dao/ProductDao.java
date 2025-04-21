package com.autoqa.qa2auto.dao;

// без private чтобы  в дальнейшем использовать Proxy(Hibernate)


import com.autoqa.qa2auto.entity.ProductEntity;

public interface ProductDao extends BaseDao<Integer, ProductEntity> {

}
