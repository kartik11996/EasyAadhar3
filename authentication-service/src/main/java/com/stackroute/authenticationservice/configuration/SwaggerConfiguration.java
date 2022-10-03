package com.stackroute.authenticationservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2

public class SwaggerConfiguration {


    public static   final String authorization_header="Authorization";

    private ApiKey apiKeys(){
        return new ApiKey("JWT",authorization_header,"header");
    }

    private List<SecurityContext> securityContextList() {

        return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
    }

    private List<SecurityReference> sf(){
        AuthorizationScope scopes=new AuthorizationScope("global","accessEverything");
        return Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[] {scopes}));
    }
        @Bean
        public Docket api() {



            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(getInfo())
                    .securityContexts(securityContextList())
                    .securitySchemes(Arrays.asList(apiKeys()))
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())
                    .build() ;

        }

        private ApiInfo getInfo() {
            ApiInfo apiInfo = new ApiInfo(
                    "EasyAadhar Slot Booking Application",
                    "An Application for booking the slot to update the Aadhar ",
                    "Authentication Service Application",
                    "Terms of service",
                    "easyaadhar@gmail.com",
                    "License of API",
                    "https://swagger.io/docs/");
            return apiInfo;
        }

    }

