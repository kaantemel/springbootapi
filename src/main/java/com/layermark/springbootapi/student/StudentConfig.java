package com.layermark.springbootapi.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args ->{
            Student kaan = new Student(
                    "Kaan",
                    LocalDate.of(2000, 10, 05),
                    "kaantemel@gmail.com");
            Student berdan = new Student(
                    "Berdan",
                    LocalDate.of(1999, 12, 28),
                    "berdansen@gmail.com");

            repository.saveAll(
                    List.of(kaan, berdan)
            );
        };
    }
}
