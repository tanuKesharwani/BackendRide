package com.example.ride.pojo;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "coordinates")
public class GpsCoordinates {

	private BigDecimal latitude = BigDecimal.ZERO;
	private BigDecimal longitude = BigDecimal.ZERO;

}
