package com.social.backend.exceptions;

import com.social.backend.enums.SocialMediaType;

public class NotImplementedSocialMediaException extends Throwable {

    public NotImplementedSocialMediaException(SocialMediaType mediaType) {
        super(String.format("{0}  is under the development", mediaType));
    }
}
