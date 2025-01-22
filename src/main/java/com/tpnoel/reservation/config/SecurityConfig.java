package com.tpnoel.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf().disable() // Désactive CSRF si nécessaire
        
            .authorizeHttpRequests(auth -> auth
            		// Définit les règles d'autorisation pour les requêtes HTTP	
                .requestMatchers("/login", "/register", "/css/**", "/js/**", "/users/**","/media/**","/assets/**","/user/**", "/dashboard/**", "/","/uploads/**").permitAll() // Autorise les ressources publiques
                .anyRequest().authenticated() // Protège toutes les autres URL
                
            )
            .formLogin(form -> form
                .loginPage("/login") // Définit la page de connexion personnalisée
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/dashboard", true) // Redirige après une connexion réussie
                .permitAll() // Permet à tous les utilisateurs d'accéder à la page de connexion
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // URL pour se déconnecter
                .logoutSuccessUrl("/login?logout") // Redirection après la déconnexion
                .permitAll() // Permet à tous les utilisateurs de se déconnecter
            )
            .sessionManagement(session -> session.invalidSessionUrl("/login?expired")); // Gestion des sessions expirées

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Utilisation de BCrypt pour l'encodage des mots de passe
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        // Fournit l'AuthenticationManager basé sur la configuration Spring Security
        return authConfig.getAuthenticationManager();
    }
}