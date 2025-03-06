package com.example.Rider_Co.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ride {
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

    @Column(nullable = false)
    private double endX;

    @Column(nullable = false)
    private double endY;

    @Column(nullable = true, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isCompleted= false;

    @Column(nullable = false)
    private double rideFare;

    @Column(nullable = false)
    private double timeTaken;
}
