package com.example.Rider_Co.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "Riders")
@Entity
public class Rider {
    @Id
    int RiderId;
    @Column(nullable = false)
    double X;
    @Column(nullable = false)
    double Y;
}
