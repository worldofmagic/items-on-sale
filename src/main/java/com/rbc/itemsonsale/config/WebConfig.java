package com.rbc.itemsonsale.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/recommendations/**")
                .allowedOrigins("http://shopping.rbc.com")
                .allowedMethods("GET")
                .allowCredentials(true).maxAge(3600);

//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("POST", "GET")
//                .allowedHeaders("*")
//                .allowCredentials(true)
//                .maxAge(3600);

    }
}
