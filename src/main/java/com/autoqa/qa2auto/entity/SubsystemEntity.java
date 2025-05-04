package com.autoqa.qa2auto.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class SubsystemEntity {

    private Integer id;
    private ProductEntity productId;
    private String name;
    private TestGroupEntity testGroupId;
}
