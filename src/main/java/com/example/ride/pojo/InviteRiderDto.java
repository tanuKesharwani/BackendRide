package com.example.ride.pojo;

import java.util.Date;
import java.util.UUID;

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
public class InviteRiderDto {

	private Long inviteId;

	private Date createdDate;

	private UUID createdByUserId;

	private String inviteStatusId;
	
}
