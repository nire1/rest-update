package com.example.restupdated;

import com.example.restupdated.model.Department;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class RestUpdatedApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestUpdatedApplication.class, args);


    }

}
