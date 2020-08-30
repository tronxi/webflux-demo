package com.tronxi.webfluxdemo.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Demo {

    private String title;

    public Demo(String title) {
        try {
            if(Long.parseLong(title) % 2 == 0)
                Thread.sleep((long) (Math.random() * 7) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       this.title = title;
    }

    @Override
    public String toString(){
        return "Title: " + title + "</br>";
    }

}
