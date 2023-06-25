package com.me.portal;

public class RestURL {
	public static final String IP = "http://192.168.1.41";
	public static final String PORT_1 = ":7075";
	public static final String login = IP+PORT_1+"/rest/me/v1/login";
	public static final String getUserDetails = IP+PORT_1+"/rest/me/v1/userDetails";
	public static final String registerUser = IP+PORT_1+"/rest/me/v1/registerUser";
	public static final String addTransaction = IP+PORT_1+"/rest/me/v1/addTransaction";
	public static final String getTransaction = IP+PORT_1+"/rest/me/v1/getTransaction";


}
