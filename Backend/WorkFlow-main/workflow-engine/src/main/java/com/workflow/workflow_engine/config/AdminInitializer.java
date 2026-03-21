package com.workflow.workflow_engine.config;





//==================== Import Statements ==============================
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.workflow.workflow_engine.entity.User;
import com.workflow.workflow_engine.repository.UserRepository;



//==================== Configuration Class Declaration ==============================
@Configuration
public class AdminInitializer implements CommandLineRunner {

 // ==================== Dependency Injection ==============================
 @Autowired
 private UserRepository userRepository;
 
 @Autowired
 private PasswordEncoder passwordEncoder;

 // ==================== Application Startup Logic ==============================
 @Override
 public void run(String... args) {

     if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {

         User admin = new User();
         admin.setName("admin");
         admin.setEmail("admin@gmail.com");
         admin.setRole("ADMIN");
         admin.setPassword(passwordEncoder.encode("admin@123"));
         admin.setVerified(true);

         userRepository.save(admin);
         
         User manager = new User();
         manager.setName("Thiru");
         manager.setEmail("manager@gmail.com");
         manager.setRole("MANAGER");
         manager.setPassword(passwordEncoder.encode("manager@123"));
         manager.setVerified(true);

         userRepository.save(manager);
         
         User bankmanager = new User();
         bankmanager.setName("Nami");
         bankmanager.setEmail("bankmanager@gmail.com");
         bankmanager.setRole("BANK_MANAGER");
         bankmanager.setPassword(passwordEncoder.encode("bankmanager@123"));
         bankmanager.setVerified(true);

         userRepository.save(bankmanager);
         
         User hr = new User();
         hr.setName("zoro");
         hr.setEmail("hr@gmail.com");
         hr.setRole("HR");
         hr.setPassword(passwordEncoder.encode("hr@123"));
         hr.setVerified(true);

         userRepository.save(hr);
         
         User assistantman = new User();
         assistantman.setName("samyugthan");
         assistantman.setEmail("assistmanager@gmail.com");
         assistantman.setRole("BANK_ASSISTANT_MANAGER");
         assistantman.setPassword(passwordEncoder.encode("assist@123"));
         assistantman.setVerified(true);

         userRepository.save(assistantman);

         System.out.println("Default ADMIN created");
     }
 }
}
