package com.lab_laboratorios.lab_laboratorios.presentation.validation;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Priority;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.TestStatus;
import com.lab_laboratorios.lab_laboratorios.presentation.model.OrderTestModel;

@Component
public class OrderTestValidation {
    private List<String> priority = List.of(Priority.values()).stream().map(Enum::name).toList();
    private List<String> testStatuses = List.of(TestStatus.values()).stream().map(Enum::name).toList();

    public ResultDto<List<String>> ValidateOrderTestModel(OrderTestModel orderTestModel) {
        var errors = new java.util.ArrayList<String>();

        if (orderTestModel == null) {
            errors.add("Order Test data is null");
            return ResultDto.fail(errors);
        }

        if (!priority.contains(orderTestModel.getPriority())) {
            errors.add("Priority is not valid. Valid priorities are: " + String.join(", ", priority));
        }
        
        if (!testStatuses.contains(orderTestModel.getStatus())) {
            errors.add("Test Status is not valid. Valid statuses are: " + String.join(", ", testStatuses));
        }

        return errors.isEmpty() ? ResultDto.ok(null) : ResultDto.fail(errors);
    }
}
