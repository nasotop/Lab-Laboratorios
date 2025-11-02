package com.lab_laboratorios.lab_laboratorios.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab_laboratorios.lab_laboratorios.application.interfaces.ITestTypeService;
import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.TestType;
import com.lab_laboratorios.lab_laboratorios.infraestructure.repository.TestTypeRepository;

@Service
public class TestTypeService implements ITestTypeService {
    @Autowired
    private TestTypeRepository testTypeRepository;

    @Override
    public ResultDto<TestType> getTestTypeById(Long id) {
        return testTypeRepository.findById(id)
                .map(ResultDto::ok)
                .orElseGet(() -> ResultDto.fail("TestType not found with id: " + id));
    }

    @Override
    public ResultDto<TestType> createTestType(TestType testType) {
        TestType savedTestType = testTypeRepository.save(testType);
        return ResultDto.ok(savedTestType);
    }

    @Override
    public ResultDto<TestType> updateTestType(Long id, TestType testType) {
        return testTypeRepository.findById(id)
                .map(existingTestType -> {
                    existingTestType.setCode(testType.getCode());
                    existingTestType.setName(testType.getName());
                    existingTestType.setSpecialization(testType.getSpecialization());
                    existingTestType.setSampleType(testType.getSampleType());
                    TestType updatedTestType = testTypeRepository.save(existingTestType);
                    return ResultDto.ok(updatedTestType);
                })
                .orElseGet(() -> ResultDto.fail("TestType not found with id: " + id));
    }

    @Override
    public ResultDto<Void> deleteTestType(Long id) {
        return testTypeRepository.findById(id)
                .map(existingTestType -> {
                    testTypeRepository.delete(existingTestType);
                    return ResultDto.<Void>ok(null);
                })
                .orElseGet(() -> ResultDto.fail("TestType not found with id: " + id));
    }

    @Override
    public ResultDto<List<TestType>> getAllTestTypes() {
        List<TestType> testTypes = testTypeRepository.findAll();
        return ResultDto.ok(testTypes);
    }
 @Override
    public ResultDto<List<TestType>> getTestTypesBySpecialization(String specialization) {
        List<TestType> testTypes = testTypeRepository.findBySpecialization(specialization);
        return ResultDto.ok(testTypes);
    }
}
