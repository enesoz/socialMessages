package com.social.ms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;

@Slf4j
public class InstagramService extends OAuth2Template {


    @Value("${instagram.clientSecret}")
    private static String clientSecret;
    @Value("${instagram.clientID}")
    private static String clientId;
    @Value("${instagram.authorize.url}")
    private static String authorizeUrl;
    @Value("${instagram.access.token.url}")
    private static String accessToken;


    public InstagramService() {
        super(clientId, clientSecret, authorizeUrl, accessToken);
    }

    @Override
    public AccessGrant authenticateClient() {
        return super.authenticateClient();
    }
}
