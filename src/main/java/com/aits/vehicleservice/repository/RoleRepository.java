package com.aits.vehicleservice.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aits.vehicleservice.entity.Role;
import com.aits.vehicleservice.entity.Role.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
    Set<Role> findByNameIn(Set<RoleName> names);

}
