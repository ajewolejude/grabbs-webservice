package com.boca.grabswebservice.security;

public class SecurityConstants {

    public static final String SIGN_UP_URLS = "/api/**";
    public static final String H2_URL = "/h2/**";
    public static final String REG_URL = "/register/**";
    public static final String LOGIN_URL = "/login/**";
    public static final String HELLO_URL = "/hello/**";
    public static final String SWAGGER_URL = "/swagger-ui.html";
    public static final String SECRET ="SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX= "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 300_000; //30 seconds
}
