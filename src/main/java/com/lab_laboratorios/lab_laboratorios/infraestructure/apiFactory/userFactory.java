package com.lab_laboratorios.lab_laboratorios.infraestructure.apiFactory;

import org.springframework.stereotype.Component;

import com.lab_laboratorios.lab_laboratorios.infraestructure.configuration.ApiConfiguration;

@Component
public class userFactory {
    
    private final String baseUrl;

    public final String getUser;
    public userFactory(ApiConfiguration apiConfig) {
        this.baseUrl =  apiConfig.getApiGateway() + "/api/user/";
        this.getUser =  baseUrl + "{id}";
    }
}
