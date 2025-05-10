package com.example.ride.config;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@Configuration
public class FireBaseConfig {

	@Bean
	FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
		return FirebaseMessaging.getInstance(firebaseApp);
	}

	@Bean
	FirebaseApp firebaseApp(GoogleCredentials credentials) {
		FirebaseOptions options = FirebaseOptions.builder().setCredentials(credentials).build();
		return FirebaseApp.initializeApp(options);
	}

	@Bean
	GoogleCredentials googleCredentials() {
		try {
			ClassPathResource resource = new ClassPathResource("google-service-account.json");
			InputStream inputStream = resource.getInputStream();
			return GoogleCredentials.fromStream(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
