package com.social.backend.service.adapters;

import com.social.backend.enums.SocialMediaType;
import com.social.backend.service.SearchAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class TwitterAdapter extends SearchAdapter {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String search(String searched) {
        URI messagesService = URI.create(String.format("http://%s:%s/%s", MESSAGE_SERVICE, MESSAGE_SERVICE_PORT, SocialMediaType.INSTAGRAM.toString(), "search/" + searched));
        return restTemplate.getForObject(messagesService, String.class);
    }

}
