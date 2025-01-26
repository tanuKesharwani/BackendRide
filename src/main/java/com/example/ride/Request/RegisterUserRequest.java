package com.example.ride.Request;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RegisterUserRequest {
    public static final String CREATE_RIDE_DETAILS = "/new/register";

    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    
}
