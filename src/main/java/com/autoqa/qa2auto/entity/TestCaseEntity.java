package com.autoqa.qa2auto.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class TestCaseEntity {

    private Long id;
    private Integer subsystemId;
    private String name;
    private Short stateId;
    private PriorityEntity priorityId;
    private String description;

}
