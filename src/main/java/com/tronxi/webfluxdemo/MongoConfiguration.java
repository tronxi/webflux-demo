package com.tronxi.webfluxdemo;

import com.tronxi.webfluxdemo.repository.DemoRepository;
import com.tronxi.webfluxdemo.repository.document.DemoDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MongoConfiguration {

    private final DemoRepository demoRepository;
    @Bean
    public void setData() {
        List<DemoDocument> demoDocumentList = Arrays.asList(
                new DemoDocument("a"),
                new DemoDocument("5"),
                new DemoDocument("b"),
                new DemoDocument("b"),
                new DemoDocument("5"),
                new DemoDocument("c"),
                new DemoDocument("c")
        );
        demoDocumentList.forEach(dc -> demoRepository.insert(dc).subscribe());
    }
}
