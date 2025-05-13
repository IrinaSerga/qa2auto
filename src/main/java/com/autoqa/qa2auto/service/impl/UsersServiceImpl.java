package com.autoqa.qa2auto.service.impl;

import com.autoqa.qa2auto.dao.UsersDao;
import com.autoqa.qa2auto.dao.impl.UsersDaoImpl;
import com.autoqa.qa2auto.dto.UsersDto;
import com.autoqa.qa2auto.exception.serviceException.UsersServiceException;
import com.autoqa.qa2auto.mapper.UsersMapper;
import com.autoqa.qa2auto.mapper.impl.UsersMapperImpl;
import com.autoqa.qa2auto.service.UsersService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsersServiceImpl implements UsersService {
    private static final UsersServiceImpl INSTANCE = new UsersServiceImpl();

    private final UsersDao usersDao = UsersDaoImpl.getInstance();
    private final UsersMapper userMapper = UsersMapperImpl.getInstance();

    private UsersServiceImpl() {
    }

    public static UsersServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<UsersDto> findAll() {
        try {
            return usersDao.findAll().stream()
                    .map(userMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new UsersServiceException("Failed to find all users", e);
        }
    }

    @Override
    public UsersDto save(UsersDto dto) {
        try {
            var entity = userMapper.toEntity(dto);
            var saved = usersDao.save(entity);
            return userMapper.toDto(saved);
        } catch (Exception e) {
            throw new UsersServiceException("Failed to save user", e);
        }
    }


    public Optional<UsersDto> findByUsernameAndEmail(String username, String email) {
        try {
            return usersDao.findAll().stream()
                    .filter(u -> u.getUsername().equals(username) && u.getEmail().equals(email))
                    .map(userMapper::toDto)
                    .findFirst();
        } catch (Exception e) {
            throw new UsersServiceException("Failed to find user by username and email", e);
        }
    }

    public boolean checkPassword(UsersDto dto, String rawPassword) {
        if (dto == null || rawPassword == null) return false;
        return rawPassword.equals(dto.getPassword());
    }
}