package ru.job4j.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class);
        System.err.println("APP RUN");
    }
    
}

