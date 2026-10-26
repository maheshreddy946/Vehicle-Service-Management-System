package com.aits.vehicleservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VehicleDTO {
    private Long id;
    private String model;
    private String registrationNumber;
    private LocalDate lastServiceDate;
    private LocalDate nextServiceDueDate;
    private Long customerId;
}
