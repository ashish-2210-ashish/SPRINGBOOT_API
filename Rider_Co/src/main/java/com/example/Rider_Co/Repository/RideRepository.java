package com.example.Rider_Co.Repository;

import com.example.Rider_Co.Model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RideRepository extends JpaRepository<Ride, Integer> {

    Optional<Ride> findByRiderIdAndIsCompleted(int riderId, boolean isCompleted);

    @Query("SELECT r FROM Ride r WHERE r.driverId = 0 AND r.isCompleted = false ORDER BY r.startX ASC, r.startY ASC")
    Ride findNearestUnassignedRide();
}

