package com.lab_laboratorios.lab_laboratorios.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab_laboratorios.lab_laboratorios.application.service.ParametersService;
import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.presentation.mapper.ParametersMapper;
import com.lab_laboratorios.lab_laboratorios.presentation.model.ParameterModel;
@RestController
@RequestMapping("api/laboratory/parameter")
@CrossOrigin(origins = "*")
public class ParametersController {
    @Autowired
    private ParametersService parametersService;

    @GetMapping("/specializations")
    public ResultDto<List<ParameterModel>> getSpecializations() {
        var specializations = parametersService.getAllSpecializations();

        if (!specializations.isSuccess()) {
            return ResultDto.fail(specializations.getMessage());
        }

        return ResultDto.ok(
                specializations.getData()
                        .stream()
                        .map(specialization -> ParametersMapper.toParameterModel(specialization))
                        .toList());
    }

    @GetMapping("/test-status")
    public ResultDto<List<ParameterModel>> getTestStatus() {
        var testStatuses = parametersService.getAllTestStatus();

        if (!testStatuses.isSuccess()) {
            return ResultDto.fail(testStatuses.getMessage());
        }

        return ResultDto.ok(
                testStatuses.getData()
                        .stream()
                        .map(testStatus -> ParametersMapper.toParameterModel(testStatus))
                        .toList());
    }

    @GetMapping("/priorities")
    public ResultDto<List<ParameterModel>> getPriorities() {
        var priorities = parametersService.getAllPriorities();

        if (!priorities.isSuccess()) {
            return ResultDto.fail(priorities.getMessage());
        }

        return ResultDto.ok(
                priorities.getData()
                        .stream()
                        .map(priority -> ParametersMapper.toParameterModel(priority))
                        .toList());
    }

    @GetMapping("/order-status")
    public ResultDto<List<ParameterModel>> getOrderStatus() {
        var orderStatuses = parametersService.getAllOrderStatus();

        if (!orderStatuses.isSuccess()) {
            return ResultDto.fail(orderStatuses.getMessage());
        }

        return ResultDto.ok(
                orderStatuses.getData()
                        .stream()
                        .map(orderStatus -> ParametersMapper.toParameterModel(orderStatus))
                        .toList());
    }
}
