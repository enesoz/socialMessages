package com.social.ms.controller;

public enum InstagramServiceEnum {

    USERS_SELF("https://api.instagram.com/v1/users/self/"),

    USER_SELF_MEDIA_RECENT("https://api.instagram.com/v1/users/self/media/recent/");

    private String url;

    InstagramServiceEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
