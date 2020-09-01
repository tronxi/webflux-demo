package com.tronxi.webfluxdemo.controller;

import com.tronxi.webfluxdemo.model.Demo;
import com.tronxi.webfluxdemo.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "webflux")
@RequiredArgsConstructor
public class WebfluxDemoController {

    private final DemoService demoService;

    @GetMapping(value = "/demo/parallel")
    public Flux<Demo> getDemoParallel() {
        return demoService.findAllParallel();
    }

    @GetMapping(value = "/demo")
    public Flux<Demo> getDemo() {
        return demoService.findAll();
    }

    @GetMapping(value = "/demo/parallel/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Demo> getDemoParallelStream() {
        return demoService.findAllParallel();
    }

    @PostMapping(value = "/demo")
    public Mono<ResponseEntity<Demo>> postDemo(@RequestBody Demo demo) {
        return demoService.insert(demo)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
