package com.tpnoel.reservation.config;


import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        // Crée un RestTemplate avec un convertisseur JSON
        RestTemplate restTemplate = new RestTemplate();

        // Ajoute le convertisseur MappingJackson2HttpMessageConverter pour le JSON
       List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
       messageConverters.add(new MappingJackson2HttpMessageConverter());

        return restTemplate;
    }
}
