package com.lab_laboratorios.lab_laboratorios.application.interfaces;

import java.util.List;

import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Order;

public interface IOrderService {
    ResultDto<Order> getOrderById(Long id);
    ResultDto<Order> createOrder(Order order);
    ResultDto<Order> updateOrder(Long id, Order order);
    ResultDto<Void> deleteOrder(Long id);
    ResultDto<List<Order>> getAllOrders();
    ResultDto<List<Order>> getOrdersByPatientId(Long patientId);
}