package com.aits.vehicleservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aits.vehicleservice.entity.ServiceRecord;
import com.aits.vehicleservice.entity.Vehicle;
import com.aits.vehicleservice.service.ServiceRecordService;
import com.aits.vehicleservice.service.UserService;
import com.aits.vehicleservice.service.VehicleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final VehicleService vehicleService;
    private final UserService userService;
    private final ServiceRecordService serviceRecordService;

    @GetMapping("/vehicles/due")
    public List<Vehicle> getVehiclesDueForService() {
        return vehicleService.getVehiclesDueForService();
    }

    @GetMapping("/vehicles/under-service")
    public List<Vehicle> getVehiclesUnderService() {
        return vehicleService.getVehiclesUnderService();
    }

    @GetMapping("/vehicles/serviced")
    public List<ServiceRecord> getServicedVehicles() {
        return serviceRecordService.getCompletedServices();
    }

    @PostMapping("/schedule/{vehicleId}")
    public ResponseEntity<String> scheduleService(
            @PathVariable Long vehicleId,
            @RequestParam Long advisorId) {
        serviceRecordService.scheduleService(vehicleId, advisorId);
        return ResponseEntity.ok("Service scheduled successfully.");
    }

    @PostMapping("/complete/{recordId}")
    public ResponseEntity<String> completeAndInvoice(@PathVariable Long recordId) {
        serviceRecordService.completeAndInvoice(recordId);
        return ResponseEntity.ok("Service completed, invoice generated.");
    }
}
