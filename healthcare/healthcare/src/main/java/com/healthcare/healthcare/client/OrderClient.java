package com.healthcare.healthcare.client;

import com.healthcare.healthcare.dto.OrderResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class OrderClient {

    private final RestClient restClient;

    public OrderClient(@Value("${order.service.url}") String orderServiceUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(orderServiceUrl)
                .build();
    }

    public List<OrderResponse> getOrdersByPatientId(Long patientId) {
        return restClient.get()
                .uri("/orders/patient/{patientId}", patientId)
                .retrieve()
                .body(new ParameterizedTypeReference<List<OrderResponse>>() {});
    }

    public Long countOrdersByPatientId(Long patientId) {
        return restClient.get()
                .uri("/orders/patient/{patientId}/count", patientId)
                .retrieve()
                .body(Long.class);
    }
}