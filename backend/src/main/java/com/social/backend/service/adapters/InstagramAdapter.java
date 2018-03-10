package com.social.backend.service.adapters;

import com.social.backend.enums.SocialMediaType;
import com.social.backend.exceptions.NotImplementedSocialMediaException;
import com.social.backend.service.SearchAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InstagramAdapter extends SearchAdapter {

    private RestTemplate restTemplate;

    public InstagramAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String search(String searched) throws NotImplementedSocialMediaException {
        throw new NotImplementedSocialMediaException(SocialMediaType.INSTAGRAM);
    }

}
