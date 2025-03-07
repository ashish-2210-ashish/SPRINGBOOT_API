package com.example.Rider_Co.controllers;

import com.example.Rider_Co.dto.MatchRequestDto;
import com.example.Rider_Co.models.Rider;
import com.example.Rider_Co.serviceInterfaces.RiderServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rider")
public class RiderController {

    private static final Logger logger = LoggerFactory.getLogger(RiderController.class);

    @Autowired
    private RiderServiceInterface riderService;

    /**
     * Retrieves all riders.
     * @return List of riders
     */
    @GetMapping
    public List<Rider> getAllRiders() {
        logger.info("Fetching all riders");
        return riderService.getAllRiders();
    }

    /**
     * Retrieves a rider by ID.
     * @param riderId ID of the rider
     * @return Rider object
     */
    @GetMapping("/{riderId}")
    public Rider getRiderById(@PathVariable int riderId) {
        logger.info("Fetching rider with ID: {}", riderId);
        return riderService.getRiderByID(riderId);
    }

    /**
     * Adds a new rider to the system.
     * @param rider Rider object to add
     * @return Success message
     */
    @PostMapping
    public String addRider(@RequestBody Rider rider) {
        logger.info("Adding new rider: {}", rider);
        return riderService.addRider(rider);
    }

    /**
     * Updates rider details.
     * @param riderId ID of the rider to update
     * @param rider Updated rider details
     * @return Success message
     */
    @PutMapping("/{riderId}")
    public String updateRider(@PathVariable int riderId, @RequestBody Rider rider) {
        logger.info("Updating rider with ID: {}", riderId);
        rider.setRiderId(riderId);
        riderService.updateRider(rider);
        return "Successfully updated the rider";
    }

    /**
     * Deletes a rider from the system.
     * @param riderId ID of the rider to delete
     * @return Success message
     */
    @DeleteMapping("/{riderId}")
    public String deleteRider(@PathVariable int riderId) {
        logger.info("Deleting rider with ID: {}", riderId);
        return riderService.deleteRider(riderId);
    }

    /**
     * Matches a rider with available drivers based on destination coordinates.
     * @param riderId ID of the rider
     * @param matchRequestDto Request body containing destination coordinates
     * @return Match result message
     */
    @PostMapping(value = "/match/{riderId}", consumes = "application/json")
    public String matchDrivers(@PathVariable int riderId, @RequestBody MatchRequestDto matchRequestDto) {
        double endX = matchRequestDto.getDestinationCoordinateX();
        double endY = matchRequestDto.getDestinationCoordinateY();
        logger.info("Matching drivers for rider ID: {} with destination coordinates ({}, {})", riderId, endX, endY);
        return riderService.matchDrivers(riderId, endX, endY);
    }
}
