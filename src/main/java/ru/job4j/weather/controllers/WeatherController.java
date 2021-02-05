package ru.job4j.weather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import ru.job4j.weather.models.Weather;
import ru.job4j.weather.repositories.WeatherRepository;

import java.time.Duration;

@RestController
@CrossOrigin(value = {"*"})
@RequestMapping(value = "/weather")
public class WeatherController {
    
    @Autowired
    private WeatherRepository weathers;
    
    
    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> all() {
        Flux<Weather> data = weathers.all();
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(3));
        return Flux.zip(data, delay).map(Tuple2::getT1);
    }
    
    @GetMapping(value = "/{id}")
    public Mono<Weather> get(@PathVariable Integer id) {
        return weathers.findById(id);
    }
    
    @GetMapping(value = "/hottest")
    public Mono<Weather> hottest() {
        return weathers.findByHottest();
    }
    
    @GetMapping(value = "/cityGreatThen/{temperature}")
    public Flux<Weather> cityGreatThen(@PathVariable String temperature) {
        return weathers.cityGreatThen(Integer.parseInt(temperature));
    }
    
}
