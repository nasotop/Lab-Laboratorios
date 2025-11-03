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

import com.lab_laboratorios.lab_laboratorios.application.service.OrderTestService;
import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.presentation.mapper.OrderTestMapper;
import com.lab_laboratorios.lab_laboratorios.presentation.model.OrderTestModel;
import com.lab_laboratorios.lab_laboratorios.presentation.validation.OrderTestValidation;
@RestController
@RequestMapping("api/laboratory/order-test")
@CrossOrigin(origins = "*")
public class OrderTestController {

    @Autowired
    private OrderTestService orderTestService;
    @Autowired
    private OrderTestValidation orderTestValidation;

    @GetMapping("/all")
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

    @GetMapping("/{id}")
    public ResultDto<OrderTestModel> getOrderTestById(@PathVariable Long id) {
        var orderTest = orderTestService.getOrderTestById(id);

        if (!orderTest.isSuccess()) {
            return ResultDto.fail(orderTest.getMessage());
        }

        return ResultDto.ok(OrderTestMapper.toModel(orderTest.getData()));
    }

    @PostMapping("/create")
    public ResultDto<OrderTestModel> createOrderTest(@RequestBody OrderTestModel orderTestModel) {
        var validation = orderTestValidation.ValidateOrderTestModel(orderTestModel);
        if (!validation.isSuccess()) {
            return ResultDto.fail(validation.getErrorMessage());
        }

        var orderTestEntity = OrderTestMapper.toEntity(orderTestModel);
        var createdOrderTest = orderTestService.createOrderTest(orderTestEntity);

        if (!createdOrderTest.isSuccess()) {
            return ResultDto.fail(createdOrderTest.getMessage());
        }

        return ResultDto.ok(OrderTestMapper.toModel(createdOrderTest.getData()));
    }

    @PutMapping("/update/{id}")
    public ResultDto<OrderTestModel> updateOrderTest(@PathVariable Long id, @RequestBody OrderTestModel orderTestModel) {
        var validation = orderTestValidation.ValidateOrderTestModel(orderTestModel);
        if (!validation.isSuccess()) {
            return ResultDto.fail(validation.getErrorMessage());
        }

        var orderTestEntity = OrderTestMapper.toEntity(orderTestModel);
        var updatedOrderTest = orderTestService.updateOrderTest(id, orderTestEntity);

        if (!updatedOrderTest.isSuccess()) {
            return ResultDto.fail(updatedOrderTest.getMessage());
        }

        return ResultDto.ok(OrderTestMapper.toModel(updatedOrderTest.getData()));
    }

    @GetMapping("/by-order/{orderId}")
    public ResultDto<List<OrderTestModel>> getOrderTestsByOrderId(@PathVariable Long orderId) {
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

    @DeleteMapping("/delete/{id}")
    public ResultDto<Void> deleteOrderTest(@PathVariable Long id) {
        return orderTestService.deleteOrderTest(id);
    }
}
