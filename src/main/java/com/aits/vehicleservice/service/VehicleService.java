package com.aits.vehicleservice.service;

import com.aits.vehicleservice.entity.Vehicle;
import com.aits.vehicleservice.enums.ServiceStatus;
import com.aits.vehicleservice.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElseThrow();
    }
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public List<Vehicle> getVehiclesDueForService() {
        return vehicleRepository.findByServiceStatus(ServiceStatus.DUE);
    }

    public List<Vehicle> getVehiclesUnderService() {
        return vehicleRepository.findByServiceStatus(ServiceStatus.IN_PROGRESS);
    }
}
