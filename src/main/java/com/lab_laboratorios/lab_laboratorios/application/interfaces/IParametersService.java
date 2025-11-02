package com.lab_laboratorios.lab_laboratorios.application.interfaces;

import java.util.List;

import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.OrderStatus;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Priority;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.Specialization;
import com.lab_laboratorios.lab_laboratorios.infraestructure.model.TestStatus;

public interface IParametersService {

    ResultDto<List<Specialization>> getAllSpecializations();

    ResultDto<List<Priority>> getAllPriorities();

    ResultDto<List<OrderStatus>> getAllOrderStatus();

    ResultDto<List<TestStatus>> getAllTestStatus();

}