package com.social.media.messages.exception;

public class AuthenticationException extends Exception {

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(final String message) {
        super(message);
    }

    public AuthenticationException(final String message, final Throwable t) {
        super(message, t);
    }

    public AuthenticationException(final Throwable t) {
        super(t);
    }
}
