package ru.job4j.weather.repositories;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.job4j.weather.models.Weather;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

@Component
public class WeatherRepository {
    
    private final Map<Integer, Weather> weathers = new ConcurrentHashMap<>();
    
    {
        weathers.put(1, new Weather(1, "Msc", 20));
        weathers.put(2, new Weather(2, "SPb", 15));
        weathers.put(3, new Weather(3, "Bryansk", 25));
        weathers.put(4, new Weather(4, "Smolensk", 15));
        weathers.put(5, new Weather(5, "Kiev", 15));
        weathers.put(6, new Weather(6, "Minsk", 15));
    }
    
    public Flux<Weather> all() {
        return Flux.fromIterable(weathers.values());
    }
    
    public Mono<Weather> findById(Integer id) {
        return Mono.justOrEmpty(weathers.get(id));
    }
    
    public Mono<Weather> findByHottest() {
        Weather rsl = weathers.values().stream()
                .reduce(BinaryOperator.maxBy(
                        Comparator.comparingInt(Weather::getTemperature)))
                .orElse(new Weather(-1, "", -1));
        return Mono.justOrEmpty(rsl);
    }
    
    public Flux<Weather> cityGreatThen(int temperature) {
        List<Weather> rsl = weathers.values().stream()
                .filter(e -> e.getTemperature() > temperature)
                .collect(Collectors.toList());
        return Flux.fromIterable(rsl);
    }
    
}