package com.lab_laboratorios.lab_laboratorios.infraestructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiConfiguration {
    @Value("${enviroment.api-user}")
    private String apiUser;


    
    public String getApiUser() {
        return apiUser;
    }

}

