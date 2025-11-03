package com.lab_laboratorios.lab_laboratorios.infraestructure.apiService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ApiClient {

    private final WebClient webClient;

    public <R> Mono<R> invoke(
            HttpMethod method,
            String urlTemplate, 
            Map<String, ?> pathParams, 
            Map<String, ?> queryParams,
            Map<String, String> headers,
            Object body,
            ParameterizedTypeReference<R> responseType) {
        UriComponentsBuilder ucb = UriComponentsBuilder.fromUriString(urlTemplate);

        if (queryParams != null && !queryParams.isEmpty()) {
            queryParams.forEach((k, v) -> {
                if (v instanceof Iterable<?> it) {
                    List<Object> values = new ArrayList<>();
                    it.forEach(values::add);
                    ucb.queryParam(k, values.toArray());
                } else if (v != null && v.getClass().isArray()) {
                    ucb.queryParam(k, (Object[]) v);
                } else {
                    ucb.queryParam(k, v);
                }
            });
        }

        UriComponents uc = (pathParams != null && !pathParams.isEmpty())
                ? ucb.buildAndExpand(pathParams)
                : ucb.build();

        URI uri = uc.encode().toUri();

        WebClient.RequestBodySpec spec = webClient.method(method).uri(uri);

        if (headers != null && !headers.isEmpty()) {
            headers.forEach(spec::header);
        }
        if (supportsBody(method) && body != null) {
            spec.bodyValue(body);
        }

        return spec
                .retrieve()
                .onStatus(HttpStatusCode::isError, resp -> resp.bodyToMono(String.class)
                        .defaultIfEmpty("")
                        .flatMap(msg -> Mono.error(new ApiCallException(resp.statusCode(), msg))))
                .bodyToMono(responseType);
    }

    public <R> R invokeBlocking(
            HttpMethod method,
            String absoluteUrl,
            Map<String, ?> pathParams,
            Map<String, ?> queryParams,
            Map<String, String> headers,
            Object body,
            ParameterizedTypeReference<R> responseType) {
        return invoke(method, absoluteUrl, pathParams, queryParams, headers, body, responseType).block();
    }

    private static boolean supportsBody(HttpMethod m) {
        return m == HttpMethod.POST || m == HttpMethod.PUT || m == HttpMethod.PATCH || m == HttpMethod.DELETE;
    }

    public static final class ApiCallException extends RuntimeException {
        private final HttpStatusCode status;

        public ApiCallException(HttpStatusCode status, String body) {
            super("HTTP " + status.value() + " - " + body);
            this.status = status;
        }

        public HttpStatusCode getStatus() {
            return status;
        }
    }
}