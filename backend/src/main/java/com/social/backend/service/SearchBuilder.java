package com.social.backend.service;

import com.social.backend.converter.OutputConverter;
import com.social.backend.enums.SocialMediaType;
import com.social.backend.exceptions.NotImplementedSocialMediaException;
import com.social.backend.service.adapters.FlickerAdapter;
import com.social.backend.service.adapters.InstagramAdapter;
import com.social.backend.service.adapters.TwitterAdapter;

public abstract class SearchBuilder implements SearchApi {


    public SearchApi build(SocialMediaType type) throws NotImplementedSocialMediaException {
        switch (type) {
            case TWITTER:
                return new TwitterAdapter();
            case INSTAGRAM:
                return new InstagramAdapter();
            case FLICKR:
                return new FlickerAdapter();
        }
        throw new NotImplementedSocialMediaException();
    }

    protected abstract OutputConverter getConverter();
}
