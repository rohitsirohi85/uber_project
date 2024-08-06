package com.uberApplication.uber.configs;

import com.uberApplication.uber.DTO.PointDto;
import com.uberApplication.uber.utils.GeometryUtils;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfigs {
    
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper= new ModelMapper();
       // define the mapper to convert pointDto to point , so it can use coordinates in locations
        mapper.typeMap(PointDto.class , Point.class).setConverter(context ->{
            PointDto pointDto = context.getSource();
            return GeometryUtils.createPoint(pointDto);
        });
        // now make another to convert the point to pointDto as well
        mapper.typeMap(Point.class , PointDto.class).setConverter(context->{
            Point point = context.getSource();
            double coordinates[]={
                    point.getX(),
                    point.getY()
            };
            return  new PointDto(coordinates);
        });
        return  mapper;
    }
}
