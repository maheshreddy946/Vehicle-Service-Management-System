package com.aits.vehicleservice.repository;

import com.aits.vehicleservice.entity.Vehicle;
import com.aits.vehicleservice.enums.ServiceStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByNextServiceDueDateBetween(LocalDate start, LocalDate end);
    List<Vehicle> findByServiceRecords_Status(String status);//Get vehicles where any related ServiceRecord has a specific status
    List<Vehicle> findByServiceStatus(ServiceStatus status);
}
