package com.example.ride.pojo.Enums;


public enum RideTypes {
    LOCAL("LOCAL"),
    DESTINATION("DESTINATION"),
    COUPLE("COUPLE"),
    SPRITIUAL("SPRITIUAL"),
    SOLO("SOLO"),
    GROUP("GROUP"),
    SPONSORED("SPONSORED"),
    CASUAL("CASUAL"),
    COMPETITIVE("COMPETITIVE");

    private String value;

    RideTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RideTypes fromValue(String value) {
        for (RideTypes type : RideTypes.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown RideType value: " + value);
    }

}
