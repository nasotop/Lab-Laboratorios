package com.lab_laboratorios.lab_laboratorios.presentation.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientModel {
    private Long id;
    private Long userId;
    private String fullName;
    private LocalDate birthDate;
    private String sex;
    private String phone;
    private String email;
}
