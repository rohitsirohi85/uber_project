package com.uberApplication.uber.utils;

import com.uberApplication.uber.DTO.PointDto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

// making a utils class which is helpful to convert coordinates to point
public class GeometryUtils {
    public static Point createPoint(PointDto pointDto){
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel() , 4326);
        Coordinate coordinate = new Coordinate(pointDto.getCoordinates()[0] ,pointDto.getCoordinates()[1]);
        return geometryFactory.createPoint(coordinate);
        // so here we convert the coordinates to point
    }
}
