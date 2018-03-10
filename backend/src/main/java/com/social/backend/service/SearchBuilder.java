package com.social.backend.service;

import com.social.backend.enums.SocialMediaType;
import com.social.backend.exceptions.NotImplementedSocialMediaException;
import com.social.backend.service.adapters.FlickerAdapter;
import com.social.backend.service.adapters.InstagramAdapter;
import com.social.backend.service.adapters.TwitterAdapter;
import org.springframework.web.client.RestTemplate;

public abstract class SearchBuilder implements SearchApi {


    public static SearchApi build(SocialMediaType type, RestTemplate template) throws NotImplementedSocialMediaException {
        switch (type) {
            case TWITTER:
                return new TwitterAdapter(template);
            case INSTAGRAM:
                return new InstagramAdapter(template);
            case FLICKR:
                return new FlickerAdapter(template);
        }
        throw new NotImplementedSocialMediaException(type);
    }

}
