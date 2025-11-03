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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab_laboratorios.lab_laboratorios.application.service.LaboratoryService;
import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.presentation.mapper.LaboratoryMapper;
import com.lab_laboratorios.lab_laboratorios.presentation.model.LaboratoryModel;
import com.lab_laboratorios.lab_laboratorios.presentation.validation.LaboratoryValidation;

@RestController
@RequestMapping("api/laboratory")
@CrossOrigin(origins = "*")
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;
    @Autowired
    private LaboratoryValidation laboratoryValidation;


    @GetMapping("/get-all")
    public ResultDto<List<LaboratoryModel>> getAllLaboratories() {
        var laboratories = laboratoryService.getAllLaboratories();

        if (!laboratories.isSuccess()) {
            return ResultDto.fail(laboratories.getMessage());
        }

        return ResultDto.ok(
                laboratories.getData()
                        .stream()
                        .map(laboratory -> LaboratoryMapper.toModel(laboratory))
                        .toList());
    }

    @GetMapping("/get-by-id/{id}")
    public ResultDto<LaboratoryModel> getLaboratoryById(@PathVariable Long id) {
        var laboratory = laboratoryService.getLaboratoryById(id);

        if (!laboratory.isSuccess()) {
            return ResultDto.fail(laboratory.getMessage());
        }

        return ResultDto.ok(LaboratoryMapper.toModel(laboratory.getData()));
    }

    @PostMapping("/create")
    public ResultDto<LaboratoryModel> createLaboratory(@RequestBody LaboratoryModel laboratoryModel) {

      

        var validation = laboratoryValidation.ValidateLaboratoryModel(laboratoryModel);
        if (!validation.isSuccess()) {
            return ResultDto.fail(validation.getErrorMessage());
        }

        var laboratoryEntity = LaboratoryMapper.toEntity(laboratoryModel);

        var createdLaboratory = laboratoryService.createLaboratory(laboratoryEntity);

        if (!createdLaboratory.isSuccess()) {
            return ResultDto.fail(createdLaboratory.getMessage());
        }

        return ResultDto.ok(LaboratoryMapper.toModel(createdLaboratory.getData()));
    }

    @PostMapping("/update/{id}")
    public ResultDto<LaboratoryModel> updateLaboratory(@PathVariable Long id,
            @RequestBody LaboratoryModel laboratoryModel) {

        var validation = laboratoryValidation.ValidateLaboratoryModel(laboratoryModel);

        if (!validation.isSuccess()) {
            return ResultDto.fail(validation.getErrorMessage());
        }

        var laboratoryEntity = LaboratoryMapper.toEntity(laboratoryModel);

        var updatedLaboratory = laboratoryService.updateLaboratory(id, laboratoryEntity);

        if (!updatedLaboratory.isSuccess()) {
            return ResultDto.fail(updatedLaboratory.getMessage());
        }

        return ResultDto.ok(LaboratoryMapper.toModel(updatedLaboratory.getData()));
    }

    @DeleteMapping("/delete/{id}")
    public ResultDto<Void> deleteLaboratory(@PathVariable Long id) {

        return laboratoryService.deleteLaboratory(id);
    }

    @GetMapping("/get-by-specialization/{specialization}")
    public ResultDto<List<LaboratoryModel>> getLaboratoriesBySpecialization(@PathVariable String specialization) {
        var laboratories = laboratoryService.getLaboratoriesBySpecialization(specialization);

        if (!laboratories.isSuccess()) {
            return ResultDto.fail(laboratories.getMessage());
        }

        return ResultDto.ok(
                laboratories.getData()
                        .stream()
                        .map(laboratory -> LaboratoryMapper.toModel(laboratory))
                        .toList());
    }

}
