package com.autoqa.qa2auto.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductDto {
    Long id;
    String code;
    String name;
    List<SubsystemDto> subsystems;
}
