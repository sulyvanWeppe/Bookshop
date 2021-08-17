package org.demo.springJdbcBookshop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ServiceModuleConfig {

    @Bean
    public WebMvcConfigurer corsController()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/bookshop_app/**")
                        .allowedOrigins("http://localhost:9000")
                        .allowedMethods("PUT","DELETE","GET","POST");
            }
        };
    }

    @Bean
    public Docket bookshopApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("org.demo.springJdbcBookshop")).build();
    }
}
