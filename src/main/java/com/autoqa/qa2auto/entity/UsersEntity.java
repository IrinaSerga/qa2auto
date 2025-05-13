package com.autoqa.qa2auto.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersEntity {
    Integer id;
    String username;
    String email;
    String password;
    TestGroupEntity testGroupId;
}