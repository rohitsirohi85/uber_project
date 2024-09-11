package com.uberApplication.uber.repository;

import com.uberApplication.uber.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uberApplication.uber.entities.Rider;

import java.util.Optional;

@Repository
public interface RiderRepo extends JpaRepository<Rider , Long> {

    Optional<Rider> findByUser(User user);
}
