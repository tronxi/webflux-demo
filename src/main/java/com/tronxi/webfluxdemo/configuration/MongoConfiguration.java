package com.tronxi.webfluxdemo.configuration;

import com.tronxi.webfluxdemo.repository.DemoRepository;
import com.tronxi.webfluxdemo.repository.document.DemoDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MongoConfiguration {

    private final DemoRepository demoRepository;
    @Bean
    public void setData() {
        for(int i = 0; i < 10; i++) {
            demoRepository.insert(new DemoDocument(String.valueOf(i))).subscribe();
        }
    }
}
