package com.tronxi.webfluxdemo.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class Demo {

    private String title;

    public Demo(String title) {
        this.title = title;
    }

    public Demo process() {
        try {
            if(title.equals("5"))
                Thread.sleep(2000);
            if(title.equals("a"))
                Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

}
