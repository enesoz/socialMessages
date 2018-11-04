package com.social.ms.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

@RestController
@Component
public class InstagramController {


    @RequestMapping(value = "/instagram/search/{keyword}", method = RequestMethod.GET)
    public Object search(@PathVariable(value = "keyword") String searched) {
        throw new ResourceAccessException("Not implemented yet");
    }
}