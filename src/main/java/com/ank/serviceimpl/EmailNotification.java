package com.ank.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.ank.service.Notification;

@Component
public class EmailNotification implements Notification{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public void notifyUser(List<Object> to) {
		
	 SimpleMailMessage mailMessage = new SimpleMailMessage();

     // Setting up necessary details
     mailMessage.setFrom(sender);
     mailMessage.setTo("ankaj2019@gmail.com");
     mailMessage.setText("Your vehicle parking has blocked Someone ,He is waiting to you !!! ");
     mailMessage.setSubject("Vehicle Parking Alert");

     // Sending the mail
       javaMailSender.send(mailMessage);
       System.out.println("Mail Sent Successfully...");
	}
	
	

	

}
