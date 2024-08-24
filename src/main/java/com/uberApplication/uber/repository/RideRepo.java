package com.uberApplication.uber.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.entities.Ride;
import com.uberApplication.uber.entities.Rider;
@Repository
public interface RideRepo extends JpaRepository<Ride ,Long>{

    Page<Ride> findByRider(Rider rider, Pageable pageRequest);

    Page<Ride> findByDriver(Driver driver, Pageable pageRequest);
    
}
