package com.aits.vehicleservice.controller;

import com.aits.vehicleservice.entity.BillOfMaterial;
import com.aits.vehicleservice.service.ServiceRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advisor")
@RequiredArgsConstructor
public class ServiceAdvisorController {

    private final ServiceRecordService serviceRecordService;

    @GetMapping("/assigned")
    public ResponseEntity<?> getAssignedServices(@RequestParam Long advisorId) {
        return ResponseEntity.ok(serviceRecordService.getScheduledServicesForAdvisor(advisorId));
    }

    @PostMapping("/add-items/{recordId}")
    public ResponseEntity<?> addBillItems(
            @PathVariable Long recordId,
            @RequestBody List<BillOfMaterial> items) {
        serviceRecordService.addWorkItems(recordId, items);
        return ResponseEntity.ok("Items added successfully.");
    }

    @PostMapping("/complete/{recordId}")
    public ResponseEntity<?> completeService(@PathVariable Long recordId) {
        serviceRecordService.markAsCompleted(recordId);
        return ResponseEntity.ok("Service marked as completed.");
    }
}
