package com.autoqa.qa2auto.entity;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ProductEntity {
    private Long id;
    private String code;
    private String name;
}

