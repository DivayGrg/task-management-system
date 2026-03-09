package com.dway.task.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dway.task.model.User;
import com.dway.task.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Configuration
public class DataInitializer {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ROLE_ADMIN");
                userRepository.save(admin);
                System.out.println("✅ Admin created!");
            } else {
                System.out.println("ℹ️ Admin already exists, skipping...");
            }

            // Check if dway exists
            if (userRepository.findByUsername("dway").isEmpty()) {
                User user = new User();
                user.setUsername("dway");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRole("ROLE_USER");
                userRepository.save(user);
                System.out.println("✅ User dway created!");
            } else {
                System.out.println("ℹ️ User dway already exists, skipping...");
            }
        };
    }
}
