package com.lab_laboratorios.lab_laboratorios.infraestructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiConfiguration {
    @Value("${enviroment.api-gateway}")
    private String apiGateway;


    
    public String getApiGateway() {
        return apiGateway;
    }

}

