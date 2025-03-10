package com.example.Rider_Co.controllers;

import com.example.Rider_Co.models.Ride;
import com.example.Rider_Co.serviceInterfaces.RideServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ride")
public class RideController {

    private static final Logger logger = LoggerFactory.getLogger(RideController.class);

    @Autowired
    private RideServiceInterface rideService;

    /**
     * Retrieves a list of all rides.
     * @return List of all rides
     */
    @GetMapping
    public List<Ride> getAllRide() {
        logger.info("Fetching all rides");
        return rideService.getAllRides();
    }

    /**
     * Retrieves all rides associated with a specific rider.
     * @param riderId ID of the rider
     * @return List of rides for the rider
     */
//    @GetMapping("/rider/{riderId}")
//    public List<Ride> getAllRideByRider(@PathVariable int riderId) {
//        logger.info("Fetching all rides for rider with ID: {}", riderId);
//        return rideService.getAllRidesByRider(riderId);
//    }

    /**
     * Retrieves all rides associated with a specific driver.
     * @param driverId ID of the driver
     * @return List of rides for the driver
     */
//    @GetMapping("/driver/{driverId}")
//    public List<Ride> getAllRideByDriver(@PathVariable int driverId) {
//        logger.info("Fetching all rides for driver with ID: {}", driverId);
//        return rideService.getAllRidesByDriver(driverId);
//    }

    /**
     * Retrieves a ride by its ID.
     * @param rideId ID of the ride
     * @return Ride object
     */
    @GetMapping("/{rideId}")
    public Ride getRideById(@PathVariable int rideId) {
        logger.info("Fetching ride with ID: {}", rideId);
        return rideService.getRideByID(rideId);
    }


    /**
     * Deletes a ride.
     * @param rideId ID of the ride to delete
     * @return Success message
     */
    @DeleteMapping("/{rideId}")
    public String deleteRide(@PathVariable int rideId) {
        logger.info("Deleting ride with ID: {}", rideId);
        return rideService.deleteRide(rideId);
    }

    /**
     * Stops a ride and records the time taken.
     * @param rideId ID of the ride to stop
     * @param timeTaken Time taken for the ride
     * @return Success message
     */
    @PutMapping("/stop/{rideId}")
    public String stopRide(@PathVariable int rideId, @RequestBody double timeTaken) {
        logger.info("Stopping ride with ID: {} and time taken: {}", rideId, timeTaken);
        return rideService.stopRide(rideId, timeTaken);
    }

    @PutMapping("/start/{driverId}/{rideId}")
    public String startRide(@PathVariable int driverId,@PathVariable int rideId) {
        logger.info("Starting the  ride with ID: {} by Driver with ID : {} ..", rideId,driverId);
        return rideService.startRide(driverId,rideId);
    }

    /**
     * Generates the bill for a ride.
     * @param rideId ID of the ride to bill
     * @return Bill details
     */
    @GetMapping("/bill/{rideId}")
    public String billRide(@PathVariable int rideId) {
        logger.info("Generating bill for ride with ID: {}", rideId);
        return rideService.billRide(rideId);
    }

    /**
     * Cancels a ride.
     * @param rideId ID of the ride to cancel
     * @return Success message
     */
    @PutMapping("/cancel/{rideId}")
    public String cancelRide(@PathVariable int rideId) {
        logger.info("Cancelling ride with ID: {}", rideId);
        return rideService.cancelRide(rideId);
    }
}
