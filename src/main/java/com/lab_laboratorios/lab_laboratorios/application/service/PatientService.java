package com.lab_laboratorios.lab_laboratorios.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab_laboratorios.lab_laboratorios.application.interfaces.IPatientService;
import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Patient;
import com.lab_laboratorios.lab_laboratorios.infraestructure.repository.PatientRepository;

@Service
public class PatientService implements IPatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public ResultDto<Patient> getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(ResultDto::ok)
                .orElseGet(() -> ResultDto.fail("Patient not found with id: " + id));
    }

    @Override
    public ResultDto<Patient> createPatient(Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
        return ResultDto.ok(savedPatient);
    }

    @Override
    public ResultDto<Patient> updatePatient(Long id, Patient patient) {
        return patientRepository.findById(id)
                .map(existingPatient -> {
                    existingPatient.setFullName(patient.getFullName());
                    existingPatient.setNationalId(patient.getNationalId());
                    existingPatient.setUserId(id);
                    existingPatient.setSex(patient.getSex());
                    existingPatient.setPhone(patient.getPhone());
                    existingPatient.setEmail(patient.getEmail());
                    existingPatient.setBirthDate(patient.getBirthDate());
                    Patient updatedPatient = patientRepository.save(existingPatient);
                    return ResultDto.ok(updatedPatient);
                })
                .orElseGet(() -> ResultDto.fail("Patient not found with id: " + id));
    }

    @Override
    public ResultDto<Void> deletePatient(Long id) {
        return patientRepository.findById(id)
                .map(existingPatient -> {
                    patientRepository.delete(existingPatient);
                    return ResultDto.<Void>ok(null);
                })
                .orElseGet(() -> ResultDto.fail("Patient not found with id: " + id));
    }

    @Override
    public ResultDto<List<Patient>> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return ResultDto.ok(patients);
    }

}
