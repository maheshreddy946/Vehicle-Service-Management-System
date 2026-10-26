package com.aits.vehicleservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import com.aits.vehicleservice.enums.ServiceStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private String registrationNumber;
    private LocalDate lastServiceDate;
    private LocalDate nextServiceDueDate;
    
    @Enumerated(EnumType.STRING)
    private ServiceStatus serviceStatus;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference //Tells Jackson to ignore this during serialization (to avoid circular reference)
    private Customer customer;
    
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)//One vehicle can have many service records.If a vehicle is deleted, its service records are deleted too.
    private List<ServiceRecord> serviceRecords;

}
