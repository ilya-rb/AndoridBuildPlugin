package data.social.twitter;

import com.google.gson.JsonParser;
import data.network.NetworkScheduler;
import data.network.RequestManager;
import data.network.Pair;
import data.network.RequestType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

final class TwitterApiService {

    private static final String CONSUMER_KEY = "Lw65OoxiJKmCmtpCjx8Jagb1L";
    private static final String CONSUMER_SECRET = "X2PwEoGGCRiB22v5pnAbFfDBtWh52tzGBRkoUpXCV7gq0KxM0l";
    private static final String APP_CALLBACK = "twitterclient://callback";
    private static final String ENDPOINT = "https://api.twitter.com/";
    private static final String CALLBACK_URL = "https://api.twitter.com/oauth/authenticate/?oauth_token=";

    private static final String HEADER_CONSUMER_KEY = "oauth_consumer_key";
    private static final String HEADER_CALLBACK = "oauth_callback";

    private static volatile TwitterApiService instance;

    private final RequestManager requestManager = new RequestManager(ENDPOINT);
    private final NetworkScheduler scheduler = new NetworkScheduler();
    private final JsonParser parser = new JsonParser();

    static TwitterApiService getInstance() {
        TwitterApiService localInstance = instance;

        if (localInstance == null) {
            synchronized (TwitterApiService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new TwitterApiService();
                }
            }
        }

        return localInstance;
    }

    private TwitterApiService() {
    }

    public CompletableFuture<String> requestOauthToken() {
        return CompletableFuture.supplyAsync(() -> {
            List<Pair> headers = new ArrayList<>();

            headers.add(new Pair(HEADER_CONSUMER_KEY, CONSUMER_KEY));
            headers.add(new Pair(HEADER_CALLBACK, APP_CALLBACK));

            String result = requestManager
                    .performRequest("oauth/request_token", RequestType.POST, null, headers);

            return CALLBACK_URL + parser.parse(result).getAsString();

        }, scheduler.getExecutor());
    }

    CompletableFuture<Boolean> authorize(String name, String password) {
        return CompletableFuture.supplyAsync(() -> true, scheduler.getExecutor());
    }
}
