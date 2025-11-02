package com.lab_laboratorios.lab_laboratorios.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab_laboratorios.lab_laboratorios.application.service.LaboratoryService;
import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.presentation.mapper.LaboratoryMapper;
import com.lab_laboratorios.lab_laboratorios.presentation.model.LaboratoryModel;

@RestController
@RequestMapping("api/laboratory")
@CrossOrigin(origins = "*")
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

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
    public ResultDto<LaboratoryModel> getLaboratoryById(Long id) {
        var laboratory = laboratoryService.getLaboratoryById(id);

        if (!laboratory.isSuccess()) {
            return ResultDto.fail(laboratory.getMessage());
        }

        return ResultDto.ok(LaboratoryMapper.toModel(laboratory.getData()));
    }

    @PostMapping("/create")
    public ResultDto<LaboratoryModel> createLaboratory(LaboratoryModel laboratoryModel) {
        var laboratoryEntity = LaboratoryMapper.toEntity(laboratoryModel);
        var createdLaboratory = laboratoryService.createLaboratory(laboratoryEntity);

        if (!createdLaboratory.isSuccess()) {
            return ResultDto.fail(createdLaboratory.getMessage());
        }

        return ResultDto.ok(LaboratoryMapper.toModel(createdLaboratory.getData()));
    }

    @PostMapping("/update/{id}")
    public ResultDto<LaboratoryModel> updateLaboratory(Long id, LaboratoryModel laboratoryModel) {
        var laboratoryEntity = LaboratoryMapper.toEntity(laboratoryModel);
        var updatedLaboratory = laboratoryService.updateLaboratory(id, laboratoryEntity);

        if (!updatedLaboratory.isSuccess()) {
            return ResultDto.fail(updatedLaboratory.getMessage());
        }

        return ResultDto.ok(LaboratoryMapper.toModel(updatedLaboratory.getData()));
    }

    @PutMapping("/delete/{id}")
    public ResultDto<Void> deleteLaboratory(Long id) {

        return laboratoryService.deleteLaboratory(id);
    }
    @GetMapping("/get-by-specialization/{specialization}")
    public ResultDto<List<LaboratoryModel>> getLaboratoriesBySpecialization(String specialization) {
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
