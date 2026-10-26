package com.aits.vehicleservice.service;

import com.aits.vehicleservice.entity.BillOfMaterial;
import com.aits.vehicleservice.repository.BillOfMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillOfMaterialService {

    private final BillOfMaterialRepository billRepository;

    public BillOfMaterial save(BillOfMaterial bill) {
        return billRepository.save(bill);
    }

    public List<BillOfMaterial> getByServiceRecordId(Long recordId) {
        return billRepository.findByServiceRecordId(recordId);
    }
}
