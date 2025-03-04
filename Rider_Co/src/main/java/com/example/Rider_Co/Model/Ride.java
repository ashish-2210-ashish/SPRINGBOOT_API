package com.example.Rider_Co.Model;

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
}
