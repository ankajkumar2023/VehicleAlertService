package com.ank.strategy;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ank.service.Notification;
import com.ank.serviceimpl.EmailNotification;
import com.ank.serviceimpl.SmsNotification;

@Component
public class NotificationFactory {
	
//	@Autowired
//	private EmailNotification emailNotification;
//	
//	@Autowired
//	private SmsNotification smsNotification;
	
	private static HashMap<String,Notification> maps = new HashMap<>();
	
	
     
	@Autowired
	public NotificationFactory(EmailNotification emailNotification, SmsNotification smsNotification) {
		super();
		maps.put(NotificationType.EMAIL.name(), emailNotification);
		maps.put(NotificationType.SMS.name(), smsNotification);
	}



//	public NotificationFactory() {
//		maps.put(NotificationType.EMAIL.name(), emailNotification);
//		maps.put(NotificationType.SMS.name(), smsNotification);
//	}



	public static Notification getAlertInstace(String alertType) {
		
		return maps.get(alertType);
		
	}
}
