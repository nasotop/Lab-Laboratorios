package com.lab_laboratorios.lab_laboratorios.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab_laboratorios.lab_laboratorios.application.interfaces.ILaboratoryService;
import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Laboratory;
import com.lab_laboratorios.lab_laboratorios.infraestructure.repository.LaboratoryRepository;

@Service
public class LaboratoryService implements ILaboratoryService {
    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Override
    public ResultDto<List<Laboratory>> getAllLaboratories() {
        List<Laboratory> laboratories = laboratoryRepository.findAll();
        return ResultDto.ok(laboratories);
    }

    @Override
    public ResultDto<Laboratory> getLaboratoryById(Long id) {
        return laboratoryRepository.findById(id)
                .map(ResultDto::ok)
                .orElseGet(() -> ResultDto.fail("Laboratory not found with id: " + id));
    }

    @Override
    public ResultDto<Laboratory> createLaboratory(Laboratory laboratory) {
        Laboratory savedLaboratory = laboratoryRepository.save(laboratory);
        return ResultDto.ok(savedLaboratory);
    }

    @Override
    public ResultDto<Laboratory> updateLaboratory(Long id, Laboratory laboratory) {
        return laboratoryRepository.findById(id)
                .map(existingLab -> {
                    existingLab.setName(laboratory.getName());
                    existingLab.setLocation(laboratory.getLocation());
                    existingLab.setCapacity(laboratory.getCapacity());
                    existingLab.setSpecialization(laboratory.getSpecialization());
                    Laboratory updatedLab = laboratoryRepository.save(existingLab);
                    return ResultDto.ok(updatedLab);
                })
                .orElseGet(() -> ResultDto.fail("Laboratory not found with id: " + id));
    }

    @Override
    public ResultDto<Void> deleteLaboratory(Long id) {
        return laboratoryRepository.findById(id)
                .map(existingLab -> {
                    laboratoryRepository.delete(existingLab);
                    return ResultDto.<Void>ok(null);
                })
                .orElseGet(() -> ResultDto.fail("Laboratory not found with id: " + id));
    }

    @Override
    public ResultDto<List<Laboratory>> getLaboratoriesBySpecialization(String specialization) {
        List<Laboratory> laboratories = laboratoryRepository.findBySpecialization(specialization);
        return ResultDto.ok(laboratories);
    }

}
