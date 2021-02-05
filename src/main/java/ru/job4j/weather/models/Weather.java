package ru.job4j.weather.models;

import lombok.*;

/* Lombok */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Weather {
    private int id;
    private String city;
    private int temperature;
    
}
