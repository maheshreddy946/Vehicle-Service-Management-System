package com.aits.vehicleservice.repository;

import com.aits.vehicleservice.entity.BillOfMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillOfMaterialRepository extends JpaRepository<BillOfMaterial, Long> {
    List<BillOfMaterial> findByServiceRecordId(Long serviceRecordId); //Custom method to get BOM entries for a specific service record

}
