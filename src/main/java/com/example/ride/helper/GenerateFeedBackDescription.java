package com.example.ride.helper;

import com.example.ride.Entity.RidesDetails;



public class GenerateFeedBackDescription {

    public static String generateFeedDescription(RidesDetails ride) {
        StringBuilder description = new StringBuilder();
        
        switch (ride.getRideType()) {
            case SPONSORED:
                description.append("New Sponsored Ride! ")
                        .append(ride.getSponserDetails().getSponserName())
                        .append(" presents: ");
                break;
            case GROUP:
                description.append("Join our Group Ride! ")
                        .append("Looking for ")
                        .append(Integer.parseInt(ride.getMaxRiderAlllowed()) - 1)
                        .append(" riders. ");
                break;
            case COMPETITIVE:
                description.append("🏁 New Competitive Ride! ")
                        .append("Entry fee: ")
                        .append(ride.getRideFare())
                        .append(". ");
                break;
            case SOLO:
                description.append("Solo Ride planned! ");
                break;
            default:
                description.append("New Ride Alert! ");
        }

        description.append(ride.getRideName())
                .append("\n\n")
                .append(ride.getRideDetails())
                .append("\n\nStart: ")
                .append(ride.getRideStartLocation().getCity())
                .append("\nDate: ")
                .append(ride.getRideStartTime());

        return description.toString();
    }
}
