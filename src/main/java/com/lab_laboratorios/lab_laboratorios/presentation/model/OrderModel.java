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
public class OrderModel {
    private Long id;
    private PatientModel patient;
    private LocalDateTime orderedAt;
    private String status;
    private String notes;
}
