package com.finestweber.msemail.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //.allowedOrigins("https://api-servidor-de-email-production.up.railway.app")
                .allowedMethods("POST")
                .allowCredentials(true)
                .exposedHeaders("Access-Control-Allow-Origin");
    }
}


