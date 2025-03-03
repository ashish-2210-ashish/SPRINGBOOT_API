package com.example.Rider_Co.Service;

import com.example.Rider_Co.Model.Rider;
import com.example.Rider_Co.Repository.RiderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RiderService {

    @Autowired
    private RiderRepository riderRepository;

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
}
