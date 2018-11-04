package com.social.backend.service;

import com.social.backend.exceptions.NotImplementedSocialMediaException;

public interface SearchApi {

    // return JSON value
    String search(String searched) throws NotImplementedSocialMediaException, Exception;

}
