package com.social.ms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.Map;

@RestController
@Component
public class InstagramController {

    private static final String API_URL_BASE = "https://api.instagram.com/v1/";
    private static final String REDIRECT_URI = "http://localhost:2222/redirect";
    private static final String AUTHORIZATION_REDIRECT_URI = "http://localhost:2222/access";

    RestTemplate template = new RestTemplate();

    @Value("${instagram.clientID}")
    private String clientId;
    @Value("${instagram.clientSecret}")
    private String clientSecret;
    @Value("${instagram.authorize.url}")
    private String authorizeUrl;
    @Value("${instagram.access.token.url}")
    private String accessTokenUrl;
    private String access_token;
    private String code;

    @CrossOrigin
    @PostMapping(value = "/instagram/authorize")
    public URI authorize() {
        URIBuilder authorizeUrl = URIBuilder.fromUri(this.authorizeUrl).queryParam("client_id", clientId)
                .queryParam("redirect_uri", "http://localhost:2222/redirect")
                .queryParam("response_type", "code");
        return authorizeUrl.build();
    }

    public void getAccessToken(String code) {

        URIBuilder accessTokenUrl = URIBuilder.fromUri(this.accessTokenUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON, MediaType.ALL));


        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.add("client_id", clientId);
        request.add("client_secret", clientSecret);
        request.add("grant_type", "authorization_code");
        request.add("redirect_uri", AUTHORIZATION_REDIRECT_URI);
        request.add("code", code);
        request.add("Content-Type", "application/x-www-form-urlencoded");


        HttpEntity<MultiValueMap<String, String>> requestEnty = new HttpEntity<>(request, headers);

        template.postForEntity(accessTokenUrl.build(), request, String.class);
    }

    @PostMapping(value = "/instagram/search/")
    public Object search(@RequestBody String searched) {

        URIBuilder searchUrl = URIBuilder.fromUri(API_URL_BASE + "/self/media/recent").queryParam("access_token", access_token);
        return template.postForObject(searchUrl.build(), searched, String.class);
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public void redirect(@RequestParam Map<String, String> allParams) throws JsonProcessingException {
        System.out.println("Something Happening:" + allParams);
        getAccessToken(allParams.get("code"));
    }

    @RequestMapping(value = "/access", method = RequestMethod.POST)
    public String access_token(@RequestBody String access) {
        this.access_token = access;
        System.out.println(access);
        return access;
    }
}