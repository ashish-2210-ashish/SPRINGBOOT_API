package com.example.Rider_Co.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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
    @ColumnDefault("0")
    private int driverId;

    @Column(nullable = false)
    private double startX;

    @Column(nullable = false)
    private double startY;

    @Column(nullable = false)
    private double endX;

    @Column(nullable = false)
    private double endY;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isRideAccepted;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isCompleted = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RideStatus status=RideStatus.AVAILABLE_RIDE;

    @Column(nullable = false)
    private double rideFare;

    @Column(nullable = false)
    private double timeTaken;
}
