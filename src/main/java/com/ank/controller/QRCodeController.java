package com.ank.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ank.security.JwtToken;
import com.ank.service.QRCodeService;
import com.google.zxing.WriterException;

@RestController
@RequestMapping("/ank/qrcode")
@CrossOrigin("*")
public class QRCodeController {
	
	@Autowired
	private QRCodeService qRCodeService;
	
	@Autowired
	private JwtToken jwtToken;
	
	
	@GetMapping(value = "/{vehicleId}")
	public  HttpEntity<byte[]>  getQRCode(HttpServletRequest request,@PathVariable Long vehicleId) throws IOException, WriterException {
		
		Long refNumber =jwtToken.getUserRefNumberFromToken(request.getHeader("Authorization").substring(7));
		    
		    byte [] qrCode = qRCodeService.getQRCode(vehicleId,refNumber);
            HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_PNG);
	        headers.setContentLength(qrCode.length);
	        return new HttpEntity<byte[]>(qrCode, headers);
		
		
	}
	

}
