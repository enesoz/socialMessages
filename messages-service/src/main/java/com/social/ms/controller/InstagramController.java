package com.social.ms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/instagram")
@Slf4j
public class InstagramController {

    private static final String API_URL_BASE = "https://api.instagram.com/v1/";
    private static final String REDIRECT_URI = "http://localhost:2222/instagram/auth/callback";
    private static final String AUTHORIZATION_REDIRECT_URI = "http://localhost:2222/instagram/access";

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
    @PostMapping(value = "/authorize")
    public URI authorize() {
        URIBuilder authorizeUrl = URIBuilder.fromUri(this.authorizeUrl).queryParam("client_id", clientId)
                .queryParam("redirect_uri", REDIRECT_URI)
                .queryParam("response_type", "code");
        return authorizeUrl.build();
    }

    public void getAccessToken(String code) throws Exception {

        URIBuilder accessTokenUrl = URIBuilder.fromUri(this.accessTokenUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON, MediaType.ALL));


        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.add("client_id", clientId);
        request.add("client_secret", clientSecret);
        request.add("grant_type", "authorization_code");
        request.add("redirect_uri", REDIRECT_URI);
        request.add("code", code);
        request.add("Content-Type", "application/x-www-form-urlencoded");


        HttpEntity<MultiValueMap<String, String>> requestEnty = new HttpEntity<>(request, headers);
        try {
            ResponseEntity<String> response = template.postForEntity(accessTokenUrl.build(), request, String.class);
            this.access_token = String.valueOf(new GsonJsonParser().parseMap(response.getBody()).get("access_token"));
        } catch (HttpStatusCodeException e) {
            ResponseEntity<String> body = ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
            throw new Exception(body.toString());
        }

    }

    @GetMapping(value = "/search/{searched}")
    public Object search(@PathVariable(name = "searched") String searched) {

        URIBuilder searchUrl = URIBuilder.fromUri(API_URL_BASE + "self/media/recent/").queryParam("access_token", access_token);
        return template.postForObject(searchUrl.build(), searched, String.class);
    }

    @RequestMapping(value = "/auth/callback", method = RequestMethod.GET)
    public String redirect(@RequestParam Map<String, String> allParams) throws Exception {
        getAccessToken(allParams.get("code"));
        return "You can close this tab";
    }

    @RequestMapping(value = "/access", method = RequestMethod.POST)
    public String access_token(@RequestBody String access) {
        this.access_token = access;
        log.info("Access_Token : {}", access);
        return access;
    }
}