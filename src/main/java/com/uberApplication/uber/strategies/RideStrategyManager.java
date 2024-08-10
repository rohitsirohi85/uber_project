package com.uberApplication.uber.strategies;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.uberApplication.uber.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.uberApplication.uber.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.uberApplication.uber.strategies.impl.RideFareSurgePricingFareCalculationStrategy;
import com.uberApplication.uber.strategies.impl.RiderFareDefaultFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {
    private final DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy;
    private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;
    private final RiderFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){
        if (riderRating>4.8) {
            return highestRatedDriverStrategy;
        }else{
            return nearestDriverStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
        // we will define the surge pricing at a surge time we will create a surge time on that time we will use surge pricing
        // surge time = 6pm to 9 pm
        LocalTime surgeStartTime=LocalTime.of(18, 0);
        LocalTime surgeEndTime=LocalTime.of(21, 0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if (isSurgeTime) {
            return surgePricingFareCalculationStrategy;
        } else {
            return defaultFareCalculationStrategy;
        }
    }
}
