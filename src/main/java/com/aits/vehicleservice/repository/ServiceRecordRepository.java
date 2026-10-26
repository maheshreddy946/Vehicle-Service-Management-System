package com.aits.vehicleservice.repository;

import com.aits.vehicleservice.entity.ServiceRecord;
import com.aits.vehicleservice.entity.User;
import com.aits.vehicleservice.enums.ServiceStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {
    List<ServiceRecord> findByServiceAdvisorIdAndStatus(Long advisorId, String status);
    List<ServiceRecord> findByStatus(String status);
    List<ServiceRecord> findByAdvisorAndStatus(User advisor, ServiceStatus status);
    List<ServiceRecord> findByStatus(ServiceStatus status);


}
