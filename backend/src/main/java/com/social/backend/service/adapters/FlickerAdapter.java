package com.social.backend.service.adapters;

import com.social.backend.enums.SocialMediaType;
import com.social.backend.exceptions.NotImplementedSocialMediaException;
import com.social.backend.service.SearchApi;
import org.springframework.stereotype.Component;

@Component
public class FlickerAdapter implements SearchApi {


    @Override
    public String search(String searched) throws NotImplementedSocialMediaException {
        throw new NotImplementedSocialMediaException(SocialMediaType.FLICKR);
    }

}
