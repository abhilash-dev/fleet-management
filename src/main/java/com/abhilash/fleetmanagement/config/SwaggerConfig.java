package com.abhilash.fleetmanagement.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
@Slf4j
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.abhilash.fleetmanagement.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo(
                        "Fleet-Management API documentation",
                        "Fleet-Management API system for a truck company (Mock)",
                        "1.0",
                        "",
                        new Contact("Abhilash Sulibela", "https://www.linkedin.com/in/abhilash-sulibela-5158576b/", "abhilash1869@gmail.com"),
                        "",
                        "",
                        Collections.emptyList()
                ));
    }
}
