package com.example.Rider_Co.Service;

import com.example.Rider_Co.Model.Driver;
import com.example.Rider_Co.Model.Ride;
import com.example.Rider_Co.Repository.DriverRepository;
import com.example.Rider_Co.Repository.RideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private RideRepository rideRepository;

    private  static final Logger logger= LoggerFactory.getLogger(DriverService.class);

    public List<Driver> GetAllDrivers() {
        logger.info("Displaying All Drivers From The Table");
        return driverRepository.findAll();
    }

    public Driver GetDriverByID(int driverId) {
        if (driverRepository.existsById(driverId)){
            logger.info("Displaying The Driver With ID : {}", driverId);
            return driverRepository.findById(driverId).orElse(new Driver());
        }else{
            logger.warn("Driver with Id : {} Doesn't Exist .",driverId);
            return new Driver();
        }
    }

    public String AddDriver(Driver driver) {
        driverRepository.save(driver);
        logger.info("Driver With Id : {} Addeded Successfully",driver.getDriverId());
        return "Successfully Added The Driver with ID : "+driver.getDriverId()+"\n\n";
    }

    public String UpdateDriver(Driver driver) {
        if(driverRepository.existsById(driver.getDriverId())){
            driverRepository.save(driver);
            logger.info("Driver With Id : {} Updated Successfully",driver.getDriverId());
            return "Successfully Updated The Driver With Id : "+driver.getDriverId()+"\n\n";
        }else{
            logger.warn("Driver with Id : {} Doesn't Exist And Cannot Be Updated.",driver.getDriverId());
            return "Driver with ID : "+driver.getDriverId()+" doesn't exist .. \n\n";
        }

    }

    public String DeleteDriver(int driverId) {
        if(driverRepository.existsById(driverId)){
            driverRepository.deleteById(driverId);
            logger.info("Driver With Id : {} Deleted Successfully",driverId);
            return "Successfully Deleted The Driver With ID : "+driverId+"\n\n";
        }else{
            logger.warn("Driver with Id : {} Doesn't Exist And Cannot Be Deleted.",driverId);
            return "Driver with ID : "+driverId+" doesn't exist .. \n\n";
        }
    }


    public String AcceptRide(int driverId,int rideId) {

        Ride selectedRide =rideRepository.findById(rideId).orElse(null);
        if (selectedRide==null){
            return "Ride is already assigned to someone else .";
        }
        selectedRide.setDriverId(driverId);
        rideRepository.save(selectedRide);


        Driver driver = driverRepository.findById(driverId).orElse(null);
        if (driver != null) {
            driver.setAvailable(false);
            driverRepository.save(driver);
        }

        return "RIDE_ACCEPTED " + rideId;

    }

    public List<Ride> getAvailableRidesForDriver(int driverId) {
        Driver driver = driverRepository.findById(driverId).orElse(null);
        if (driver == null) {
            return new ArrayList<>(); // Return empty if driver not found
        }

        double driverX = driver.getX();
        double driverY = driver.getY();

        List<Ride> availableRides = rideRepository.findAllUnassignedRide();

        // Sort rides by nearest distance
        for (int i = 0; i < availableRides.size(); i++) {
            for (int j = i + 1; j < availableRides.size(); j++) {
                Ride r1 = availableRides.get(i);
                Ride r2 = availableRides.get(j);

                double d1 = calculateDistance(driverX, driverY, r1.getStartX(), r1.getStartY());
                double d2 = calculateDistance(driverX, driverY, r2.getStartX(), r2.getStartY());

                if (d1 > d2) {
                    // Swap rides if they are not in order
                    Ride temp = availableRides.get(i);
                    availableRides.set(i, availableRides.get(j));
                    availableRides.set(j, temp);
                }
            }
        }

        return availableRides;
    }

    private double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
