package com.lab_laboratorios.lab_laboratorios.presentation.mapper;

import com.lab_laboratorios.lab_laboratorios.presentation.model.ParameterModel;

public class ParametersMapper {
    
    public static ParameterModel toParameterModel(Enum<?> enumConstant) {
        return ParameterModel.builder()
                .value((long) enumConstant.ordinal())
                .description(enumConstant.name())
                .build();
    }

}
