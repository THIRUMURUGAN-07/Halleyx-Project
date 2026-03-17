package com.workflow.workflow_engine.controller;

import com.workflow.workflow_engine.dto.LoginRequest;
import com.workflow.workflow_engine.dto.NameRoleDto;
import com.workflow.workflow_engine.dto.OtpVerifyDto;
import com.workflow.workflow_engine.entity.User;
import com.workflow.workflow_engine.repository.UserRepository;
import com.workflow.workflow_engine.security.JwtUtil;
import com.workflow.workflow_engine.service.AuthService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody User user){

        return authService.sendOtp(user);
    }

    @PostMapping("/verify-otp")
    public User verifyOtp(@RequestBody OtpVerifyDto otp){
    	
    	String email = otp.getEmail();
    	
    	String otp1 = otp.getOtp();

        return authService.verifyOtp(email,otp1);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        if(!user.isVerified()) {
        	return Map.of("Response", "User Not verified");
        }

        return Map.of("token", jwtUtil.generateToken(user.getEmail()));
    }
    
    @GetMapping("/email")
    public NameRoleDto getEmail(@RequestHeader("Authorization") String authHeader){

        String token = authHeader.substring(7);

        String email =  jwtUtil.extractUsername(token);
        
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        NameRoleDto nameRoleDto = new NameRoleDto(user.getName(),user.getRole());
        return nameRoleDto;
    }
}