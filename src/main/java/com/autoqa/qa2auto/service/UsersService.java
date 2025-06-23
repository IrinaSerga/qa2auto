package com.autoqa.qa2auto.service;

import com.autoqa.qa2auto.dto.UsersDto;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<UsersDto> findAll();
    Optional<UsersDto> findByEmail(String email);
    Integer save(UsersDto user);
    boolean checkPassword(UsersDto username, String password);
}
