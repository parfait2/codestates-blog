package com.example.day6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // Article에서 등록 시간, 수정 시간
@SpringBootApplication
public class Day6Application {

    public static void main(String[] args) {
        SpringApplication.run(Day6Application.class, args);
    }

}
