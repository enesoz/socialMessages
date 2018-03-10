package com.social.ms.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.social.twitter.api.SearchResults;

import java.util.List;

@Getter
@Setter
@ToString
@Document(collection = "result")
public class SearchResult {

    @Id
    private long id;
    private String searched;
    private List<TweetBrief> tweets;
    @JsonIgnore
    private SearchResults originalResult;

    @JsonCreator
    public SearchResult() {
    }

    public SearchResult(String searched, List<TweetBrief> tweets, SearchResults originalResult) {
        this.searched = searched;
        this.tweets = tweets;
        this.originalResult = originalResult;
    }

    public static SearchResult createSearchResult(String searched, List tweets, SearchResults originalResult) {
        return new SearchResult(searched, tweets, originalResult);
    }


    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return new String();
        }
    }
}
