package com.social.media.messages.exceptions;

public class NotImplementedSocialMediaException extends Throwable {

    public NotImplementedSocialMediaException() {
        super("Selected Social Media Type is under the development");
    }
}
