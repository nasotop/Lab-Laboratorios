package com.lab_laboratorios.lab_laboratorios.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab_laboratorios.lab_laboratorios.application.service.PatientService;
import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.presentation.mapper.PatientMapper;
import com.lab_laboratorios.lab_laboratorios.presentation.model.PatientModel;

@RestController
@RequestMapping("api/laboratory/patient")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    public ResultDto<List<PatientModel>> getAllPatients() {
        var patients = patientService.getAllPatients();

        if (!patients.isSuccess()) {
            return ResultDto.fail(patients.getMessage());
        }

        return ResultDto.ok(
                patients.getData()
                        .stream()
                        .map(patient -> PatientMapper.toModel(patient))
                        .toList());
    }

    public ResultDto<PatientModel> getPatientById(Long id) {
        var patient = patientService.getPatientById(id);

        if (!patient.isSuccess()) {
            return ResultDto.fail(patient.getMessage());
        }

        return ResultDto.ok(PatientMapper.toModel(patient.getData()));
    }

    public ResultDto<PatientModel> createPatient(PatientModel patientModel) {
        var patientEntity = PatientMapper.toEntity(patientModel);
        var createdPatient = patientService.createPatient(patientEntity);

        if (!createdPatient.isSuccess()) {
            return ResultDto.fail(createdPatient.getMessage());
        }

        return ResultDto.ok(PatientMapper.toModel(createdPatient.getData()));
    }

    public ResultDto<PatientModel> updatePatient(Long id, PatientModel patientModel) {
        var patientEntity = PatientMapper.toEntity(patientModel);
        var updatedPatient = patientService.updatePatient(id, patientEntity);

        if (!updatedPatient.isSuccess()) {
            return ResultDto.fail(updatedPatient.getMessage());
        }

        return ResultDto.ok(PatientMapper.toModel(updatedPatient.getData()));
    }

    public ResultDto<Void> deletePatient(Long id) {

        return patientService.deletePatient(id);
    }


}
