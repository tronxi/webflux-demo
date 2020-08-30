package com.tronxi.webfluxdemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReactorDemoTest {

    @Test
    public void verifyFlux() {
        List<String> fruitList = Arrays.asList("Apple", "Orange", "Grape", "Banana", "Strawberry");
        List<String> nameList = Arrays.asList("Sergio", "pablo");
        Flux<String> fruitFlux = Flux.fromIterable(fruitList);
        Flux<String> nameFlux = Flux.fromIterable(nameList).delaySubscription(Duration.ofSeconds(2));

        Flux<String> mergedFlux = fruitFlux.mergeWith(nameFlux);

        StepVerifier.create(mergedFlux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .expectNext("Sergio")
                .expectNext("pablo")
                .verifyComplete();
    }

    @Test
    public void generateLongAndVerify() {

        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1))
                .take(5)
                .skip(1);


        StepVerifier.create(intervalFlux)
                .expectNext(1L)
                .expectNext(2L)
                .expectNext(3L)
                .expectNext(4L)
                .verifyComplete();
    }

    @Test
    public void zipTuple() {
        List<String> fruitList = Arrays.asList("Apple", "Orange", "Grape", "Banana", "Strawberry");
        List<String> nameList = Arrays.asList("Sergio", "pablo");
        Flux<String> fruitFlux = Flux.fromIterable(fruitList);
        Flux<String> nameFlux = Flux.fromIterable(nameList);

        Flux<Tuple2<String, String>> tuple2Flux = Flux.zip(fruitFlux, nameFlux);
        tuple2Flux.subscribe(System.out::println);

        StepVerifier.create(tuple2Flux)
                .expectNextMatches(p -> p.getT1().equals("Apple") && p.getT2().equals("Sergio"))
                .expectNextMatches(p -> p.getT1().equals("Orange") && p.getT2().equals("pablo"))
                .verifyComplete();

    }

    @Test
    public void zipToMergedString() {
        List<String> fruitList = Arrays.asList("Apple", "Orange", "Grape", "Banana", "Strawberry");
        List<String> nameList = Arrays.asList("Sergio", "pablo");
        Flux<String> fruitFlux = Flux.fromIterable(fruitList);
        Flux<String> nameFlux = Flux.fromIterable(nameList);

        Flux<String> zipString = Flux.zip(fruitFlux, nameFlux, (f, n) -> "Fruit " + f + " name " + n);
        zipString.subscribe(System.out::println);

    }

    @Test
    public void testFirst() {
        List<String> fruitList = Arrays.asList("Apple", "Orange", "Grape", "Banana", "Strawberry");
        List<String> nameList = Arrays.asList("Sergio", "pablo");
        Flux<String> fruitFlux = Flux.fromIterable(fruitList).delaySubscription(Duration.ofSeconds(2));
        Flux<String> nameFlux = Flux.fromIterable(nameList);

        Flux<String> first = Flux.first(fruitFlux, nameFlux);
        first.subscribe(System.out::println);

    }

    @Test
    public void shouldMapParallel() {
        List<String> fruitList = Arrays.asList("Apple", "Orange", "Grape", "Banana", "Strawberry");
        List<String> fruitListUpperCase = Arrays.asList("APPLE", "ORANGE", "GRAPE", "BANANA", "STRAWBERRY");

        Flux<String> fruitFlux = Flux.fromIterable(fruitList);

        Flux<String> upperCase = fruitFlux.flatMap(f -> Mono.just(f)
            .map(String::toUpperCase)
            .subscribeOn(Schedulers.parallel()));

        upperCase.subscribe(System.out::println);

        StepVerifier.create(upperCase)
                .expectNextMatches(fruitListUpperCase::contains)
                .expectNextMatches(fruitListUpperCase::contains)
                .expectNextMatches(fruitListUpperCase::contains)
                .expectNextMatches(fruitListUpperCase::contains)
                .expectNextMatches(fruitListUpperCase::contains)
                .verifyComplete();
    }

    @Test
    public void shouldCreateMap() {
        List<String> fruitList = Arrays.asList("Apple", "Orange", "Grape", "Banana", "Strawberry");

        Flux<String> fruitFlux = Flux.fromIterable(fruitList);

        Mono<Map<String, String>> map = fruitFlux.collectMap(f -> f.substring(0, 1));

        map.subscribe(System.out::println);

        StepVerifier.create(map)
                .expectNextMatches(f -> f.size() == 5)
                .verifyComplete();
    }

}
