package com.tronxi.webfluxdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Demo {

    private String title;

    public Demo(String title) {
        try {
            if(title.equals("hola"))
                Thread.sleep(3000);
            if(title.equals("tal"))
                Thread.sleep(2000);
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
