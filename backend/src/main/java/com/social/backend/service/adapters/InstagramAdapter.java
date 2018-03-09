package com.social.backend.service.adapters;

import com.social.backend.enums.SocialMediaType;
import com.social.backend.exceptions.NotImplementedSocialMediaException;
import com.social.backend.service.SearchAdapter;
import org.springframework.stereotype.Component;

@Component
public class InstagramAdapter extends SearchAdapter {


    @Override
    public String search(String searched) throws NotImplementedSocialMediaException {
        throw new NotImplementedSocialMediaException(SocialMediaType.INSTAGRAM);
    }

}