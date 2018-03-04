package com.social.media.messages.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.social.media.messages.BackendApplication.MESSAGES_MS_URL;


@Component
@RestController
public class SocialMessageController {

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET)
    public String search(@PathVariable(value = "keyword") String search) {
        return restTemplate.getForObject(MESSAGES_MS_URL + "/search/" + search, String.class);
    }
}
