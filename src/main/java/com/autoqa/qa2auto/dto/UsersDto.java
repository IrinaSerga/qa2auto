package com.autoqa.qa2auto.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UsersDto {
    Integer id;
    String username;
    String email;
    String password;
    Integer testGroupId;


    String testGroupName;
}
