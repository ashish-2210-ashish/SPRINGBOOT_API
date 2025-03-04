package com.example.Rider_Co.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  RideId;

    @Column(nullable = false)
    private int RiderId;

    @Column(nullable = false)
    private int DriverId;

    @Column(nullable = false)
    private double StartX;

    @Column(nullable = false)
    private double StartY;

    @Column(nullable = true )
    private boolean IsCompleted= false;
}
