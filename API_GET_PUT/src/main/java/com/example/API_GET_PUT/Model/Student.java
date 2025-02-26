package com.example.API_GET_PUT.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private  int rno;
    private String Name;
    private int age;
    private String Fav_Subject;
}
