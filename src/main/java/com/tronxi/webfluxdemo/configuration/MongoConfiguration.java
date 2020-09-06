package com.tronxi.webfluxdemo.configuration;

import com.tronxi.webfluxdemo.repository.DemoRepository;
import com.tronxi.webfluxdemo.repository.document.DemoDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MongoConfiguration {

    private final DemoRepository demoRepository;
    @Bean
    public void setData() {
        List<DemoDocument> demoDocumentList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            demoDocumentList.add(new DemoDocument(String.valueOf(i)));
        }

        Flux.fromIterable(demoDocumentList)
                .delayElements(Duration.ofMillis(100))
                .subscribe(dc -> demoRepository.insert(dc).subscribe());
    }
}
