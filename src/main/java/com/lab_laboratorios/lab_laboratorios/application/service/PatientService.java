package com.lab_laboratorios.lab_laboratorios.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab_laboratorios.lab_laboratorios.application.interfaces.IPatientService;
import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Patient;
import com.lab_laboratorios.lab_laboratorios.infraestructure.repository.PatientRepository;
import com.lab_laboratorios.lab_laboratorios.infraestructure.service.UserService;

@Service
public class PatientService implements IPatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserService userService;

    @Override
    public ResultDto<Patient> getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(ResultDto::ok)
                .orElseGet(() -> ResultDto.fail("Patient not found with id: " + id));
    }

    @Override
    public ResultDto<Patient> createPatient(Patient patient) {
        var user = userService.getUserById(patient.getUserId());

        if (!user.isSuccess()) {
            return ResultDto.fail("User not found with id: " + patient.getUserId());
        }

        Patient savedPatient = patientRepository.save(patient);
        return ResultDto.ok(savedPatient);
    }

    @Override
    public ResultDto<Patient> updatePatient(Long id, Patient patient) {

        var user = userService.getUserById(patient.getUserId());

        if (!user.isSuccess()) {
            return ResultDto.fail("User not found with id: " + patient.getUserId());
        }
        
        return patientRepository.findById(id)
                .map(existingPatient -> {
                    existingPatient.setFullName(patient.getFullName());
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
