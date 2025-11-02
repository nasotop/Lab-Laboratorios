package com.lab_laboratorios.lab_laboratorios.application.service;

import java.util.List;

import org.aspectj.weaver.ast.Test;
import org.springframework.stereotype.Service;

import com.lab_laboratorios.lab_laboratorios.application.interfaces.IParametersService;
import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.OrderStatus;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Priority;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Specialization;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.TestStatus;

@Service
public class ParametersService implements IParametersService {

    @Override
    public ResultDto<List<Specialization>> getAllSpecializations() {
        var specializations = List.of(Specialization.values());
        return ResultDto.ok(specializations);
    }

    @Override
    public ResultDto<List<Priority>> getAllPriorities() {
        var priorities = List.of(Priority.values());
        return ResultDto.ok(priorities);
    }

    @Override
    public ResultDto<List<OrderStatus>> getAllOrderStatus() {
        var statuses = List.of(OrderStatus.values());
        return ResultDto.ok(statuses);
    }

    @Override
    public ResultDto<List<TestStatus>> getAllTestStatus() {
        var statuses = List.of(TestStatus.values());
        return ResultDto.ok(statuses);
    }

}
