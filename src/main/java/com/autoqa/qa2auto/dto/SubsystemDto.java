package com.autoqa.qa2auto.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SubsystemDto {
    Integer id;
    Long productId;
    String name;
    Integer testGroupId;
}
