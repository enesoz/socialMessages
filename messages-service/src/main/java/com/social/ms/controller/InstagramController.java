package com.social.ms.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.social.ms.model.InstagramBrief;
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

import java.io.IOException;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/instagram")
@Slf4j
public class InstagramController {

    private static final String REDIRECT_URI = "http://localhost:2222/instagram/auth/callback";

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
                .queryParam("response_type", "code").queryParam("scope", "basic");
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

    @GetMapping("/all")
    public List<InstagramBrief> getAll() throws IOException {

        URIBuilder searchUrl = URIBuilder.fromUri(InstagramServiceEnum.USER_SELF_MEDIA_RECENT.getUrl()).queryParam("access_token", access_token);
        String forObject = template.getForObject(searchUrl.build(), String.class);
        List<InstagramBrief> retVal = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(forObject);
        JsonNode data = jsonNode.get("data");
        data.elements().forEachRemaining(a -> {
            retVal.add(InstagramBrief.createInstagramBrief(a.get("id").asText(), a.get("images").get("standard_resolution").get("url").asText(), a.get("caption").isNull() ? "" : a.get("caption").get("text").asText(""), new Date(a.get("created_time").asLong()), a.get("link").asText(), a.get("likes").get("count").asInt()));
        });
        return retVal;
    }

    @GetMapping(value = "/search/{searched}")
    public Object search(@PathVariable(name = "searched") String searched) throws IOException {
        // Due to instagram odd access rule for now get all pic for account
        return getAll();
    }

    @RequestMapping(value = "/auth/callback", method = RequestMethod.GET)
    public String redirect(@RequestParam Map<String, String> allParams) throws Exception {
        getAccessToken(allParams.get("code"));
        return "window.close();";
    }

}