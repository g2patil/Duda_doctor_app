package com.Duda_doctor_app;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.SessionCookieConfig;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ServletContextInitializer initializer() {
        return servletContext -> {
            SessionCookieConfig session = servletContext.getSessionCookieConfig();
            session.setName("JSESSIONID");
            session.setPath("/");
            session.setDomain("192.168.1.114"); // or specify the domain
            session.setSecure(false); // set to true if using HTTPS
            session.setHttpOnly(true);
            session.setMaxAge(1800); // set the max age of the cookie in seconds
        };
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://192.168.1.114:8081","http://localhost:8081","http://localhost:3000", "http://10.0.2.2:3000")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true);
    }
    
}


