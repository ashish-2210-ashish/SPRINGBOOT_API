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
    private int  rideId;

    @Column(nullable = false)
    private int riderId;

    @Column(nullable = false)
    private int driverId;

    @Column(nullable = false)
    private double startX;

    @Column(nullable = false)
    private double startY;

    @Column(nullable = true )
    private boolean isCompleted= false;
}
