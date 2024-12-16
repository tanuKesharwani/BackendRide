package com.example.ride.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImpl {

	@Autowired
	private UserRegistrationCrud repositoryCrud;

	public void saveEntity() {
		UserRegistration entity = UserRegistration.builder().name("tanu").age(21).build();

		repositoryCrud.save(entity);
	}

}
