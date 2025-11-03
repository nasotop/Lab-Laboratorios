package com.lab_laboratorios.lab_laboratorios.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lab_laboratorios.lab_laboratorios.LabLaboratoriosApplication;
import com.lab_laboratorios.lab_laboratorios.application.interfaces.IOrderService;
import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Order;
import com.lab_laboratorios.lab_laboratorios.infraestructure.repository.OrderRepository;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PatientService patientService;

    @Override
    public ResultDto<Order> getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(ResultDto::ok)
                .orElseGet(() -> ResultDto.fail("Order not found with id: " + id));
    }

    @Override
    public ResultDto<Order> createOrder(Order order) {
        var patient = patientService.getPatientById(order.getPatient().getId());
        if (!patient.isSuccess()) {
            return ResultDto.fail("Patient doesn't exists");
        }
        Order savedOrder = orderRepository.save(order);
        return ResultDto.ok(savedOrder);
    }

    @Override
    public ResultDto<Order> updateOrder(Long id, Order order) {
        var patient = patientService.getPatientById(order.getPatient().getId());
        if (!patient.isSuccess()) {
            return ResultDto.fail("Patient doesn't exists");
        }
        return orderRepository.findById(id)
                .map(existingOrder -> {
                    existingOrder.setNotes(order.getNotes());
                    existingOrder.setOrderedAt(order.getOrderedAt());
                    existingOrder.setStatus(order.getStatus());

                    if (order.getPatient() != null) {
                        existingOrder.setPatient(order.getPatient());
                    }

                    Order updatedOrder = orderRepository.save(existingOrder);
                    return ResultDto.ok(updatedOrder);
                })
                .orElseGet(() -> ResultDto.fail("Order not found with id: " + id));
    }

    @Override
    public ResultDto<Void> deleteOrder(Long id) {
        return orderRepository.findById(id)
                .map(existingOrder -> {
                    orderRepository.delete(existingOrder);
                    return ResultDto.<Void>ok(null);
                })
                .orElseGet(() -> ResultDto.fail("Order not found with id: " + id));
    }

    @Override
    public ResultDto<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return ResultDto.ok(orders);
    }

    @Override
    public ResultDto<List<Order>> getOrdersByPatientId(Long patientId) {
        List<Order> orders = orderRepository.findAll().stream()
                .filter(order -> order.getPatient().getId().equals(patientId))
                .toList();
        return ResultDto.ok(orders);
    }

}
