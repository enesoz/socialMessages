package com.social.ms.controller;

import com.social.ms.model.SearchResult;
import com.social.ms.service.TwitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import javax.annotation.PostConstruct;

@RestController
@Component
@Slf4j
public class TwitterController {

    TwitterTemplate twitterTemplate;
    @Autowired
    TwitterService service;
    @Value("${twitter.consumer.key}")
    private String consumerKey;
    @Value("${twitter.consumer.secret}")
    private String consumerSecret;

    private boolean isConnectionAvaliable;

    @PostConstruct
    public void init() {
        try {
            twitterTemplate = new TwitterTemplate(consumerKey, consumerSecret);
            isConnectionAvaliable = true;
        } catch (ResourceAccessException e) {
            isConnectionAvaliable = false;
            log.error("Twitter Api is not avaliable now ");
        }
    }

    @PostMapping(value = "/twitter/search")
    public SearchResult search(@RequestBody String searched) {
        if (isConnectionAvaliable) {
            SearchResults searchResults = twitterTemplate.searchOperations().search(searched);
            return service.saveAs(searched, searchResults);
        } else
            throw new ResourceAccessException("Twitter Api is not avaliable now");
    }

    @PostMapping("/search")
    public SearchResults searchAsAdmin(@RequestBody String searched) {
        if (isConnectionAvaliable) {
            SearchResults searchResults = twitterTemplate.searchOperations().search(searched);
            return searchResults;
        } else
            throw new ResourceAccessException("Twitter Api is not avaliable now");
    }

}
