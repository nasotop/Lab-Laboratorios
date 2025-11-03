package com.lab_laboratorios.lab_laboratorios.presentation.validation;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.OrderStatus;
import com.lab_laboratorios.lab_laboratorios.presentation.model.OrderModel;
@Component
public class OrderValidation {
    private List<String> orderStatuses = List.of(OrderStatus.values()).stream().map(Enum::name).toList();

    public ResultDto<List<String>> ValidateOrderModel(OrderModel orderModel) {
        var errors = new java.util.ArrayList<String>();

        if (orderModel == null) {
            errors.add("Order data is null");
            return ResultDto.fail(errors);
        }

        if (!orderStatuses.contains(orderModel.getStatus())) {
            errors.add("Status is not valid. Valid statuses are: " + String.join(", ", orderStatuses));
        }

        return errors.isEmpty() ? ResultDto.ok(null) : ResultDto.fail(errors);
    }
}
