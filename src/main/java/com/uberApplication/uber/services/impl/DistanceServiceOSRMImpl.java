package com.uberApplication.uber.services.impl;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.uberApplication.uber.services.DistanceService;

import lombok.Data;

@Service
public class DistanceServiceOSRMImpl implements DistanceService{
    
    private static final String OSRM_API_BASE_URL = "https://router.project-osrm.org/route/v1/driving/";

    @Override
    public double calculateDistance(Point src, Point des) {
        // we call the third party api
        String uri=src.getX()+","+src.getY()+";"+des.getX()+","+des.getY();  // making the way to use uri
        try{
            OSRMResponseDto responseDto =  RestClient.builder()
            .baseUrl(OSRM_API_BASE_URL)
            .build()
            .get()
            .uri(uri)
            .retrieve()
            .body(OSRMResponseDto.class);
            return responseDto.getRoutes().get(0).getDistance()/1000.0;  // here we getting the routes..inside routes we get the distance in meter which we convert in km by /1000
        }
        catch(Exception e){
         throw new RuntimeException("error getting data from OSRM"+e.getMessage());
        }
      
    }
    
}

@Data
class OSRMResponseDto{
    private List<OSRMRoute> routes;  // defining the routes from api
}

@Data
class OSRMRoute{  // making routes class so we can decide what we want in the routes
  private double distance;  // defining that want distance from the api inside routes we ca define more according to our choice from api
}
