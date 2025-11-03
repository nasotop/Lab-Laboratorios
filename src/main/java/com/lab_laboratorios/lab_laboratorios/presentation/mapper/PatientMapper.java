package com.lab_laboratorios.lab_laboratorios.presentation.mapper;

import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Patient;
import com.lab_laboratorios.lab_laboratorios.presentation.model.PatientModel;

public class PatientMapper {
    public static PatientModel toModel(Patient patient) {
        return PatientModel.builder()
                .id(patient.getId())
                .userId(patient.getUserId())
                .fullName(patient.getFullName())
                .birthDate(patient.getBirthDate())
                .sex(patient.getSex())
                .phone(patient.getPhone())
                .email(patient.getEmail())
                .build();
    }

    public static Patient toEntity(PatientModel patientModel) {
        return Patient.builder()
                .id(patientModel.getId())
                .userId(patientModel.getUserId())
                .fullName(patientModel.getFullName())
                .birthDate(patientModel.getBirthDate())
                .sex(patientModel.getSex())
                .phone(patientModel.getPhone())
                .email(patientModel.getEmail())
                .build();

    }

}
