package com.social.ms.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TweetBrief {

    private Long id;
    private String text;
    private String from_user;
    private Date createdAt;
    private String languageCode;
    private int retweetCount;
    private String profileImageUrl;

    public TweetBrief(String text, String from_user, Date createdAt, String languageCode, int retweetCount, String profileImageUrl) {
        this.text = text;
        this.from_user = from_user;
        this.createdAt = createdAt;
        this.languageCode = languageCode;
        this.retweetCount = retweetCount;
        this.profileImageUrl = profileImageUrl;
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
