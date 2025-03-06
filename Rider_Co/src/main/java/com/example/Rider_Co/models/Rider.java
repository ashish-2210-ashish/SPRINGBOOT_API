package com.example.Rider_Co.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "Riders")
@Entity
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int riderId;
    @Column(nullable = false)
    double X;
    @Column(nullable = false)
    double Y;
}
