package com.example.ride.pojo.Enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum PremiumTypes {
    MONTHLY("monthly"),
    YEARLY("Yearly"),
    DAILY("Daily");

    private String value;

    PremiumTypes(String value)
    {
        this.value = value;
    }
    public String getValue()
    {
        return value;
    }
    public static PremiumTypes fromValue(String value) {
        for (PremiumTypes type : PremiumTypes.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown Premium Type value: " + value);
    }
}
