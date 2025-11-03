package com.lab_laboratorios.lab_laboratorios.presentation.validation;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Specialization;
import com.lab_laboratorios.lab_laboratorios.presentation.model.LaboratoryModel;
@Component
public class LaboratoryValidation {
        private List<String> specializations = List.of(Specialization.values()).stream().map(Enum::name).toList();

    public ResultDto<List<String>> ValidateLaboratoryModel(LaboratoryModel laboratoryModel) {
        var errors = new java.util.ArrayList<String>();

        if (laboratoryModel == null) {
            errors.add("Laboratory data is null");
            return ResultDto.fail(errors);
        }

        if(!specializations.contains(laboratoryModel.getSpecialization()) ){
            errors.add("Specialization is not valid. Valid specializations are: " + String.join(", ", specializations));
        }


        return errors.isEmpty() ? ResultDto.ok(null) : ResultDto.fail(errors);  
    }
}
