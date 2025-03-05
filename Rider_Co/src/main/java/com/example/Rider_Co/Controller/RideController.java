package com.example.Rider_Co.Controller;

import com.example.Rider_Co.Model.Ride;
import com.example.Rider_Co.Service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ride")
public class RideController {
    @Autowired
    RideService rideService;

    @GetMapping
    public List<Ride> getAllRide() {
        return rideService.getAllRides();
    }

    @GetMapping("/{rideId}")
    public Ride getRideById(@PathVariable int rideId) {
        return rideService.getRideByID(rideId);
    }

    @PostMapping
    public String addRide(@RequestBody Ride ride) {
        return rideService.addRide(ride);
    }

    @PutMapping("/{rideId}")
    public String updateRide(@PathVariable int rideId, @RequestBody Ride ride) {
        ride.setRideId(rideId);
        rideService.updateRide(ride);
        return "Successfully updated the ride \n\n";
    }

    @DeleteMapping("/{rideId}")
    public String deleteRide(@PathVariable int rideId) {
        return rideService.deleteRide(rideId);
    }

    @PutMapping("/stop/{rideId}")
    public String stopRide(@PathVariable int rideId) {
        return rideService.stopRide(rideId);
    }

    @PutMapping("/cancel/{rideId}")
    public String cancelRide(@PathVariable int rideId) {
        return rideService.cancelRide(rideId);
    }


}
