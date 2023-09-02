package com.ank.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ank.domain.Vehicle;
import com.ank.exception.VehicleAlertException;
import com.ank.literals.VehicleAlertLiterals;
import com.ank.model.NotificationModel;
import com.ank.repository.VehicleRepository;
import com.ank.service.Notification;
import com.ank.service.NotificationService;
import com.ank.strategy.NotificationFactory;
import com.ank.strategy.NotificationType;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Override
	public void sendNotification(NotificationModel notificationModel) {

		Vehicle vehicleDomain=vehicleRepository.getVehicleByVehicleId(notificationModel.getVehicleId());
	     checkVehicleExistInSystem(vehicleDomain);
	    //save acknowledge details in table with inprocess staus;
		List<Object> toList=new ArrayList<Object>();
		toList.add(vehicleDomain.getUser().getEmailId());
		Notification notification =NotificationFactory.getAlertInstace(NotificationType.EMAIL.name());
		notification.notifyUser(toList);
		//update acknowledge status as success;
		
	}

	@Override
	public void sendAcknowledgement(Long acknowledgementId) {
		
		// fetch userdetail and  notify to the user
		
		Notification notification =NotificationFactory.getAlertInstace(NotificationType.EMAIL.name());
		notification.notifyUser(null);
		
	}
	

	private void checkVehicleExistInSystem(Vehicle vehicleDomain) {
		if(vehicleDomain ==null ||VehicleAlertLiterals.INACTIVE.equalsIgnoreCase(vehicleDomain.getUser().getStatus())) {
			throw new VehicleAlertException(VehicleAlertLiterals.VEHICLE_NOT_EXIST_CODE,VehicleAlertLiterals.VEHICLE_NOT_EXIST_MSG);
		}
	}


}
