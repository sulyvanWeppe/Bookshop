package org.demo.springJdbcBookshop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ServiceModuleConfig {

    @Bean
    public WebMvcConfigurer corsController()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/bookshop_app/*").allowedOrigins("http://localhost:9090");
            }
        };
    }
}
