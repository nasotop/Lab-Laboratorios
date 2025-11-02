package com.lab_laboratorios.lab_laboratorios.infraestructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab_laboratorios.lab_laboratorios.infraestructure.model.TestType;

public interface TestTypeRepository extends JpaRepository<TestType, Long> {

    List<TestType> findBySpecialization(String specialization);
    
}
