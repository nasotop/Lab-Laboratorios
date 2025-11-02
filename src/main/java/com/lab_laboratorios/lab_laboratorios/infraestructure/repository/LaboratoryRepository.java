package com.lab_laboratorios.lab_laboratorios.infraestructure.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Laboratory;
public interface LaboratoryRepository extends  JpaRepository<Laboratory, Long> {

    List<Laboratory> findBySpecialization(String specialization);
    
}
