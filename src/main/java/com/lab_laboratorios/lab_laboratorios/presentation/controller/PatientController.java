package com.lab_laboratorios.lab_laboratorios.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("get-all")
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

    @GetMapping("{id}")

    public ResultDto<PatientModel> getPatientById(@PathVariable Long id) {
        var patient = patientService.getPatientById(id);

        if (!patient.isSuccess()) {
            return ResultDto.fail(patient.getMessage());
        }

        return ResultDto.ok(PatientMapper.toModel(patient.getData()));
    }

    @PostMapping("create")

    public ResultDto<PatientModel> createPatient(@RequestBody PatientModel patientModel) {
        var patientEntity = PatientMapper.toEntity(patientModel);
        var createdPatient = patientService.createPatient(patientEntity);

        if (!createdPatient.isSuccess()) {
            return ResultDto.fail(createdPatient.getMessage());
        }

        return ResultDto.ok(PatientMapper.toModel(createdPatient.getData()));
    }

    @PostMapping("update/{id}")

    public ResultDto<PatientModel> updatePatient(@PathVariable Long id, @RequestBody PatientModel patientModel) {
        var patientEntity = PatientMapper.toEntity(patientModel);
        var updatedPatient = patientService.updatePatient(id, patientEntity);

        if (!updatedPatient.isSuccess()) {
            return ResultDto.fail(updatedPatient.getMessage());
        }

        return ResultDto.ok(PatientMapper.toModel(updatedPatient.getData()));
    }

    @DeleteMapping("delete/{id}")
    public ResultDto<Void> deletePatient(@PathVariable Long id) {

        return patientService.deletePatient(id);
    }

}
