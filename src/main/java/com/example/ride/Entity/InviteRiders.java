package com.example.ride.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "invite_rider")
public class InviteRiders implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long inviteId; // Long ID to be generated explicitly

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date createdDate;

	@Field(name = "created_by_userId")
	private UUID createdByUserId;

	@Field(name = "invite_status")
	private String inviteStatus;

}
