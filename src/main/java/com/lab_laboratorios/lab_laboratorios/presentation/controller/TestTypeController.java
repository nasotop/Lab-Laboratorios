package com.lab_laboratorios.lab_laboratorios.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab_laboratorios.lab_laboratorios.application.service.TestTypeService;
import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.presentation.mapper.TestTypeMapper;
import com.lab_laboratorios.lab_laboratorios.presentation.model.TestTypeModel;

@RestController
@RequestMapping("api/laboratory/test-type")
@CrossOrigin(origins = "*")
public class TestTypeController {
    @Autowired
    private TestTypeService testTypeService;

    public ResultDto<List<TestTypeModel>> getAllTestTypes() {
        var testTypes = testTypeService.getAllTestTypes();

        if (!testTypes.isSuccess()) {
            return ResultDto.fail(testTypes.getMessage());
        }

        return ResultDto.ok(
                testTypes.getData()
                        .stream()
                        .map(testType -> TestTypeMapper.toModel(testType))
                        .toList());
    }

    public ResultDto<TestTypeModel> getTestTypeById(Long id) {
        var testType = testTypeService.getTestTypeById(id);

        if (!testType.isSuccess()) {
            return ResultDto.fail(testType.getMessage());
        }

        return ResultDto.ok(TestTypeMapper.toModel(testType.getData()));
    }

    public ResultDto<TestTypeModel> createTestType(TestTypeModel testTypeModel) {
        var testTypeEntity = TestTypeMapper.toEntity(testTypeModel);
        var createdTestType = testTypeService.createTestType(testTypeEntity);

        if (!createdTestType.isSuccess()) {
            return ResultDto.fail(createdTestType.getMessage());
        }

        return ResultDto.ok(TestTypeMapper.toModel(createdTestType.getData()));
    }

    public ResultDto<TestTypeModel> updateTestType(Long id, TestTypeModel testTypeModel) {
        var testTypeEntity = TestTypeMapper.toEntity(testTypeModel);
        var updatedTestType = testTypeService.updateTestType(id, testTypeEntity);

        if (!updatedTestType.isSuccess()) {
            return ResultDto.fail(updatedTestType.getMessage());
        }

        return ResultDto.ok(TestTypeMapper.toModel(updatedTestType.getData()));
    }

    public ResultDto<Void> deleteTestType(Long id) {
        var deletedTestType = testTypeService.deleteTestType(id);

        if (!deletedTestType.isSuccess()) {
            return ResultDto.fail(deletedTestType.getMessage());
        }

        return ResultDto.ok(null);
    }

    public ResultDto<List<TestTypeModel>> getTestTypesBySpecialization(String specialization) {
        var testTypes = testTypeService.getTestTypesBySpecialization(specialization);

        if (!testTypes.isSuccess()) {
            return ResultDto.fail(testTypes.getMessage());
        }

        return ResultDto.ok(
                testTypes.getData()
                        .stream()
                        .map(testType -> TestTypeMapper.toModel(testType))
                        .toList());
    }

}
