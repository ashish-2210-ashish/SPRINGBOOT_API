package com.example.Rider_Co.Controller;

import com.example.Rider_Co.Dto.MatchRequestDto;
import com.example.Rider_Co.Model.Rider;
import com.example.Rider_Co.Service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rider")
public class RiderController {

    @Autowired
    private RiderService riderService;

    @GetMapping
    public List<Rider> getAllRiders() {
        return riderService.getAllRiders();
    }

    @GetMapping("/{riderId}")
    public Rider getRiderById(@PathVariable int riderId) {
        return riderService.getRiderByID(riderId);
    }

    @PostMapping
    public String addRider(@RequestBody Rider rider) {
        return riderService.addRider(rider);
    }

    @PutMapping("/{riderId}")
    public String updateRider(@PathVariable int riderId, @RequestBody Rider rider) {
        rider.setRiderId(riderId);
        riderService.updateRider(rider);
        return "Successfully updated the rider \n\n";
    }

    @DeleteMapping("/{riderId}")
    public String deleteRider(@PathVariable int riderId) {
        return riderService.deleteRider(riderId);
    }

    @PostMapping(value = "/match/{riderId}", consumes = "application/json")
    public String matchDrivers(@PathVariable int riderId, @RequestBody MatchRequestDto matchRequestDto){
        double endX = matchRequestDto.getEndX();
        double endY = matchRequestDto.getEndY();
        return riderService.matchDrivers(riderId,endX,endY);
    }
}
