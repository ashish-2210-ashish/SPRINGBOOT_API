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
    private DriverService driverService;

    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.GetAllDrivers();
    }

    @GetMapping("/{driverId}")
    public Driver getDriverById(@PathVariable int driverId) {
        return driverService.GetDriverByID(driverId);
    }

    @PostMapping
    public String addDriver(@RequestBody Driver driver) {
        return driverService.AddDriver(driver);
    }

    @PutMapping("/{driverId}")
    public String updateDriver(@PathVariable int driverId ,@RequestBody Driver driver) {
        driver.setDriverId(driverId);
        driverService.UpdateDriver(driver);
        return "Successfully updated the driver \n\n";
    }

    @DeleteMapping("/{driverId}")
    public String deleteDriver(@PathVariable int driverId) {
        return driverService.DeleteDriver(driverId);

    }
}
