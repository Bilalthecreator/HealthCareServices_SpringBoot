package com.healthcare.order_service.service;


import com.healthcare.order_service.dto.OrderRequestDTO;
import com.healthcare.order_service.dto.OrderResponseDTO;
import com.healthcare.order_service.entity.Order;
import com.healthcare.order_service.entity.OrderStatus;
import com.healthcare.order_service.exception.ResourceNotFoundException;
import com.healthcare.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    private OrderResponseDTO toDTO(Order order) {
        return new OrderResponseDTO(
            order.getId(),
            order.getPatientId(),
            order.getMedicationName(),
            order.getQuantity(),
            order.getStatus().name(),
            order.getOrderDate(),
            order.getUpdatedAt()
        );
    }

    public List<OrderResponseDTO> getAllOrders() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public OrderResponseDTO getOrderById(Long id) {
        return toDTO(repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id)));
    }

    public List<OrderResponseDTO> getOrdersByPatientId(Long patientId) {
        return repository.findByPatientId(patientId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Long countOrdersByPatientId(Long patientId) {
        return repository.countByPatientId(patientId);
    }

    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
        if (dto.getPatientId() == null)
            throw new IllegalArgumentException("patientId cannot be null");
        if (dto.getMedicationName() == null || dto.getMedicationName().isBlank())
            throw new IllegalArgumentException("medicationName cannot be empty");

        Order order = new Order();
        order.setPatientId(dto.getPatientId());
        order.setMedicationName(dto.getMedicationName());
        order.setQuantity(dto.getQuantity());
        order.setStatus(dto.getStatus() != null
            ? OrderStatus.valueOf(dto.getStatus().toUpperCase())
            : OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        return toDTO(repository.save(order));
    }

    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO dto) {
        Order existing = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        existing.setMedicationName(dto.getMedicationName());
        existing.setQuantity(dto.getQuantity());
        if (dto.getStatus() != null)
            existing.setStatus(OrderStatus.valueOf(dto.getStatus().toUpperCase()));
        existing.setUpdatedAt(LocalDateTime.now());

        return toDTO(repository.save(existing));
    }

    public void deleteOrder(Long id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Order not found with id: " + id);
        repository.deleteById(id);
    }
}