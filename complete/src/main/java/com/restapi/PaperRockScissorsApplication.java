package com.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//@PropertySource(value = "file:api.properties")
@SpringBootApplication
public class PaperRockScissorsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaperRockScissorsApplication.class, args);
    }
}
