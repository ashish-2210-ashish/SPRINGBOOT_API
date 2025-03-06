package com.example.Rider_Co.services;

import com.example.Rider_Co.models.ride;
import com.example.Rider_Co.models.rider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RiderService {

    @Autowired
    private com.example.Rider_Co.repositories.riderRepository riderRepository;

    @Autowired
    private com.example.Rider_Co.repositories.rideRepository rideRepository;

    private static final Logger logger = LoggerFactory.getLogger(RiderService.class);

    /**
     * Fetches all riders from the database.
     * @return List of riders.
     */
    public List<rider> getAllRiders() {
        logger.info("Fetching all riders from the database");
        return riderRepository.findAll();
    }

    /**
     * Retrieves a rider by their ID.
     * @param riderId The ID of the rider.
     * @return Rider object or an empty Rider if not found.
     */
    public rider getRiderByID(int riderId) {
        return riderRepository.findById(riderId)
                .orElseGet(() -> {
                    logger.warn("Rider with ID: {} not found", riderId);
                    return new rider();
                });
    }

    /**
     * Adds a new rider to the system.
     * @param rider The Rider object to add.
     * @return Status message.
     */
    public String addRider(rider rider) {
        riderRepository.save(rider);
        logger.info("Rider with ID: {} added successfully", rider.getRiderId());
        return "Successfully added the rider with ID: " + rider.getRiderId();
    }

    /**
     * Updates an existing rider's details.
     * @param rider The Rider object with updated information.
     * @return Status message.
     */
    public String updateRider(rider rider) {
        if (!riderRepository.existsById(rider.getRiderId())) {
            logger.warn("Rider with ID: {} not found. Update failed.", rider.getRiderId());
            return "Rider with ID: " + rider.getRiderId() + " does not exist.";
        }

        riderRepository.save(rider);
        logger.info("Rider with ID: {} updated successfully", rider.getRiderId());
        return "Successfully updated the rider with ID: " + rider.getRiderId();
    }

    /**
     * Deletes a rider by their ID.
     * @param riderId The ID of the rider to delete.
     * @return Status message.
     */
    public String deleteRider(int riderId) {
        if (!riderRepository.existsById(riderId)) {
            logger.warn("Rider with ID: {} not found. Deletion failed.", riderId);
            return "Rider with ID: " + riderId + " does not exist.";
        }

        riderRepository.deleteById(riderId);
        logger.info("Rider with ID: {} deleted successfully", riderId);
        return "Successfully deleted the rider with ID: " + riderId;
    }

    /**
     * Matches a rider with available drivers based on their start and destination coordinates.
     * @param riderId The ID of the rider.
     * @param endX Destination X-coordinate.
     * @param endY Destination Y-coordinate.
     * @return Status message.
     */
    public String matchDrivers(int riderId, double endX, double endY) {
        rider rider = riderRepository.findById(riderId).orElse(null);
        if (rider == null) {
            logger.warn("Matching failed: Rider with ID {} not found.", riderId);
            return "RIDER_NOT_FOUND";
        }

        // Check if the rider already has an ongoing ride
        Optional<ride> existingRide = rideRepository.findByRiderIdAndIsCompleted(riderId, false);
        if (existingRide.isPresent()) {
            logger.warn("Rider with ID: {} already has an active ride.", riderId);
            return "RIDE_ALREADY_EXISTS_FOR_RIDER " + riderId;
        }

        // Create a new ride request
        ride ride = new ride();
        ride.setDriverId(0); // Initially unassigned
        ride.setRiderId(riderId);
        ride.setStartX(rider.getX());
        ride.setStartY(rider.getY());
        ride.setEndX(endX);
        ride.setEndY(endY);
        ride.setRideFare(0);
        ride.setTimeTaken(0);
        ride.setCompleted(false);

        rideRepository.save(ride);
        logger.info("Ride created for rider ID: {} with destination ({}, {})", riderId, endX, endY);
        return "Successfully added the ride for rider " + riderId;
    }
}
