package com.example.JPA_DEMO.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Student {
    @Id
    private int rollNo;
    private String name;
    private String gender;
    private String field;
}
