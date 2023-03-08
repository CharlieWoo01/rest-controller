package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository repository) {
        return args -> {
            String email = "charliewoo1412@gmail.com";
            Address address = new Address(
                    "England",
                    "London",
                    "ME58RT"
            );
            Student student = new Student(
                    "Charlie",
                    "Roberts",
                    email,
                    Gender.MALE,
                    address,
                    List.of("Computer Science", "Maths"),
                    BigDecimal.TEN,
                    LocalDateTime.now()
            );

            repository.findStudentByEmail(email)
                    .ifPresentOrElse(s -> System.out.println(s + " already exists"), () -> {
                        repository.insert(student);
                        System.out.println("Inserting student " + student);
                    });
        };
    }
}
