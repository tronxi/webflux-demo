package com.tronxi.webfluxdemo.controller;

import com.tronxi.webfluxdemo.model.Demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(path = "webflux", produces = "application/stream+json")
public class WebfluxDemoController {

    @GetMapping("/demo")
    public Flux<Demo> qualityListing() {
        return Flux.just("hola", "que", "tal")
                .flatMap(str -> Mono.just(str)
                    .map(Demo::new)
                    .subscribeOn(Schedulers.parallel()));
    }
}
