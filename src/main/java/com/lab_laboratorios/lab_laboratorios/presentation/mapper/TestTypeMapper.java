package com.lab_laboratorios.lab_laboratorios.presentation.mapper;

import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Specialization;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.TestType;
import com.lab_laboratorios.lab_laboratorios.presentation.model.TestTypeModel;

public class TestTypeMapper {
    public static TestTypeModel toModel(TestType testType) {
        return TestTypeModel.builder()
                .id(testType.getId())
                .name(testType.getName())
                .code(testType.getCode())
                .specialization(testType.getSpecialization().toString())
                .sampleType(testType.getSampleType())
                .build();
    }

    public static TestType toEntity(TestTypeModel testTypeModel) {
        return TestType.builder()
                .id(testTypeModel.getId())
                .name(testTypeModel.getName())
                .code(testTypeModel.getCode())
                .specialization(Specialization.valueOf(testTypeModel.getSpecialization()))
                .sampleType(testTypeModel.getSampleType())
                .build();
    }
}
