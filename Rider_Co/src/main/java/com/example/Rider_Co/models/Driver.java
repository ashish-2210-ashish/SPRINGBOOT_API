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
    private double X;

    @Column(nullable = false)
    private double y;

    @Column(nullable = true)
    private boolean available=true;
}
