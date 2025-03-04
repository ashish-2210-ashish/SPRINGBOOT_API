package com.example.Rider_Co.Service;

import com.example.Rider_Co.Model.Driver;
import com.example.Rider_Co.Model.Rider;
import com.example.Rider_Co.Repository.DriverRepository;
import com.example.Rider_Co.Repository.RiderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RiderService {

    @Autowired
    private RiderRepository riderRepository;

    @Autowired
    private DriverRepository driverRepository;

    private static final Logger logger = LoggerFactory.getLogger(RiderService.class);

    public List<Rider> getAllRiders() {
        logger.info("Displaying all riders from the table");
        return riderRepository.findAll();
    }

    public Rider getRiderByID(int riderId) {
        if (riderRepository.existsById(riderId)) {
            logger.info("Displaying the rider with ID: {}", riderId);
            return riderRepository.findById(riderId).orElse(new Rider());
        } else {
            logger.warn("Rider with ID: {} doesn't exist.", riderId);
            return new Rider();
        }
    }

    public String addRider(Rider rider) {
        riderRepository.save(rider);
        logger.info("Rider with ID: {} added successfully", rider.getRiderId());
        return "Successfully added the rider with ID: " + rider.getRiderId() + "\n\n";
    }

    public String updateRider(Rider rider) {
        if (riderRepository.existsById(rider.getRiderId())) {
            riderRepository.save(rider);
            logger.info("Rider with ID: {} updated successfully", rider.getRiderId());
            return "Successfully updated the rider with ID: " + rider.getRiderId() + "\n\n";
        } else {
            logger.warn("Rider with ID: {} doesn't exist and cannot be updated.", rider.getRiderId());
            return "Rider with ID: " + rider.getRiderId() + " doesn't exist.\n\n";
        }
    }

    public String deleteRider(int riderId) {
        if (riderRepository.existsById(riderId)) {
            riderRepository.deleteById(riderId);
            logger.info("Rider with ID: {} deleted successfully", riderId);
            return "Successfully deleted the rider with ID: " + riderId + "\n\n";
        } else {
            logger.warn("Rider with ID: {} doesn't exist and cannot be deleted.", riderId);
            return "Rider with ID: " + riderId + " doesn't exist.\n\n";
        }
    }

    public List<Integer> getNearestDrivers(int riderId) {
        Rider rider = riderRepository.findById(riderId).orElse(null);
        if (rider == null) {
            return new ArrayList<>();
        }

        double riderX = rider.getX();
        double riderY = rider.getY();


        List<Driver> availableDrivers = driverRepository.findByavailable(true);


        List<Integer> nearestDrivers = new ArrayList<>();
        double[] distances = new double[5];

        for (Driver driver : availableDrivers) {
            double distance = calculateDistance(riderX, riderY, driver.getX(), driver.getY());

            if (distance <= 5) {
                insertDriverSorted(nearestDrivers, distances, driver.getDriverId(), distance);
            }
        }

        if (nearestDrivers.isEmpty()) {
            System.out.println("NO_DRIVERS_AVAILABLE");
            return nearestDrivers;
        }

        System.out.print("DRIVERS_MATCHED ");
        for (int driverId : nearestDrivers) {
            System.out.print(driverId + " ");
        }
        System.out.println();

        return nearestDrivers;
    }


    private void insertDriverSorted(List<Integer> driverList, double[] distances, int driverId, double distance) {
        for (int i = 0; i < 5; i++) {
            if (driverList.size() < i + 1 || distance < distances[i] || (distance == distances[i] && driverId < driverList.get(i))) {
                driverList.add(i, driverId);
                distances[i] = distance;
                if (driverList.size() > 5) {
                    driverList.remove(5);
                }
                return;
            }
        }
    }

    private double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }


}
