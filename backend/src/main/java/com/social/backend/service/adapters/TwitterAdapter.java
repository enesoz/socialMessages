package com.social.backend.service.adapters;

import com.social.backend.enums.SocialMediaType;
import com.social.backend.service.SearchAdapter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@Configurable
public class TwitterAdapter extends SearchAdapter {

    private RestTemplate restTemplate;

    public TwitterAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public String search(String searched) throws Exception {
        try {
            URI messagesService = URI.create(String.format("http://%s:%s/%s/%s", MESSAGE_SERVICE, MESSAGE_SERVICE_PORT, SocialMediaType.TWITTER.toString(), "search/" + searched));
            return restTemplate.getForObject(messagesService, String.class);
        } catch (Exception e) {
            throw e;
        }
    }

}
