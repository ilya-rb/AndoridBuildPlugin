package data.social.twitter;

import data.social.SocialAuthenticator;

import java.util.concurrent.CompletableFuture;

public class TwitterSocialAuthenticator implements SocialAuthenticator {

    private final TwitterApiService service = TwitterApiService.getInstance();

    @Override
    public CompletableFuture<Boolean> authorize(String username, String password) {
        return service.authorize(username, password);
    }

    public CompletableFuture<String> requestOauthToken() {
        return service.requestOauthToken();
    }
}
