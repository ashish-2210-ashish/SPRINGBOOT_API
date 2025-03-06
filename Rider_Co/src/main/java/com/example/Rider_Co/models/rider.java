package com.example.Rider_Co.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "Riders")
@Entity
public class rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int RiderId;
    @Column(nullable = false)
    double X;
    @Column(nullable = false)
    double Y;
}
