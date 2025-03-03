package com.example.Rider_Co.Repository;

import com.example.Rider_Co.Model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride,Integer> {
}
