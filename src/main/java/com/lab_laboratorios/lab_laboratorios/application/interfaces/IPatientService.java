package com.lab_laboratorios.lab_laboratorios.application.interfaces;

import java.util.List;

import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Patient;

public interface IPatientService {
    ResultDto<Patient> getPatientById(Long id);
    ResultDto<Patient> createPatient(Patient patient);
    ResultDto<Patient> updatePatient(Long id, Patient patient);
    ResultDto<Void> deletePatient(Long id);
    ResultDto<List<Patient>> getAllPatients();
}