package com.example.Rider_Co.repositories;

import com.example.Rider_Co.models.rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface riderRepository extends JpaRepository<rider,Integer> {
}
