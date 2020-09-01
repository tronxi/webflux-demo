package com.tronxi.webfluxdemo.service;

import com.tronxi.webfluxdemo.model.Demo;
import com.tronxi.webfluxdemo.repository.DemoRepository;
import com.tronxi.webfluxdemo.repository.mapper.DemoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
@Service
public class DemoService {

    private final DemoRepository demoRepository;
    private final DemoMapper demoMapper;

    public Flux<Demo> findAllParallel() {
        return demoRepository
                .findAll()
                .flatMap(demoDocument -> Mono.just(demoDocument)
                        .map(demoMapper::toDomain)
                .subscribeOn(Schedulers.parallel())
                .flatMap(demo -> Mono.just(demo)
                        .map(Demo::process))
                        .subscribeOn(Schedulers.elastic()));
    }

    public Flux<Demo> findAll() {
        return demoRepository
                .findAll()
                .flatMap(demoDocument -> Mono.just(demoDocument)
                        .map(dc -> new Demo(dc.getTitle()))
                        .flatMap(demo -> Mono.just(demo)
                                .map(Demo::process)));
    }

    public Mono<Demo> insert(Demo demo) {
        return demoRepository.insert(demoMapper.toDocument(demo))
                .map(demoMapper::toDomain);
    }


}
