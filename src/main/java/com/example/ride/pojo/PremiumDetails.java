package com.example.ride.pojo;

import com.example.ride.pojo.Enums.PremiumTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PremiumDetails {
    
    private PremiumTypes premiumType;
    private String purcahedAt;
    private String ExpiredAt;
    private String transactionRef;

}
