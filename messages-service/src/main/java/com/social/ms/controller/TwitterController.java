package com.social.ms.controller;

import com.social.ms.model.SearchResult;
import com.social.ms.service.TwitterClient;
import com.social.ms.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@Component
public class TwitterController implements TwitterClient {

    TwitterTemplate twitterTemplate;
    @Autowired
    TwitterService service;
    @Value("${twitter.consumer.key}")
    private String consumerKey;
    @Value("${twitter.consumer.secret}")
    private String consumerSecret;

    @PostConstruct
    public void init() {
        twitterTemplate = new TwitterTemplate(consumerKey, consumerSecret);
    }

    @RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET)
    public SearchResult search(@PathVariable(value = "keyword") String searched) {
        SearchResult result;
        SearchResults searchResults = twitterTemplate.searchOperations().search(searched);
        return service.saveAs(searched, searchResults);
    }

}
