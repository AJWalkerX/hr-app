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
	public static final String WORKHOLIDAY = ROOT+ "/work-holiday";
	public static final String SALARY = ROOT+ "/salary";
	public static final String PERSONAL_SPENDING = ROOT+ "/personal-spending";
	public static final String SHIFT = ROOT+ "/shift";
	public static final String EMBEZZLEMENT = ROOT+ "/embezzlement";
	

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
	public static final String CREATE_HOLIDAY= "/create-holiday";
	public static final String MANAGER_EMPLOYEES = "/employees";
	public static final String PERMIT_AUTHORIZATION =  "/permit-authorization";
	public static final String USER_INFORMATION =  "/user-information";
	public static final String UPDATE_EMPLOYEE =  "/employee-update-information";
	public static final String USER_UPDATE_INFORMATION ="/user-update-information";
	public static final String ADD_NEW_EMPLOYEE ="/add-new-employee";
	public static final String GETALLVIEWUSERPERMIT ="/get-all-view-user-permit";
	public static final String ADD_COMMENT ="/add-comment";
	public static final String DELETE_EMPLOYEE ="/delete-employee";
	public static final String GETUSERALLCOMMENT = "/comment-list";
	public static final String ADD_PERSONAL_SPENDING = "/add-personal-spending";
	public static final String GET_PERSONAL_SPENDINGS = "/get-personal-spendings";
	public static final String GETCOMMENTDETAILS = "/get-comment-details";
	public static final String MANAGER_EMPLOYEES_SPENDING ="/manager-employees-spending";
	public static final String CREATE_SHIFT ="/create-shift";
	public static final String ASSIGN_SHIFT ="/assign-shift";
	public static final String SPENDING_AUTHORIZATION =  "/spending-authorization";
	public static final String LIST_SHIFT =  "/list-shift";
	public static final String MY_SHIFTS =  "/list-my-shift";
	public static final String UPDATE_SHIFT =  "/update-shift";
	public static final String DELETE_SHIFT =  "/delete-shift";
	public static final String ADD_EMBEZZLEMENT =  "/add-embezzlement";
	public static final String GET_EMBEZZLEMENT_LIST =  "/get-embezzlement-list";
	public static final String ASSIGMENT_EMBEZZLEMENT=  "/assignment-embezzlement";
	public static final String GET_EMBEZZLEMENT_DETAILS=  "/get-embezzlement-details/{embezzlementId}";
}