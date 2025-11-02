package com.lab_laboratorios.lab_laboratorios.presentation.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderTestModel {
    private Long id;
    private OrderModel order;
    private TestTypeModel testType;
    private LaboratoryModel laboratory;
    private String priority;
    private String status;
    private LocalDateTime scheduledStart;
    private LocalDateTime scheduledEnd;
}
