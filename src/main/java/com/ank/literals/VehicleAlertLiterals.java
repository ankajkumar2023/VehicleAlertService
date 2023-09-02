package com.ank.literals;

public class VehicleAlertLiterals {
	
	public static final String USER_SAVE_SUCCESS_MESSAGE="User added successfully.";
	
	public static final String USER_UPDATE_SUCCESS_MESSAGE="User updated successfully.";
	
	public static final String USER_DELETE_SUCCESS_MESSAGE="User deleted successfully.";
	
	public static final String VEHICLE_SAVE_SUCCESS_MESSAGE="Vehicle added successfully.";
	
	public static final String VEHICLE_UPDATE_SUCCESS_MESSAGE="Vehicle updated successfully.";
	
	public static final String VEHICLE_DELETE_SUCCESS_MESSAGE="Vehicle deleted successfully.";
	
	public static final String INVALID_TOKEN_OR_VEHILENOTEXIST= "Either Invalid Token or Vehicle does not Exist. ";
	
	public static final String USER_REFERENCE_ID="referenceNumber";
	
	public static final String INACTIVE="inacative";
	
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	public static final Integer GENERIC_CODE=1001;
	
	public static final Integer AUTHENTICATION_FAIL_CODE=1002;
	
	public static final Integer INVALID_TOKEN_CODE=1003;
	public static final String INVALID_TOKEN_MSG="Invalid Token";
	
	public static final Integer INVALID_USER_CODE=1004;
	public static final String INVALID_USER_MSG="Invalid User.";
	
	public static final Integer TOKEN_EXPIRED_CODE=1005;
	public static final String TOKEN_EXPIRED_MSG="Token has been expired.";
	
	public static final Integer TOKEN_SIGNATRE_ERROR_CODE=1006;
	public static final String TOKEN_SIGNATRE_ERROR_MSG="JWT signature does not match locally computed signature.";

	public static final Integer VEHICLE_NOT_EXIST_CODE=1007;
	public static final String  VEHICLE_NOT_EXIST_MSG="Vehicle does not exist in system.";
	
	public static final Integer USER_NOT_EXIST_CODE=1008;
	public static final String  USER_NOT_EXIST_MSG="User does not exist in system.";
	
	public static final Integer DB_ERROR_CODE=1009;
	public static final String  DB_ERROR__MSG="DB exception occured.";
	
	

}
