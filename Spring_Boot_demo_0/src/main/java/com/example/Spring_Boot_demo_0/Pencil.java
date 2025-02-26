package com.example.Spring_Boot_demo_0;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Pencil implements  Writer {

    @Override
    public void write() {
        System.out.println("writing with pencil ....");

    }
}
