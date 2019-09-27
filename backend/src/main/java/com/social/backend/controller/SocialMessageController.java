package com.social.backend.controller;

import com.social.backend.enums.SocialMediaType;
import com.social.backend.exceptions.NotImplementedSocialMediaException;
import com.social.backend.service.SearchApi;
import com.social.backend.service.SearchBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;
import java.util.Map;

@Slf4j
@Component
@RestController
public class SocialMessageController {

    @Autowired
    RestTemplate template;

    @CrossOrigin
    @PostMapping(value = "/search", produces = "application/json", consumes = "application/json")
    public String search(@RequestBody Map<String, String> body) throws NotImplementedSocialMediaException, Exception {
        try {
            String type = body.get("type");
            SearchApi searchApi = SearchBuilder.build(SocialMediaType.valueOf(type.toUpperCase(Locale.ENGLISH)), template);
            String searched = body.get("searched");
            return searchApi.search(searched);
        } catch (Exception e) {
            return e.getMessage();
        }

    }

}


