package com.ank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ank.model.NotificationModel;
import com.ank.service.NotificationService;

@RestController
@RequestMapping("/ank/notification")
@CrossOrigin("*")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@PostMapping
	public String sendNotification(@RequestBody NotificationModel notificationModel) {
		System.out.println("Notification request recieved for  :- "+"***"+notificationModel.getLocation());
		notificationService.sendNotification(notificationModel);
		return "Email Notification sent successfully";
	}
	
	@GetMapping(value = "/acknowledgement/{id}")
	public String sendAcknowledgement(@PathVariable String acknowledgementId) {
		System.out.println("Acknowledgement request recieved  :- "+acknowledgementId);
		notificationService.sendAcknowledgement(Long.valueOf(acknowledgementId));
		return "Notification sent successfully";
	}

}
