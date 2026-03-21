package com.workflow.workflow_engine.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.workflow.workflow_engine.entity.User;
import com.workflow.workflow_engine.repository.UserRepository;
import com.workflow.workflow_engine.security.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public User register(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
    private final EmailService emailService;
    private final OtpService otpService;

    public String sendOtp(User user){

        String otp = otpService.generateOtp();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setOtp(otp);
        user.setVerified(false);

        userRepository.save(user);

        emailService.sendOtp(user.getEmail(),otp);

        return "OTP sent to email";
    }

    public User verifyOtp(String email,String otp){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getOtp().equals(otp)){
            throw new RuntimeException("Invalid OTP");
        }

        user.setVerified(true);
        user.setOtp(null);

        userRepository.save(user);

        return user;
    }
    
    public String getEmail(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            String email = jwtUtil.extractEmail(token);

            return email;
        }

        return "Invalid token";

}
}
