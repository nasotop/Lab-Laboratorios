package com.lab_laboratorios.lab_laboratorios.application.interfaces;

import java.util.List;

import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Laboratory;

public interface ILaboratoryService {
    ResultDto<List<Laboratory>> getAllLaboratories();
    ResultDto<Laboratory> getLaboratoryById(Long id);
    ResultDto<Laboratory> createLaboratory(Laboratory laboratory);
    ResultDto<Laboratory> updateLaboratory(Long id, Laboratory laboratory);
    ResultDto<Void> deleteLaboratory(Long id);
    ResultDto<List<Laboratory>> getLaboratoriesBySpecialization(String specialization);
}