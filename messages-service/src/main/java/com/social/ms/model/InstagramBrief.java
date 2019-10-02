package com.social.ms.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InstagramBrief {

    private String id;
    private String url;
    private String caption;
    private Date createdTime;
    private String link;
    private int like;

    public static InstagramBrief createInstagramBrief(String id, String url, String caption, Date createdTime, String link, int like) {
        return new InstagramBrief(id, url, caption, createdTime, link, like);
    }
}
