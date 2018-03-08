package com.social.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Component
@RestController
public class SocialMessageController {

    private static final String MESSAGE_SERVICE = "messages";
    private static final int MESSAGE_SERVICE_PORT = 2222;

    @Autowired
    RestTemplate restTemplate;


    @CrossOrigin
    @RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET)
    public String search(@PathVariable(value = "keyword") String searched) {
        try {
            URI messagesService = URI.create(String.format("http://%s:%s/%s", MESSAGE_SERVICE, MESSAGE_SERVICE_PORT, "search/" + searched)); // ... do something with the URI } }
            return restTemplate.getForObject(messagesService, String.class);
        } catch (HttpServerErrorException e) {
            log.error("Message-Microservis throw error", e);
            return e.getResponseBodyAsString();
        }
    }
}


