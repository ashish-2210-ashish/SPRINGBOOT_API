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

    @GetMapping("/rider/{riderId}")
    public List<Ride> getAllRideByRider(@PathVariable int riderId) {
        return rideService.getAllRidesByRider(riderId);
    }

    @GetMapping("/driver/{driverId}")
    public List<Ride> getAllRideByDriver(@PathVariable int driverId) {
        return rideService.getAllRidesByDriver(driverId);
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
    public String stopRide(@PathVariable int rideId,@RequestBody double timeTaken) {
        return rideService.stopRide(rideId,timeTaken);
    }

    @GetMapping("/bill/{rideId}")
    public String billRide(@PathVariable int rideId) {
        return rideService.billRide(rideId);
    }

    @PutMapping("/cancel/{rideId}")
    public String cancelRide(@PathVariable int rideId) {
        return rideService.cancelRide(rideId);
    }






}
