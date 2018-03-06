package com.social.media.messages.controller;

import com.social.ms.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@Component
@RestController
public class SocialMessageController {

    private static final String MESSAGE_SERVICE = "messages";
    private static final int MESSAGE_SERVICE_PORT = 2222;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @CrossOrigin
    @RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET)
    public SearchResult search(@PathVariable(value = "keyword") String searched) {
        URI messagesService = URI.create(String.format("http://%s:%s/%s", MESSAGE_SERVICE, MESSAGE_SERVICE_PORT, "search/" + searched)); // ... do something with the URI } }
        return restTemplate.getForObject(messagesService, SearchResult.class);
    }
}


