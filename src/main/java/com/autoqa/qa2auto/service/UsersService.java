package com.autoqa.qa2auto.service;

import com.autoqa.qa2auto.dto.UsersDto;
import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<UsersDto> findAll();
    UsersDto save(UsersDto user);

    Optional<UsersDto> findByUsernameAndEmail(String username, String password);
    boolean checkPassword(UsersDto username, String password);
}
