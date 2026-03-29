package com.healthcare.order_service.controller;

import com.healthcare.order_service.dto.OrderRequestDTO;
import com.healthcare.order_service.dto.OrderResponseDTO;
import com.healthcare.order_service.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrderById(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(service.getOrdersByPatientId(patientId));
    }

    @GetMapping("/patient/{patientId}/count")
    public ResponseEntity<Long> countOrdersByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(service.countOrdersByPatientId(patientId));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createOrder(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Long id, @RequestBody OrderRequestDTO dto) {
        return ResponseEntity.ok(service.updateOrder(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}