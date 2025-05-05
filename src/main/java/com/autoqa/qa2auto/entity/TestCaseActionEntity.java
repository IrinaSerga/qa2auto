package com.autoqa.qa2auto.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class TestCaseActionEntity {
    private int id;
    private TestCaseEntity testCaseId;
    private TestCaseActionEntity actionId;
    private int stepOrder;
}
