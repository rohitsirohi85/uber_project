package com.uberApplication.uber.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(indexes = {
        @Index(name = "idx_rating_rider" , columnList = "rider_id"),
        @Index(name = "idx_rating_driver" , columnList = "driver_id")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Ride ride;
    @ManyToOne
    private Rider rider;
    @ManyToOne
    private Driver driver;

    private Integer driverRating; // rating  for driver
    private Integer riderRating;   // rating for rider
}
