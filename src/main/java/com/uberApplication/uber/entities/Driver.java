package com.uberApplication.uber.entities;




import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(indexes = {
        @Index(name = "idx_driver_vehicle_id" , columnList = "vehicleId")
})
public class Driver {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double rating;

     private Boolean available;

     private String vehicleId;

     @Column(columnDefinition = "Geometry(Point, 4326)")   // only work when you have jts core and hibernate spatial dependencies
     private  Point currentLocation;
}
