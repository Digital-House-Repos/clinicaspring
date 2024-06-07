package com.backend.clinica;

import com.backend.clinica.repository.DBInitializer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaApplication {
    @Autowired
    private DBInitializer dbInitializer;

    public static void main(String[] args) {
        SpringApplication.run(ClinicaApplication.class, args);
    }

    @PostConstruct
    public void init() {
        dbInitializer.loadDataToOrm();
    }
}
