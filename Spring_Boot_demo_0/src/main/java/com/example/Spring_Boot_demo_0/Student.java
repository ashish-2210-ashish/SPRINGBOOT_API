package com.example.Spring_Boot_demo_0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Student {
    @Autowired
    Pen pen;
    int age;

    @Autowired
//    @Qualifier("pencil")
    Writer writer;



    @Autowired
    public void setPen(Pen pen) {
        System.out.println("setter is called ...");
        this.pen = pen;
    }

    public void show(){
        System.out.println("showing student details ....");
    }


    public void Write_Exam(){
        writer.write();
    }

}
