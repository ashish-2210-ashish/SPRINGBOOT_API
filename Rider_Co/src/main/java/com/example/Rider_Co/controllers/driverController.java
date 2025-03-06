package com.example.Rider_Co.controllers;

import com.example.Rider_Co.models.driver;
import com.example.Rider_Co.models.ride;
import com.example.Rider_Co.services.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class driverController {

    private static final Logger logger = LoggerFactory.getLogger(driverController.class);

    @Autowired
    private DriverService driverService;

    /**
     * Retrieves a list of all drivers.
     * @return List of drivers
     */
    @GetMapping
    public List<driver> getAllDrivers() {
        logger.info("Fetching all drivers");
        return driverService.GetAllDrivers();
    }

    /**
     * Retrieves a driver by their ID.
     * @param driverId ID of the driver
     * @return Driver object
     */
    @GetMapping("/{driverId}")
    public driver getDriverById(@PathVariable int driverId) {
        logger.info("Fetching driver with ID: {}", driverId);
        return driverService.GetDriverByID(driverId);
    }

    /**
     * Adds a new driver to the system.
     * @param driver Driver object to add
     * @return Success message
     */
    @PostMapping
    public String addDriver(@RequestBody driver driver) {
        logger.info("Adding new driver: {}", driver);
        return driverService.AddDriver(driver);
    }

    /**
     * Updates driver details.
     * @param driverId ID of the driver to update
     * @param driver Updated driver details
     * @return Success message
     */
    @PutMapping("/{driverId}")
    public String updateDriver(@PathVariable int driverId, @RequestBody driver driver) {
        logger.info("Updating driver with ID: {}", driverId);
        driver.setDriverId(driverId);
        driverService.UpdateDriver(driver);
        return "Successfully updated the driver";
    }

    /**
     * Deletes a driver from the system.
     * @param driverId ID of the driver to delete
     * @return Success message
     */
    @DeleteMapping("/{driverId}")
    public String deleteDriver(@PathVariable int driverId) {
        logger.info("Deleting driver with ID: {}", driverId);
        return driverService.DeleteDriver(driverId);
    }

    /**
     * Allows a driver to accept a ride.
     * @param driverId ID of the driver accepting the ride
     * @param rideId ID of the ride being accepted
     * @return Success message
     */
    @PostMapping("/acceptRide/{driverId}/{rideId}")
    public String acceptRide(@PathVariable int driverId, @PathVariable int rideId) {
        logger.info("Driver with ID {} is accepting ride with ID {}", driverId, rideId);
        return driverService.AcceptRide(driverId, rideId);
    }

    /**
     * Retrieves available rides for a specific driver.
     * @param driverId ID of the driver
     * @return List of available rides
     */
    @GetMapping("/availableRides/{driverId}")
    public List<ride> getAvailableRides(@PathVariable int driverId) {
        logger.info("Fetching available rides for driver with ID: {}", driverId);
        return driverService.getAvailableRidesForDriver(driverId);
    }
}
