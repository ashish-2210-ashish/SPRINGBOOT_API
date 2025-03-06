package com.example.Rider_Co.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name ="Drivers")
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverId;

    @Column(nullable = false)
    private double coordinateX;

    @Column(nullable = false)
    private double coordinateY;

    @Column(nullable = true)
    private boolean available=true;
}
