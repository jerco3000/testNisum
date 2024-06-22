package com.prueba.nisum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//** Api de prueba para Nisum
@SpringBootApplication
@EnableWebMvc
public class TestNisumApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestNisumApplication.class, args);
    }

}
