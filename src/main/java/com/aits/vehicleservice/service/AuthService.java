package com.aits.vehicleservice.service;

import com.aits.vehicleservice.dto.LoginRequest;
import com.aits.vehicleservice.dto.SignupRequest;
import com.aits.vehicleservice.entity.Role;
import com.aits.vehicleservice.entity.User;
import com.aits.vehicleservice.repository.RoleRepository;
import com.aits.vehicleservice.repository.UserRepository;
import com.aits.vehicleservice.security.CustomUserDetailsService;
import com.aits.vehicleservice.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String jwt = jwtUtils.generateToken(userDetails.getUsername());


        return ResponseEntity.ok(jwt);
    }

    public ResponseEntity<?> register(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        // Convert Set<String> to Set<Role.RoleName>
        Set<Role.RoleName> roleNames = signupRequest.getRoles().stream()
            .map(Role.RoleName::valueOf)
            .collect(Collectors.toSet());

        Set<Role> roles = roleRepository.findByNameIn(roleNames);
        user.setRoles(roles);

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

}
