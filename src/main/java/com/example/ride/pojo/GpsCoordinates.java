package com.example.ride.pojo;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonRootName(value = "coordinates")
public class GpsCoordinates {
    
    @Field("address")
    private String address = "";
    
    @Field("city")
    private String city = "";
    
    @Field("latitude")
    private double latitude =0;
    
    @Field("longitude")
    private double longitude = 0;

    // Required GeoJSON fields
    @Field("type")
    private final String type = "Point";
    
    @Field("coordinates")
    private double[] coordinates;

    // This will be called before saving to MongoDB
    public void initializeGeoJson() {
        this.coordinates = new double[]{
            longitude,latitude
		};
    }

    // Convenience method to create a GeoJsonPoint
    public GeoJsonPoint toGeoJsonPoint() {
        return new GeoJsonPoint(
            longitude,latitude
        );
    }
}