package com.tronxi.webfluxdemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ReactorDemoTest {

    @Test
    void verifyFlux() {
        List<String> fruitList = Arrays.asList("Apple", "Orange", "Grape", "Banana", "Strawberry");
        Flux<String> fruitFlux = Flux.fromIterable(fruitList);

        StepVerifier.create(fruitFlux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }

    @Test
    void generateLongAndVerify() {

        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1))
                .take(5);
        intervalFlux.subscribe(System.out::println);
        intervalFlux.subscribe(System.out::println);

        StepVerifier.create(intervalFlux)
                .expectNext(0L)
                .expectNext(1L)
                .expectNext(2L)
                .expectNext(3L)
                .expectNext(4L)
                .verifyComplete();
    }
}
