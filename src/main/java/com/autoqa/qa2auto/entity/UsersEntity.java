package com.autoqa.qa2auto.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersEntity {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private TestGroupEntity testGroupId;
}