package com.ajwalker.constant;

public class MailApis {
//    "/v1/dev/user/auth-mail?auth="
    public static final String VERIFICATION = "Email Authentication";
    public static final String VERIFICATION_PASSWORD = "Email Authentication";
    public static final String VERIFICATION_LINK = RestApis.DOMAIN + RestApis.USER + RestApis.AUTHMAIL + "?auth=";
    public static final String NEW_PASSWORD_LINK = RestApis.DOMAIN + RestApis.USER + RestApis.NEW_PASSWORD + "?auth=";
    public static final String NEW_PASSWORD_VERIFICATION_LINK = RestApis.DOMAIN + RestApis.USER + RestApis.NEW_PASSWORD + "?auth=";
}
