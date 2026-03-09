package com.dway.task.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Waterfall logic: Pehle permissions set karo
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Sirf Admin access
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Dono dekh sakte hain
                        .requestMatchers("/", "/css/**", "/js/**").permitAll() // Sabke liye open
                        .anyRequest().authenticated() // Baki sab ke liye login zaroori
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true) // Login ke baad home par bhejega
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Password ko hash karne ke liye
    }
}