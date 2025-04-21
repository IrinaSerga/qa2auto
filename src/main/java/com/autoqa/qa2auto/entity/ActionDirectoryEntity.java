package com.autoqa.qa2auto.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ActionDirectoryEntity {

    private int id;
    private String name;
    private String description;
}
