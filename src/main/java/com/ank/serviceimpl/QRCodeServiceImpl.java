package com.ank.serviceimpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ank.exception.VehicleAlertException;
import com.ank.literals.VehicleAlertLiterals;
import com.ank.model.VehicleModel;
import com.ank.service.QRCodeService;
import com.ank.service.VehicleService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRCodeServiceImpl implements QRCodeService{
	
	@Autowired
	private VehicleService vehicleService;
	
	@Value("${qrcode.content.url}")
	private String notificationUrl;
	

	@Override
	public byte[] getQRCode(Long vehicleId,Long refNumber) throws IOException, WriterException {
		
		      validateVehicleExistInSystem(vehicleId, refNumber);
			
			  String url = new StringBuilder(notificationUrl).append(vehicleId).toString();
			  QRCodeWriter qrCodeWriter= new QRCodeWriter();
			  BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 400, 400);
			  ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		      MatrixToImageWriter.writeToStream(bitMatrix, "png", pngOutputStream);
		      return pngOutputStream.toByteArray();
	        
	}


	private void validateVehicleExistInSystem(Long vehicleId, Long refNumber) {
		VehicleModel vehicle=vehicleService.getVehicleByVehicleIdAndUserId(vehicleId, refNumber);
		if(vehicle.getVehicleId() == null) {
			throw new VehicleAlertException(VehicleAlertLiterals.INVALID_TOKEN_CODE,VehicleAlertLiterals.INVALID_TOKEN_OR_VEHILENOTEXIST);
		}
	}
	
	

}
