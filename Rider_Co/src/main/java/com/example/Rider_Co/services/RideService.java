package com.example.Rider_Co.services;

import com.example.Rider_Co.models.Ride;
import com.example.Rider_Co.repositories.DriverRepository;
import com.example.Rider_Co.repositories.RideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DriverService driverService;

    private static final Logger logger = LoggerFactory.getLogger(RideService.class);

    public List<Ride> getAllRides() {
        logger.info("Fetching all rides from the database");
        return rideRepository.findAll();
    }

    public Ride getRideByID(int rideId) {
        return rideRepository.findById(rideId)
                .orElseGet(() -> {
                    logger.warn("Ride with ID {} not found.", rideId);
                    return new Ride();
                });
    }




    public String deleteRide(int rideId) {
        if (!rideRepository.existsById(rideId)) {
            logger.warn("Ride with ID: {} does not exist, deletion failed.", rideId);
            return "Ride with ID: " + rideId + " does not exist.";
        }

        rideRepository.deleteById(rideId);
        logger.info("Ride with ID: {} deleted successfully", rideId);
        return "Successfully deleted ride with ID: " + rideId;
    }

    public String stopRide(int rideId, double timeTaken) {
        Ride currentRide = rideRepository.findById(rideId).orElse(null);
        if (currentRide == null) {
            logger.warn("Ride {} not found, cannot stop.", rideId);
            return "Ride " + rideId + " does not exist.";
        }

        if (currentRide.isCompleted()) {
            logger.warn("Ride {} is already completed.", rideId);
            return "Ride " + rideId + " is already completed.";
        }

        currentRide.setCompleted(true);
        currentRide.setTimeTaken(timeTaken);
        rideRepository.save(currentRide);

        // Free up the driver
        releaseDriver(currentRide.getDriverId());

        logger.info("Ride {} stopped successfully.", rideId);
        return "Ride " + rideId + " successfully stopped.";
    }

    public String cancelRide(int rideId) {
        Ride currentRide = rideRepository.findById(rideId).orElse(null);
        if (currentRide == null) {
            logger.warn("Ride {} not found, cannot cancel.", rideId);
            return "Ride " + rideId + " does not exist.";
        }

        if (currentRide.isCompleted()) {
            logger.warn("Ride {} is already completed, cannot cancel.", rideId);
            return "Ride " + rideId + " is already completed.";
        }

        releaseDriver(currentRide.getDriverId());
        logger.info("Ride {} canceled successfully.", rideId);

        return deleteRide(rideId);
    }

    public String billRide(int rideId) {
        Ride currentRide = rideRepository.findById(rideId).orElse(null);
        if (currentRide == null) {
            logger.warn("Ride {} not found, cannot generate bill.", rideId);
            return "Ride " + rideId + " does not exist.";
        }

        if (currentRide.getRideFare() != 0) {
            logger.info("Ride {} already has a fare calculated: Rs. {}", rideId, currentRide.getRideFare());
            return "Total fare of the ride " + rideId + " is Rs. " + currentRide.getRideFare();
        }

        double distance = calculateDistance(
                currentRide.getStartX(), currentRide.getStartY(),
                currentRide.getEndX(), currentRide.getEndY()
        );
        double timeFare = 2 * currentRide.getTimeTaken();
        double distanceFare = 6.5 * distance;
        double baseFare = 50;
        double totalFare = (baseFare + distanceFare + timeFare) * 1.2; // Including 20% tax

        currentRide.setRideFare(totalFare);
        rideRepository.save(currentRide);

        logger.info("Ride {} billed successfully. Total fare: Rs. {}", rideId, totalFare);
        return "Total fare of the ride " + rideId + " is Rs. " + totalFare;
    }

    private void releaseDriver(int driverId) {
        if (driverId == 0) return;

        driverRepository.findById(driverId).ifPresent(driver -> {
            driver.setAvailable(true);
            driverRepository.save(driver);
            logger.info("Driver {} marked as available.", driverId);
        });
    }

    private double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public List<Ride> getAllRidesByRider(int riderId) {
        logger.info("Fetching all rides for rider {}", riderId);
        return rideRepository.findByRiderId(riderId);
    }

    public List<Ride> getAllRidesByDriver(int driverId) {
        logger.info("Fetching all rides for driver {}", driverId);
        return rideRepository.findByDriverId(driverId);
    }
}
