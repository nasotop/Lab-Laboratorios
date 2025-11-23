package com.lab_laboratorios.lab_laboratorios.presentation.mapper;

import org.aspectj.weaver.ast.Test;

import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Laboratory;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Order;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.OrderTest;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Priority;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.TestStatus;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.TestType;
import com.lab_laboratorios.lab_laboratorios.presentation.model.OrderTestModel;

public class OrderTestMapper {

    public static OrderTest toEntity(OrderTestModel model) {
        if (model == null)
            return null;

        OrderTest entity = new OrderTest();
        entity.setId(model.getId());

        Order order = new Order();
        order.setId(model.getOrderId());
        entity.setOrder(order);

        TestType testType = new TestType();
        testType.setId(model.getTestTypeId());
        entity.setTestType(testType);

        Laboratory lab = new Laboratory();
        lab.setId(model.getLaboratoryId());
        entity.setLaboratory(lab);

        entity.setPriority(Priority.valueOf(model.getPriority()));
        entity.setStatus(TestStatus.valueOf(model.getStatus()));
        entity.setScheduledStart(model.getScheduledStart());
        entity.setScheduledEnd(model.getScheduledEnd());

        return entity;
    }

    public static OrderTestModel toModel(OrderTest e) {
        return OrderTestModel.builder()
                .id(e.getId())
                .orderId(e.getOrder().getId())
                .testTypeId(e.getTestType().getId())
                .laboratoryId(e.getLaboratory().getId())
                .priority(e.getPriority().name())
                .status(e.getStatus().name())
                .scheduledStart(e.getScheduledStart())
                .scheduledEnd(e.getScheduledEnd())
                .build();
    }
}
