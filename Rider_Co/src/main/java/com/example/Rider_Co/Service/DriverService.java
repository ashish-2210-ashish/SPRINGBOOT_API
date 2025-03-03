package com.example.Rider_Co.Service;

import com.example.Rider_Co.Model.Driver;
import com.example.Rider_Co.Repository.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

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


}
