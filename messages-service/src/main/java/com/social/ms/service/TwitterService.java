package com.social.ms.service;

import com.social.ms.model.SearchResult;
import com.social.ms.model.TweetBrief;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TwitterService {

    @Autowired
    MongoTemplate mongoTemplate;

    public SearchResult saveAs(String searched, org.springframework.social.twitter.api.SearchResults result) {
        try {
            SearchResult retVal = SearchResult.createSearchResult(searched, extracTotweetBrief(result.getTweets()));
            mongoTemplate.save(retVal);
            return retVal;
        } catch (Exception e) {
            throw e;
        }
    }

    private List extracTotweetBrief(List<Tweet> tweets) {
        ArrayList<TweetBrief> targetList =
                tweets.stream().
                        map(s -> {
                            TweetBrief tweetBrief = new TweetBrief(s.getText(), s.getFromUser(), s.getCreatedAt(), s.getLanguageCode(), s.getRetweetCount());
                            return tweetBrief;
                        }).
                        collect(Collectors.toCollection(ArrayList::new));
        return targetList;
    }
}
