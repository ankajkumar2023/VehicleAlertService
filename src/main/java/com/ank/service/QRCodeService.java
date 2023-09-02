package com.ank.service;

import java.io.IOException;

import com.google.zxing.WriterException;

public interface QRCodeService {

	public byte[] getQRCode(Long vehicleID,Long refNumber)  throws IOException,WriterException;
}
