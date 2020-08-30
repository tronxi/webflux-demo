package com.tronxi.webfluxdemo.controller;

import com.tronxi.webfluxdemo.model.Demo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "webflux")
public class WebfluxDemoController {

    @GetMapping("/demo")
    public Flux<Demo> getDemo() {
        return getFlux();
    }
    @GetMapping(value = "/demo/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Demo> getDemoStream() {
        return getFlux();
    }

    private Flux<Demo> getFlux() {
        return Flux.interval(Duration.ofSeconds(0))
                .take(10)
                .flatMap(str -> Mono.just(str.toString())
                        .map(Demo::new)
                        .subscribeOn(Schedulers.parallel()));
    }
}
