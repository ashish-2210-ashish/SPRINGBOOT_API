package com.example.Spring_Boot_demo_0;


import org.springframework.stereotype.Component;

@Component
public class Pen implements  Writer {

    @Override
    public void write() {
        System.out.println("writing with pen.....");

    }
}