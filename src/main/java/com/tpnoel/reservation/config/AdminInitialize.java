package com.tpnoel.reservation.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import  com.tpnoel.reservation.model.User;
import  com.tpnoel.reservation.model.User.Role;
import  com.tpnoel.reservation.repository.UserRepository;

@Configuration
public class AdminInitialize {

    @Bean
    public CommandLineRunner initUsers(UserRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (utilisateurRepository.findAll().isEmpty()) {
                
                User admin = new User();
                admin.setSurname("Admin User");
                admin.setPhoneNumber("898989");
                admin.setAddress("juju");
                admin.setSex("M");
                admin.setEmail("admin@admin.com");
                admin.setPassword(passwordEncoder.encode("123")); 
                admin.setRole(Role.ADMIN);
                utilisateurRepository.save(admin);

        

                System.out.println("Utilisateurs initialisés avec succès !");
            }
        };
    }
}