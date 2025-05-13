package com.autoqa.qa2auto.exception.serviceException;

public class UsersServiceException extends RuntimeException{
  public UsersServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public UsersServiceException(String message) {
    super(message);
  }
}
