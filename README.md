# Healthcare Microservices

Two Spring Boot microservices communicating over HTTP.

## Services

| Service | Port | Description |
|---|---|---|
| patient-service | 8080 | Manages patient records |
| order-service | 8081 | Manages medication orders |

## How to run

Start order-service first, then patient-service.

### order-service
cd order-service
./mvnw spring-boot:run

### patient-service
cd patient-service
./mvnw spring-boot:run

## Key endpoints

- GET  http://localhost:8080/patients
- POST http://localhost:8080/patients
- GET  http://localhost:8080/patients/{id}/orders  ← combines both services
- GET  http://localhost:8081/orders
- POST http://localhost:8081/orders