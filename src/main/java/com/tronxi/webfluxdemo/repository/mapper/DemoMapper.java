package com.tronxi.webfluxdemo.repository.mapper;

import com.tronxi.webfluxdemo.model.Demo;
import com.tronxi.webfluxdemo.repository.document.DemoDocument;
import org.springframework.stereotype.Component;

@Component
public class DemoMapper {

    public Demo toDomain(DemoDocument demoDocument) {
        return new Demo(demoDocument.getTitle());
    }

    public DemoDocument toDocument(Demo demo) {
        return new DemoDocument(demo.getTitle());
    }
}
