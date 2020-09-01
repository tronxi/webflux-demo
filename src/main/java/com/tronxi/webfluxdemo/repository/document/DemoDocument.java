package com.tronxi.webfluxdemo.repository.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class DemoDocument {

    @Id
    private String id;

    private String title;

    public DemoDocument(String title) {
        this.title = title;
    }
}
