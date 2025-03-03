package com.example.Rider_Co.Repository;

import com.example.Rider_Co.Model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider,Integer> {
}
