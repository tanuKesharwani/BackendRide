package com.example.ride.serviceImpl;

import com.example.ride.Crud.RidesDetailsCrud;
import com.example.ride.Entity.RidesDetails;
import com.example.ride.Request.NearByReideRequest;
import com.example.ride.Response.NearByRideRespone;
import com.example.ride.service.NearByRideService;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.util.GenericException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class NearByRideServiceImpl implements NearByRideService {

    private final RidesDetailsCrud rideDetailsCrud;
    private static final double DEFAULT_RADIUS_KM = 10.0;
    private static final double METERS_PER_KM = 1000.0;

    @Override
    public GenericWebServiceResponse findNearbyRides(NearByReideRequest request) {
        try {
            log.info("Finding nearby rides for location: {}, {}", request.getLatitude(), request.getLongitude());

            // Validate request
            validateRequest(request);

            // Convert km to meters for MongoDB query
            double radiusInKm = request.getRadiusInKm() != null ? request.getRadiusInKm() : DEFAULT_RADIUS_KM;
            double radiusInMeters = radiusInKm * METERS_PER_KM;

            // Prepare coordinates for MongoDB query
            double[] coordinates = {request.getLongitude(), request.getLatitude()};

            // Find nearby rides
            List<RidesDetails> nearbyRides = rideDetailsCrud.findNearbyRides(coordinates, radiusInMeters);

            // Filter out user's own rides if userId provided
            if (request.getUserId() != null && !request.getUserId().isEmpty()) {
                nearbyRides = nearbyRides.stream()
                    .filter(ride -> !ride.getCreatedByUserId().equals(request.getUserId()))
                    .collect(Collectors.toList());
            }

            // Calculate distances and create response objects
            List<NearByRideRespone> responses = nearbyRides.stream()
                .map(ride -> NearByRideRespone.builder()
                    .ride(ride)
                    .distanceInKm(calculateDistance(
                        request.getLatitude(),
                        request.getLongitude(),
                        ride.getRideStartLocation().getLatitude(),
                        ride.getRideStartLocation().getLongitude()
                    ))
                    .build())
                .collect(Collectors.toList());

            return new GenericWebServiceResponse(true, "Nearby rides found", responses);

        } catch (Exception e) {
            log.error("Error finding nearby rides: ", e);
            throw new GenericException("Error finding nearby rides: " + e.getMessage());
        }
    }

    private void validateRequest(NearByReideRequest request) {
        if (request.getLatitude() == null || request.getLongitude() == null) {
            throw new IllegalArgumentException("Latitude and longitude are required");
        }
        if (request.getLatitude() < -90 || request.getLatitude() > 90) {
            throw new IllegalArgumentException("Invalid latitude value");
        }
        if (request.getLongitude() < -180 || request.getLongitude() > 180) {
            throw new IllegalArgumentException("Invalid longitude value");
        }
        if (request.getRadiusInKm() != null && request.getRadiusInKm() <= 0) {
            throw new IllegalArgumentException("Radius must be greater than 0");
        }
    }

    // Haversine formula to calculate distance between two points
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth's radius in kilometers

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}
