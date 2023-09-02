package com.ank.service;

import com.ank.model.NotificationModel;

public interface NotificationService {
	
	public void sendNotification( NotificationModel notificationModel);
	
	public void sendAcknowledgement(Long acknowledgementId);

}
