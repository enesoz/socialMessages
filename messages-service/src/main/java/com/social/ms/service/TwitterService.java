package com.social.ms.service;

import com.mongodb.MongoException;
import com.social.ms.model.SearchResult;
import com.social.ms.model.TweetBrief;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Log
public class TwitterService {

    private Logger log;

    @Autowired
    MongoTemplate mongoTemplate;

    public SearchResult saveAs(String searched, org.springframework.social.twitter.api.SearchResults result) {
        try {
            SearchResult retVal = SearchResult.createSearchResult(searched, extracTotweetBrief(result.getTweets()));
            mongoTemplate.save(retVal);
            return retVal;
        } catch (com.mongodb.MongoTimeoutException mongo) {
            log.error("Mongo instance doesn't exist");
            throw new MongoException("Mongo instance doesn't exist");
        } catch (Exception e) {
            log.error("Something goes wrong", e);
            throw e;
        }
    }

    /**
     * org.springframework.social.twitter.api.Tweet ==> com.social.ms.model.TweetBrief
     *
     * @return List of tweetbrief
     * @see TweetBrief
     */

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
