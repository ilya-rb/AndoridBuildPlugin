package data.social;

import data.social.twitter.TwitterSocialAuthenticator;

import java.util.concurrent.CompletableFuture;

public interface SocialAuthenticator {

    CompletableFuture<Boolean> authorize(String username, String password);

    void onSuccess(String token);

    class Factory {

        public static SocialAuthenticator create(SocialType type) {
            return new TwitterSocialAuthenticator();
        }

    }

}
