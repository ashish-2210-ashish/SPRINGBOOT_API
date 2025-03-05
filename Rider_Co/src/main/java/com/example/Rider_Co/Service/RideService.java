package com.example.Rider_Co.Service;

import com.example.Rider_Co.Model.Driver;
import com.example.Rider_Co.Model.Ride;
import com.example.Rider_Co.Repository.DriverRepository;
import com.example.Rider_Co.Repository.RideRepository;
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

    private static final Logger logger = LoggerFactory.getLogger(RideService.class);

    public List<Ride> getAllRides() {
        logger.info("Displaying all rides from the table");
        return rideRepository.findAll();
    }

    public Ride getRideByID(int rideId) {
        if (rideRepository.existsById(rideId)) {
            logger.info("Displaying the ride with ID: {}", rideId);
            return rideRepository.findById(rideId).orElse(new Ride());
        } else {
            logger.warn("Ride with ID: {} doesn't exist.", rideId);
            return new Ride();
        }
    }

    public String addRide(Ride ride) {
        rideRepository.save(ride);
        logger.info("Ride with ID: {} added successfully", ride.getRideId());
        return "Successfully added the ride with ID: " + ride.getRideId() + "\n\n";
    }

    public String updateRide(Ride ride) {
        if (rideRepository.existsById(ride.getRideId())) {
            rideRepository.save(ride);
            logger.info("Ride with ID: {} updated successfully", ride.getRideId());
            return "Successfully updated the ride with ID: " + ride.getRideId() + "\n\n";
        } else {
            logger.warn("Ride with ID: {} doesn't exist and cannot be updated.", ride.getRideId());
            return "Ride with ID: " + ride.getRideId() + " doesn't exist.\n\n";
        }
    }

    public String deleteRide(int rideId) {
        if (rideRepository.existsById(rideId)) {
            rideRepository.deleteById(rideId);
            logger.info("Ride with ID: {} deleted successfully", rideId);
            return "Successfully deleted the ride with ID: " + rideId + "\n\n";
        } else {
            logger.warn("Ride with ID: {} doesn't exist and cannot be deleted.", rideId);
            return "Ride with ID: " + rideId + " doesn't exist.\n\n";
        }
    }

    public String stopRide(int rideId) {
        Ride currentRide = rideRepository.findById(rideId).orElse(null);

        if (currentRide == null) {
            return "Ride " + rideId + " doesn't exist...\n";
        }

        if (currentRide.isCompleted()) {
            return "Ride " + rideId + " is already completed...\n";
        }
        currentRide.setCompleted(true);
        rideRepository.save(currentRide);

        int driverId = currentRide.getDriverId();
        if (driverId != 0) {
            Driver currentDriver = driverRepository.findById(driverId).orElse(null);
            if (currentDriver != null) {
                currentDriver.setAvailable(true);
                driverRepository.save(currentDriver);
            }
        }

        return "Ride " + rideId + " successfully stopped...\n";
    }

    public String cancelRide(int rideId) {
        Ride currentRide = rideRepository.findById(rideId).orElse(null);

        if (currentRide == null) {
            return "Ride " + rideId + " doesn't exist...\n";
        }

        if (currentRide.isCompleted()) {
            return "Ride " + rideId + " is already completed...\n";
        }


        int driverId = currentRide.getDriverId();
        if (driverId != 0) {
            Driver currentDriver = driverRepository.findById(driverId).orElse(null);
            if (currentDriver != null) {
                currentDriver.setAvailable(true);
                driverRepository.save(currentDriver);
            }
        }

        return deleteRide(rideId);
    }
}
