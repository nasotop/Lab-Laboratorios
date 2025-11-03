package com.lab_laboratorios.lab_laboratorios.infraestructure.service;

import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.lab_laboratorios.lab_laboratorios.domain.dataTransferObject.ResultDto;
import com.lab_laboratorios.lab_laboratorios.infraestructure.apiFactory.userFactory;
import com.lab_laboratorios.lab_laboratorios.infraestructure.apiService.ApiClient;
import com.lab_laboratorios.lab_laboratorios.infraestructure.apiService.TypeRefs;
import com.lab_laboratorios.lab_laboratorios.infraestructure.dataTransferObject.UserDto;

@Service
public class UserService {
    private final ApiClient api;
    private final userFactory routes; 

    public UserService(userFactory routes, ApiClient api) {
        this.api = api;
        this.routes = routes;
    }

     public ResultDto<UserDto> getUserById(Long id) {
        try {
            Map<String, Object> params = Map.of("id", id);
            return api.invokeBlocking(
                    HttpMethod.GET,
                    routes.getUser,
                    params,
                    null,
                    null,
                    null,
                    TypeRefs.resultOf(UserDto.class));
        } catch (Exception e) {
            return ResultDto.fail("Error fetching user: " + e.getMessage());
        }
    }
}
