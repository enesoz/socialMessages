package com.social.backend.enums;

import java.util.Locale;

public enum SocialMediaType {

    TWITTER, INSTAGRAM, FLICKR;

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
