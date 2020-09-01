package com.tronxi.webfluxdemo.repository;

import com.tronxi.webfluxdemo.repository.document.DemoDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DemoRepository extends ReactiveMongoRepository<DemoDocument, String> {
}
