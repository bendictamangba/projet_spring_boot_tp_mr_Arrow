//package com.tpnoel.reservation;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig {
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                // Permet l'accès aux API de localhost:8082 depuis localhost:8081
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:8081") // CORS pour le frontend
//                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Méthodes HTTP autorisées
//                        .allowedHeaders("*") // Permet tous les headers
//                        .allowCredentials(true); // Si vous avez des cookies ou des sessions
//            }
//        };
//    }
//}
//
