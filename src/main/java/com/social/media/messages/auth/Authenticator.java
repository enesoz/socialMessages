package com.social.media.messages.auth;


import com.google.api.client.auth.oauth.*;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.social.media.messages.exception.AuthenticationException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;


public class Authenticator {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Authenticator.class);

    private final PrintStream out;

    @Value("consumer.key")
    private final String consumerKey;
    @Value("consumer.secret")
    private final String consumerSecret;

    private HttpRequestFactory factory;

    private static final HttpTransport TRANSPORT = new NetHttpTransport();
    @Value("twitter.authorize_url")
    private static String AUTHORIZE_URL;
    @Value("twitter.access.token.url")
    private static String ACCESS_TOKEN_URL;
    @Value("twitter.request.token.url")
    private static String REQUEST_TOKEN_URL;


    public Authenticator(final PrintStream out, final String consumerKey, final String consumerSecret) {
        this.out = out;
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
    }


    public synchronized HttpRequestFactory getAuthorizedHttpRequestFactory() throws AuthenticationException {
        if (factory != null) {
            return factory;
        }

        factory = createRequestFactory();
        return factory;
    }


    private HttpRequestFactory createRequestFactory() throws AuthenticationException {
        OAuthHmacSigner signer = new OAuthHmacSigner();
        signer.clientSharedSecret = consumerSecret;

        OAuthCredentialsResponse requestTokenResponse = getTemporaryToken(signer);
        signer.tokenSharedSecret = requestTokenResponse.tokenSecret;

        OAuthAuthorizeTemporaryTokenUrl authorizeUrl = new OAuthAuthorizeTemporaryTokenUrl(AUTHORIZE_URL);
        authorizeUrl.temporaryToken = requestTokenResponse.token;

        String providedPin = retrievePin(authorizeUrl);

        OAuthCredentialsResponse accessTokenResponse = retrieveAccessTokens(providedPin, signer, requestTokenResponse.token);
        signer.tokenSharedSecret = accessTokenResponse.tokenSecret;

        OAuthParameters parameters = new OAuthParameters();
        parameters.consumerKey = consumerKey;
        parameters.token = accessTokenResponse.token;
        parameters.signer = signer;

        return TRANSPORT.createRequestFactory(parameters);
    }

    private OAuthCredentialsResponse getTemporaryToken(final OAuthHmacSigner signer) throws AuthenticationException {
        OAuthGetTemporaryToken requestToken = new OAuthGetTemporaryToken(REQUEST_TOKEN_URL);
        requestToken.consumerKey = consumerKey;
        requestToken.transport = TRANSPORT;
        requestToken.signer = signer;

        OAuthCredentialsResponse requestTokenResponse;
        try {
            requestTokenResponse = requestToken.execute();
        } catch (IOException e) {
            throw new AuthenticationException("Unable to aquire temporary token: " + e.getMessage(), e);
        }

        out.println("Aquired temporary token...\n");

        return requestTokenResponse;
    }


    private String retrievePin(final OAuthAuthorizeTemporaryTokenUrl authorizeUrl) throws AuthenticationException {
        String providedPin;
        Scanner scanner = new Scanner(System.in);
        try {
            out.println("Go to the following link in your browser:\n" + authorizeUrl.build());
            out.println("\nPlease enter the retrieved PIN:");
            providedPin = scanner.nextLine();
        } finally {
            scanner.close();
        }

        if (providedPin == null) {
            throw new AuthenticationException("Unable to read entered PIN");
        }

        return providedPin;
    }


    private OAuthCredentialsResponse retrieveAccessTokens(final String providedPin, final OAuthHmacSigner signer, final String token) throws AuthenticationException {
        OAuthGetAccessToken accessToken = new OAuthGetAccessToken(ACCESS_TOKEN_URL);
        accessToken.verifier = providedPin;
        accessToken.consumerKey = consumerSecret;
        accessToken.signer = signer;
        accessToken.transport = TRANSPORT;
        accessToken.temporaryToken = token;

        OAuthCredentialsResponse accessTokenResponse;
        try {
            accessTokenResponse = accessToken.execute();
        } catch (IOException e) {
            throw new AuthenticationException("Unable to authorize access: " + e.getMessage(), e);
        }
        out.println("\nAuthorization was successful");

        return accessTokenResponse;
    }
}
