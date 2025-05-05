package com.autoqa.qa2auto.dto;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductDto {
    Long id;
    String code;
    String name;
}
