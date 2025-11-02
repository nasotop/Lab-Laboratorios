package com.lab_laboratorios.lab_laboratorios.application.interfaces;

import java.util.List;

import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.TestType;

public interface ITestTypeService {
    ResultDto<TestType> getTestTypeById(Long id);
    ResultDto<TestType> createTestType(TestType testType);
    ResultDto<TestType> updateTestType(Long id, TestType testType);
    ResultDto<Void> deleteTestType(Long id);
    ResultDto<List<TestType>> getAllTestTypes();
    ResultDto<List<TestType>> getTestTypesBySpecialization(String specialization);
}
