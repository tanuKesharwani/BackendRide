package com.example.ride.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class PushNotificationService {

	@Autowired
	FirebaseMessaging firebaseMessagingInstance;

	// Method to send notification to a single token
	public void send(String token, String body) {
		try {
			Notification notification = Notification.builder().setTitle("Alert!").setBody(body).build();

			AndroidConfig androidConfig = AndroidConfig.builder().setPriority(AndroidConfig.Priority.HIGH).build();

			Aps aps = Aps.builder().setAlert(body).setSound("default").build();

			ApnsConfig apnsConfig = ApnsConfig.builder().setAps(aps).putHeader("apns-priority", "10").build();

			Message message = Message.builder().setToken(token).setAndroidConfig(androidConfig)
					.setApnsConfig(apnsConfig).setNotification(notification).build();

			firebaseMessagingInstance.send(message);
		} catch (FirebaseMessagingException e) {
			e.printStackTrace();
		}
	}

	// New method to send notification to a list of tokens
	public void sendToMultipleTokens(List<String> tokens, String body) {
		for (String token : tokens) {
			send(token, body); 
		}
	}
}
