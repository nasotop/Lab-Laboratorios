package com.lab_laboratorios.lab_laboratorios.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab_laboratorios.lab_laboratorios.application.service.OrderTestService;
import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.presentation.mapper.OrderTestMapper;
import com.lab_laboratorios.lab_laboratorios.presentation.model.OrderTestModel;

@RestController
@RequestMapping("api/laboratory/order-test")
@CrossOrigin(origins = "*")
public class OrderTestController {

    @Autowired
    private OrderTestService orderTestService;

    public ResultDto<List<OrderTestModel>> getAllOrderTests() {
        var orderTests = orderTestService.getAllOrderTests();

        if (!orderTests.isSuccess()) {
            return ResultDto.fail(orderTests.getMessage());
        }

        return ResultDto.ok(
                orderTests.getData()
                        .stream()
                        .map(orderTest -> OrderTestMapper.toModel(orderTest))
                        .toList());
    }
    public ResultDto<OrderTestModel> getOrderTestById(Long id) {
        var orderTest = orderTestService.getOrderTestById(id);

        if (!orderTest.isSuccess()) {
            return ResultDto.fail(orderTest.getMessage());
        }

        return ResultDto.ok(OrderTestMapper.toModel(orderTest.getData()));
    }
    public ResultDto<OrderTestModel> createOrderTest(OrderTestModel orderTestModel) {
        var orderTestEntity = OrderTestMapper.toEntity(orderTestModel);
        var createdOrderTest = orderTestService.createOrderTest(orderTestEntity);

        if (!createdOrderTest.isSuccess()) {
            return ResultDto.fail(createdOrderTest.getMessage());
        }

        return ResultDto.ok(OrderTestMapper.toModel(createdOrderTest.getData()));
    }
    public ResultDto<OrderTestModel> updateOrderTest(Long id, OrderTestModel orderTestModel) {
        var orderTestEntity = OrderTestMapper.toEntity(orderTestModel);
        var updatedOrderTest = orderTestService.updateOrderTest(id, orderTestEntity);

        if (!updatedOrderTest.isSuccess()) {
            return ResultDto.fail(updatedOrderTest.getMessage());
        }

        return ResultDto.ok(OrderTestMapper.toModel(updatedOrderTest.getData()));
    }
    public ResultDto<List<OrderTestModel>> getOrderTestsByOrderId(Long orderId) {
        var orderTests = orderTestService.getOrderTestsByOrderId(orderId);

        if (!orderTests.isSuccess()) {
            return ResultDto.fail(orderTests.getMessage());
        }

        return ResultDto.ok(
                orderTests.getData()
                        .stream()
                        .map(orderTest -> OrderTestMapper.toModel(orderTest))
                        .toList());
    }
    public ResultDto<Void> deleteOrderTest(Long id) {
        return orderTestService.deleteOrderTest(id);
    }


}
