package com.lab_laboratorios.lab_laboratorios.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab_laboratorios.lab_laboratorios.application.service.OrderService;
import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.presentation.mapper.OrderMapper;
import com.lab_laboratorios.lab_laboratorios.presentation.model.OrderModel;
import com.lab_laboratorios.lab_laboratorios.presentation.validation.OrderValidation;

@RestController
@RequestMapping("api/laboratory/order")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderValidation orderValidation;

    @GetMapping("/all")
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

    @GetMapping("/{id}")
    public ResultDto<OrderModel> getOrderById(@PathVariable Long id) {
        var order = orderService.getOrderById(id);

        if (!order.isSuccess()) {
            return ResultDto.fail(order.getMessage());
        }

        return ResultDto.ok(OrderMapper.toModel(order.getData()));
    }

    @PostMapping("/create")
    public ResultDto<OrderModel> createOrder(@RequestBody OrderModel orderModel) {
        var validation = orderValidation.ValidateOrderModel(orderModel);
        if (!validation.isSuccess()) {
            return ResultDto.fail(validation.getErrorMessage());
        }
        var orderEntity = OrderMapper.toEntity(orderModel);
        var createdOrder = orderService.createOrder(orderEntity);

        if (!createdOrder.isSuccess()) {
            return ResultDto.fail(createdOrder.getMessage());
        }

        return ResultDto.ok(OrderMapper.toModel(createdOrder.getData()));
    }

    @PutMapping("/update/{id}")
    public ResultDto<OrderModel> updateOrder(@PathVariable Long id, @RequestBody OrderModel orderModel) {
        var validation = orderValidation.ValidateOrderModel(orderModel);
        if (!validation.isSuccess()) {
            return ResultDto.fail(validation.getErrorMessage());
        }

        var orderEntity = OrderMapper.toEntity(orderModel);
        var updatedOrder = orderService.updateOrder(id, orderEntity);

        if (!updatedOrder.isSuccess()) {
            return ResultDto.fail(updatedOrder.getMessage());
        }

        return ResultDto.ok(OrderMapper.toModel(updatedOrder.getData()));
    }

    @DeleteMapping("/delete/{id}")
    public ResultDto<Void> deleteOrder(@PathVariable Long id) {
        var deletedOrder = orderService.deleteOrder(id);

        if (!deletedOrder.isSuccess()) {
            return ResultDto.fail(deletedOrder.getMessage());
        }

        return ResultDto.ok(null);
    }

    @GetMapping("/by-patient/{patientId}")
    public ResultDto<List<OrderModel>> getOrdersByPatientId(@PathVariable Long patientId) {
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
