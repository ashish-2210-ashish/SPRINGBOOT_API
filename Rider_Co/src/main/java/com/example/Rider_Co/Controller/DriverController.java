package com.example.Rider_Co.Controller;

import com.example.Rider_Co.Model.Driver;
import com.example.Rider_Co.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driver_service;

    @GetMapping
    public List<Driver> getAllDrivers() {
        return driver_service.GetAllDrivers();
    }

    @GetMapping("/{driverId}")
    public Driver getDriverById(@PathVariable int driverId) {
        return driver_service.GetDriverByID(driverId);
    }

    @PostMapping
    public String addDriver(@RequestBody Driver driver) {
        return driver_service.AddDriver(driver);
    }

    @PutMapping("/{driverId}")
    public String updateDriver(@PathVariable String driverId ,@RequestBody Driver driver) {
        driver.setDriverId(Integer.parseInt(driverId));
        driver_service.UpdateDriver(driver);
        return "Successfully updated the driver \n\n";
    }

    @DeleteMapping("/{driverId}")
    public String deleteDriver(@PathVariable int driverId) {
        return driver_service.DeleteDriver(driverId);

    }
}
