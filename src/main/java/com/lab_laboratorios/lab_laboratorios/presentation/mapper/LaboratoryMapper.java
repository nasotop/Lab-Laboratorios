package com.lab_laboratorios.lab_laboratorios.presentation.mapper;

import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Laboratory;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Specialization;
import com.lab_laboratorios.lab_laboratorios.presentation.model.LaboratoryModel;

public class LaboratoryMapper {
    public static LaboratoryModel toModel(Laboratory laboratory) {
        return LaboratoryModel.builder()
                .id(laboratory.getId())
                .name(laboratory.getName())
                .location(laboratory.getLocation())
                .capacity(laboratory.getCapacity())
                .specialization(laboratory.getSpecialization().toString())
                .build();
    }

    public static Laboratory toEntity(LaboratoryModel laboratoryModel) {
        return Laboratory.builder()
                .id(laboratoryModel.getId())
                .name(laboratoryModel.getName())
                .location(laboratoryModel.getLocation())
                .capacity(laboratoryModel.getCapacity())
                .specialization(Specialization.valueOf(laboratoryModel.getSpecialization()))
                .build();
    }
}
