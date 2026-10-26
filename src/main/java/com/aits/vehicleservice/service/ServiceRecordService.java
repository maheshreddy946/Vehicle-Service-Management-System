package com.aits.vehicleservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aits.vehicleservice.entity.BillOfMaterial;
import com.aits.vehicleservice.entity.ServiceRecord;
import com.aits.vehicleservice.entity.User;
import com.aits.vehicleservice.entity.Vehicle;
import com.aits.vehicleservice.enums.ServiceStatus;
import com.aits.vehicleservice.repository.BillOfMaterialRepository;
// ... other imports
import com.aits.vehicleservice.repository.ServiceRecordRepository;
import com.aits.vehicleservice.repository.UserRepository;
import com.aits.vehicleservice.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceRecordService {

    private final ServiceRecordRepository serviceRecordRepository;
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final BillOfMaterialRepository billOfMaterialRepository;


    public List<ServiceRecord> getScheduledServicesForAdvisor(Long advisorId) {
        User advisor = userRepository.findById(advisorId).orElseThrow();
        return serviceRecordRepository.findByAdvisorAndStatus(advisor, ServiceStatus.IN_PROGRESS);
    }

    public void addWorkItems(Long recordId, List<BillOfMaterial> items) {
        ServiceRecord record = serviceRecordRepository.findById(recordId).orElseThrow();
        for (BillOfMaterial item : items) {
            item.setServiceRecord(record);
        }
        billOfMaterialRepository.saveAll(items);
    }

    public void markAsCompleted(Long recordId) {
        ServiceRecord record = serviceRecordRepository.findById(recordId).orElseThrow();

        record.setStatus(ServiceStatus.COMPLETED);
        record.setEndTime(LocalDateTime.now());

        Vehicle vehicle = record.getVehicle();
        vehicle.setServiceStatus(ServiceStatus.COMPLETED);

        serviceRecordRepository.save(record);
        vehicleRepository.save(vehicle);
    }
    public List<ServiceRecord> getCompletedServices() {
        return serviceRecordRepository.findByStatus(ServiceStatus.COMPLETED);
    }
    public void scheduleService(Long vehicleId, Long advisorId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow();
        User advisor = userRepository.findById(advisorId).orElseThrow();

        ServiceRecord record = new ServiceRecord();
        record.setVehicle(vehicle);
        record.setAdvisor(advisor);
        record.setStatus(ServiceStatus.IN_PROGRESS);
        record.setStartTime(LocalDateTime.now());

        serviceRecordRepository.save(record);

        vehicle.setServiceStatus(ServiceStatus.IN_PROGRESS);
        vehicleRepository.save(vehicle);
    }

    public void completeAndInvoice(Long recordId) {
        ServiceRecord record = serviceRecordRepository.findById(recordId).orElseThrow();
        record.setStatus(ServiceStatus.COMPLETED);
        record.setEndTime(LocalDateTime.now());

        Vehicle vehicle = record.getVehicle();
        vehicle.setServiceStatus(ServiceStatus.COMPLETED);

        serviceRecordRepository.save(record);
        vehicleRepository.save(vehicle);
    }


}
