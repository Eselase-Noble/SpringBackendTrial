package com.example.Noble.Students;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){

        return args -> {
           Students Noble = new Students(

                    "Noble",
                    "eselase@gmail.com",
                    LocalDate.of(1999, OCTOBER, 29)

            );

            Students Eselase = new Students(

                    "Eselase",
                    "noble@gmail.com",
                    LocalDate.of(2002, NOVEMBER, 29)

            );

            repository.saveAll(
                    List.of(Noble, Eselase)
            );
        };
    }
}
