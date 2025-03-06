package com.example.Rider_Co.repositories;

import com.example.Rider_Co.models.ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface rideRepository extends JpaRepository<ride, Integer> {

    List<ride> findByRiderId(int riderId);

    List<ride> findByDriverId(int driverId);


    Optional<ride> findByRiderIdAndIsCompleted(int riderId, boolean isCompleted);


    @Query("SELECT r FROM Ride r WHERE r.driverId = 0 AND r.isCompleted = false ")
    List<ride> findAllUnassignedRide();
}

