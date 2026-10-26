package com.aits.vehicleservice.repository;

import com.aits.vehicleservice.entity.WorkItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkItemRepository extends JpaRepository<WorkItem, Long> {
}
