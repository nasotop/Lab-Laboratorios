package com.lab_laboratorios.lab_laboratorios.presentation.mapper;

import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Order;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.OrderStatus;
import com.lab_laboratorios.lab_laboratorios.presentation.model.OrderModel;

public class OrderMapper {
    
public static OrderModel toModel(Order order) {
        return OrderModel.builder()
                .id(order.getId())
                .patient(PatientMapper.toModel(order.getPatient()))
                .orderedAt(order.getOrderedAt())
                .status(order.getStatus().toString())
                .notes(order.getNotes())
                .build();
    }

    public static Order toEntity(OrderModel orderModel) {
        return Order.builder()
                .id(orderModel.getId())
                .patient(PatientMapper.toEntity(orderModel.getPatient()))
                .orderedAt(orderModel.getOrderedAt())
                .status(OrderStatus.valueOf(orderModel.getStatus()))
                .notes(orderModel.getNotes())
                .build();
    }

}
