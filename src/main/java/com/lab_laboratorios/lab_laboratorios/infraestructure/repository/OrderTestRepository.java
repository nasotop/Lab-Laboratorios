package com.lab_laboratorios.lab_laboratorios.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab_laboratorios.lab_laboratorios.infraestructure.model.OrderTest;

public interface OrderTestRepository extends JpaRepository<OrderTest, Long> {
    
}
