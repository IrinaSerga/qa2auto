package com.autoqa.qa2auto.exception;

public class ProductDaoException  extends DaoException{
    public ProductDaoException(Throwable throwable) {
        super(throwable);
    }
    public ProductDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
