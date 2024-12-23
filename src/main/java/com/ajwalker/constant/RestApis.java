package com.ajwalker.constant;

public class RestApis {

	public static final String DOMAIN = "http://localhost:9090";

	private static final String VERSION = "/v1";
	private static final String API = "/api";
	private static final String DEVELOPER = "/dev";
	private static final String TEST = "/test";
	private static final String PROD = "/prod";
	
	private static final String ROOT = VERSION + DEVELOPER;
	
	public static final String USER = ROOT+ "/user";
	public static final String ADMIN = ROOT+ "/admin";
	public static final String PERSONAL = ROOT+ "/personal";
	public static final String MANAGER = ROOT+ "/manager";
	public static final String COMMENT = ROOT+ "/comment";
	public static final String COMPANY = ROOT+ "/company";

	public static final String REGISTER = "/register";
	public static final String AUTHMAIL = "/auth-mail";
	public static final String DOLOGIN = "/dologin";
	public static final String ADMINLOGIN = "/adminlogin";
	public static final String LISTCUSTOMER = "/list-customer";
	public static final String LISTUSERONWAIT = "/list-user-on-wait";
	public static final String USERAUTHORISATION = "/user-authorisation";
	public static final String FORGOT_PASSWORD_MAIL = "/auth-forgot-password";
	public static final String NEW_PASSWORD = "/new-password";
	public static final String GETALLCOMMENT = "/get-all-comment";
	public static final String GETUSERPROFILEINFO = "/get-user-profile-info";
	public static final String UPDATE_COMPANY= "/update-company";
	public static final String GETPERMITUSERLIST= "/get-user-permit-list";

}