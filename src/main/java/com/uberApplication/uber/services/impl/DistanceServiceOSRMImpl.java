package com.uberApplication.uber.services.impl;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import com.uberApplication.uber.services.DistanceService;

@Service
public class DistanceServiceOSRMImpl implements DistanceService{

    @Override
    public double calculateDistance(Point src, Point des) {
        // we will call the third party api
        throw new UnsupportedOperationException("Unimplemented method 'calculateDistance'");
    }
    
}
