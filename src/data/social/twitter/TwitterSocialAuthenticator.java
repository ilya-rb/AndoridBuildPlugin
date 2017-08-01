package data.social.twitter;

import data.social.SocialAuthenticator;

import java.util.concurrent.CompletableFuture;

public class TwitterSocialAuthenticator implements SocialAuthenticator {

    @Override
    public CompletableFuture<Boolean> authorize(String username, String password) {

    }

    @Override
    public void onSuccess(String token) {

    }
}
