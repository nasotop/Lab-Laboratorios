package com.lab_laboratorios.lab_laboratorios.infraestructure.apiService;

import reactor.netty.http.client.HttpClient;
import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    WebClient webClient(WebClient.Builder builder) {
        HttpClient http = HttpClient.create().responseTimeout(Duration.ofSeconds(10));

        return builder
                .clientConnector(new ReactorClientHttpConnector(http))
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
