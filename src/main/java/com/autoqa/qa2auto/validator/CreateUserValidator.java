package com.autoqa.qa2auto.validator;

import com.autoqa.qa2auto.dao.impl.UsersDaoImpl;
import com.autoqa.qa2auto.dto.UsersDto;
import com.autoqa.qa2auto.entity.Role;

public class CreateUserValidator implements Validator<UsersDto> {


    private static final CreateUserValidator INSTANCE = new CreateUserValidator();
    private final UsersDaoImpl usersDao = UsersDaoImpl.getInstance();

    @Override
    public ValidationResult isValid(UsersDto usersDto) {
        var validationResult = new ValidationResult();
        // Наличие роли
        if (Role.find(usersDto.getRole()).isEmpty()) {
           validationResult.add(Error.of("invalid.role", "Role does not exist"));
        }

        // Проверка дубликата
        boolean exists = usersDao.findAll().stream()
                .anyMatch(user -> user.getUsername().equals(usersDto.getUsername())
                        && user.getEmail().equals(usersDto.getEmail()));
        if (exists) {
            validationResult.add(Error.of("duplicate.user", "User with such username and email already exists"));
        }
        return validationResult;
    }

    /*
    *  @Override
    public ValidationResult isValid(UsersDto userDto) {
        var validationResult = new ValidationResult();

        // Проверка роли
        if (Role.find(userDto.getRole()).isEmpty()) {
            validationResult.add(Error.of("invalid.role", "Role does not exist"));
        }

        // Проверка дубликата
        boolean exists = usersDao.findAll().stream()
                .anyMatch(user -> user.getUsername().equals(userDto.getUsername())
                        && user.getEmail().equals(userDto.getEmail()));
        if (exists) {
            validationResult.add(Error.of("duplicate.user", "User with such username and email already exists"));
        }


        return validationResult;
    }*/



    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
