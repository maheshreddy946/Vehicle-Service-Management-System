package com.aits.vehicleservice.dto;

import java.util.Set;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String email;
    private Set<String> roles; // ROLE_ADMIN or ROLE_SERVICE_ADVISOR
}
