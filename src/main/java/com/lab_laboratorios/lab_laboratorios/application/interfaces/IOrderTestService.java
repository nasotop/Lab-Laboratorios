package com.lab_laboratorios.lab_laboratorios.application.interfaces;

import java.util.List;

import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.OrderTest;

public interface IOrderTestService {
    ResultDto<OrderTest> createOrderTest(OrderTest orderTest);
    ResultDto<OrderTest> getOrderTestById(Long id);
    ResultDto<OrderTest> updateOrderTest(Long id, OrderTest orderTest);
    ResultDto<Void> deleteOrderTest(Long id);
    ResultDto<List<OrderTest>> getAllOrderTests();
    ResultDto<List<OrderTest>> getOrderTestsByOrderId(Long orderId);
}