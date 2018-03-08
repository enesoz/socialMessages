package com.social.backend.enums;

public enum SocialMediaType {

    TWITTER, INSTAGRAM, FLICKR;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
