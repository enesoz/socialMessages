package com.social.backend.controller;

import com.social.backend.enums.SocialMediaType;
import com.social.backend.exceptions.NotImplementedSocialMediaException;
import com.social.backend.service.SearchApi;
import com.social.backend.service.SearchBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Locale;

@Slf4j
@Component
@RestController
public class SocialMessageController {

    @CrossOrigin
    @RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET)
    public String search(@PathVariable(value = "keyword") String searched, @PathParam(value = "type") String type) throws NotImplementedSocialMediaException, Exception {
        try {
            SearchApi searchApi = SearchBuilder.build(SocialMediaType.valueOf(type.toUpperCase(Locale.ENGLISH)));
            return searchApi.search(searched);

        } catch (Exception e) {
            log.error("Message-Microservis throw error", e);
            throw e;
        }
    }
}


