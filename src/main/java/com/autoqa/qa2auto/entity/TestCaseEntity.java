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
    private SubsystemEntity subsystemId;
    private String name;
    private Short stateId;
    private TestCasePriorityEntity priorityId;
    private String description;

}
