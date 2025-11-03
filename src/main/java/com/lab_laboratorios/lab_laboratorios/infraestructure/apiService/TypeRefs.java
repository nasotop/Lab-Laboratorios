package com.lab_laboratorios.lab_laboratorios.infraestructure.apiService;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;

import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;


public final class TypeRefs {
    private TypeRefs() {}

    // Permite crear ParameterizedTypeReference<ResultDto<T>> sin clases anónimas explícitas
    public static <T> ParameterizedTypeReference<ResultDto<T>> resultOf(Class<T> inner) {
        ResolvableType type = ResolvableType.forClassWithGenerics(ResultDto.class, inner);
        @SuppressWarnings("unchecked")
        ParameterizedTypeReference<ResultDto<T>> ref =
                ParameterizedTypeReference.forType(type.getType());
        return ref;
    }
}