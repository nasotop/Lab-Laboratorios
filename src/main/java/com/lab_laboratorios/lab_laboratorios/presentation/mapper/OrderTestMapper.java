package com.lab_laboratorios.lab_laboratorios.presentation.mapper;

import org.aspectj.weaver.ast.Test;

import com.lab_laboratorios.lab_laboratorios.infraestructure.model.OrderTest;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Priority;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.TestStatus;
import com.lab_laboratorios.lab_laboratorios.presentation.model.OrderTestModel;

public class OrderTestMapper {

    public static OrderTestModel toModel(OrderTest orderTest) {
        return OrderTestModel.builder()
                .id(orderTest.getId())
                .order(OrderMapper.toModel(orderTest.getOrder()))
                .testType(TestTypeMapper.toModel(orderTest.getTestType()))
                .laboratory(LaboratoryMapper.toModel(orderTest.getLaboratory()))
                .status(orderTest.getStatus().toString())
                .priority(orderTest.getPriority().toString())
                .scheduledStart(orderTest.getScheduledStart())
                .scheduledEnd(orderTest.getScheduledEnd())
                .build();
    }

    public static OrderTest toEntity(OrderTestModel orderTestModel) {
        return OrderTest.builder()
                .id(orderTestModel.getId())
                .order(OrderMapper.toEntity(orderTestModel.getOrder()))
                .testType(TestTypeMapper.toEntity(orderTestModel.getTestType()))
                .laboratory(LaboratoryMapper.toEntity(orderTestModel.getLaboratory()))
                .status(TestStatus.valueOf(orderTestModel.getStatus()))
                .priority(Priority.valueOf(orderTestModel.getPriority()))
                .scheduledStart(orderTestModel.getScheduledStart())
                .scheduledEnd(orderTestModel.getScheduledEnd())
                .build();
    }
}
