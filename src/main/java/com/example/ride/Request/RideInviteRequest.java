package com.example.ride.Request;

import java.util.List;

import com.google.auto.value.AutoValue.Builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RideInviteRequest {
    public static final String INVITE_TO_JOIN_RIDE = "/ride/invite";

  
   List<String>invitedUserId;
   String groupId;
   String rideId;
}
