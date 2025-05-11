package com.autoqa.qa2auto.dto;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TestGroupDto {
    Integer id;
    String name;
}
