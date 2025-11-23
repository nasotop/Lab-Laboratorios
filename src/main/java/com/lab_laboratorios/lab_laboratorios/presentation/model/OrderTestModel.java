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
    private Long orderId;
    private Long testTypeId;
    private Long laboratoryId;
    private String priority;
    private String status;
    private LocalDateTime scheduledStart;
    private LocalDateTime scheduledEnd;
}
