package com.social.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Component
public class InstagramController {

    private static final String API_URL_BASE = "https://api.instagram.com/v1/";
    @Autowired
    RestTemplate template;
    @Value("${instagram.clientID}")
    private String clientId;
    @Value("${instagram.clientSecret}")
    private String clientSecret;
    @Value("${instagram.authorize.url}")
    private String authorizeUrl;
    @Value("${instagram.access.token.url}")
    private String accessTokenUrl;

    @PostMapping(value = "/instagram/search/")
    public Object search(@RequestBody String searched) {
        OAuth2Template oAuth2Template = new OAuth2Template(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        URIBuilder searchUrl = URIBuilder.fromUri(API_URL_BASE + "/search").queryParam("access_token", oAuth2Template.authenticateClient().getAccessToken());
        return template.postForObject(searchUrl.build(), searched, String.class);
    }
}