package com.lab_laboratorios.lab_laboratorios.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab_laboratorios.lab_laboratorios.application.service.OrderService;
import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.presentation.mapper.OrderMapper;
import com.lab_laboratorios.lab_laboratorios.presentation.model.OrderModel;

@RestController
@RequestMapping("api/laboratory/order")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    public ResultDto<List<OrderModel>> getAllOrders() {
        var orders = orderService.getAllOrders();

        if (!orders.isSuccess()) {
            return ResultDto.fail(orders.getMessage());
        }

        return ResultDto.ok(
                orders.getData()
                        .stream()
                        .map(order -> OrderMapper.toModel(order))
                        .toList());
    }

    public ResultDto<OrderModel> getOrderById(Long id) {
        var order = orderService.getOrderById(id);

        if (!order.isSuccess()) {
            return ResultDto.fail(order.getMessage());
        }

        return ResultDto.ok(OrderMapper.toModel(order.getData()));
    }
    public ResultDto<OrderModel> createOrder(OrderModel orderModel) {
        var orderEntity = OrderMapper.toEntity(orderModel);
        var createdOrder = orderService.createOrder(orderEntity);

        if (!createdOrder.isSuccess()) {
            return ResultDto.fail(createdOrder.getMessage());
        }

        return ResultDto.ok(OrderMapper.toModel(createdOrder.getData()));
    }
    public ResultDto<OrderModel> updateOrder(Long id, OrderModel orderModel) {
        var orderEntity = OrderMapper.toEntity(orderModel);
        var updatedOrder = orderService.updateOrder(id, orderEntity);

        if (!updatedOrder.isSuccess()) {
            return ResultDto.fail(updatedOrder.getMessage());
        }

        return ResultDto.ok(OrderMapper.toModel(updatedOrder.getData()));
    }
    public ResultDto<Void> deleteOrder(Long id) {
        var deletedOrder = orderService.deleteOrder(id);

        if (!deletedOrder.isSuccess()) {
            return ResultDto.fail(deletedOrder.getMessage());
        }

        return ResultDto.ok(null);
    }

    public ResultDto<List<OrderModel>> getOrdersByPatientId(Long patientId) {
        var orders = orderService.getOrdersByPatientId(patientId);

        if (!orders.isSuccess()) {
            return ResultDto.fail(orders.getMessage());
        }

        return ResultDto.ok(
                orders.getData()
                        .stream()
                        .map(order -> OrderMapper.toModel(order))
                        .toList());
    }


}
