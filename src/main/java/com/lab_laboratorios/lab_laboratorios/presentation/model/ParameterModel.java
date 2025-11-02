package com.lab_laboratorios.lab_laboratorios.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParameterModel {
    public Long value;
    public String description;
}
