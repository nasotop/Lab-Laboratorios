package com.lab_laboratorios.lab_laboratorios.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab_laboratorios.lab_laboratorios.application.interfaces.IOrderTestService;
import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.OrderTest;
import com.lab_laboratorios.lab_laboratorios.infraestructure.repository.OrderTestRepository;

@Service
public class OrderTestService implements IOrderTestService {
    @Autowired
    private OrderTestRepository orderTestRepository;

    @Override
    public ResultDto<OrderTest> createOrderTest(OrderTest orderTest) {
        OrderTest savedOrderTest = orderTestRepository.save(orderTest);
        return ResultDto.ok(savedOrderTest);
    }

    @Override
    public ResultDto<OrderTest> getOrderTestById(Long id) {
        return orderTestRepository.findById(id)
                .map(ResultDto::ok)
                .orElseGet(() -> ResultDto.fail("OrderTest not found with id: " + id));
    }

    @Override
    public ResultDto<OrderTest> updateOrderTest(Long id, OrderTest orderTest) {
        return orderTestRepository.findById(id)
                .map(existingOrderTest -> {
                    existingOrderTest.setTestType(orderTest.getTestType());
                    existingOrderTest.setPriority(orderTest.getPriority());
                    existingOrderTest.setStatus(orderTest.getStatus());
                    existingOrderTest.setOrder(orderTest.getOrder());
                    existingOrderTest.setLaboratory(orderTest.getLaboratory());
                    existingOrderTest.setScheduledStart(orderTest.getScheduledStart());
                    existingOrderTest.setScheduledEnd(orderTest.getScheduledEnd());
                    OrderTest updatedOrderTest = orderTestRepository.save(existingOrderTest);
                    return ResultDto.ok(updatedOrderTest);
                })
                .orElseGet(() -> ResultDto.fail("OrderTest not found with id: " + id));
    }

    @Override
    public ResultDto<Void> deleteOrderTest(Long id) {
        return orderTestRepository.findById(id)
                .map(existingOrderTest -> {
                    orderTestRepository.delete(existingOrderTest);
                    return ResultDto.<Void>ok(null);
                })
                .orElseGet(() -> ResultDto.fail("OrderTest not found with id: " + id));
    }

    @Override
    public ResultDto<List<OrderTest>> getAllOrderTests() {
        List<OrderTest> orderTests = orderTestRepository.findAll();
        return ResultDto.ok(orderTests);
    }

    @Override
    public ResultDto<List<OrderTest>> getOrderTestsByOrderId(Long orderId) {
        List<OrderTest> orderTests = orderTestRepository.findAll().stream()
                .filter(orderTest -> orderTest.getOrder().getId().equals(orderId))
                .toList();
        return ResultDto.ok(orderTests);
    }
}
