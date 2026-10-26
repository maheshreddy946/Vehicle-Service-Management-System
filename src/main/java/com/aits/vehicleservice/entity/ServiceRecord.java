package com.aits.vehicleservice.entity;

import com.aits.vehicleservice.enums.ServiceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate serviceDate;
    private LocalDateTime endTime;
    private LocalDateTime startTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User advisor;

    @Enumerated(EnumType.STRING)
    private ServiceStatus status;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "service_advisor_id")
    private User serviceAdvisor;

    @OneToMany(mappedBy = "serviceRecord", cascade = CascadeType.ALL)
    private List<BillOfMaterial> billOfMaterials;
}
