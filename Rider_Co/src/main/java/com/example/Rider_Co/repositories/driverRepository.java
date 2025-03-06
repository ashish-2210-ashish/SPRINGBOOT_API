package com.example.Rider_Co.repositories;

import com.example.Rider_Co.models.driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface driverRepository extends JpaRepository<driver,Integer> {

}
