package com.social.backend.service;


public abstract class SearchAdapter implements SearchApi {

    protected static final String MESSAGE_SERVICE = "messages";
    protected static final int MESSAGE_SERVICE_PORT = 2222;

    public void print() {
        System.out.println("beni ara");
    }

}
