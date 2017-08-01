package data.social.twitter;

import data.network.NetworkScheduler;
import data.network.RequestManager;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

final class TwitterApiService {

    private static final String CONSUMER_KEY = "Lw65OoxiJKmCmtpCjx8Jagb1L";
    private static final String CONSUMER_SECRET = "X2PwEoGGCRiB22v5pnAbFfDBtWh52tzGBRkoUpXCV7gq0KxM0l";
    private static final String ENDPOINT = "https://api.github.com/";

    private static volatile TwitterApiService instance;

    private final NetworkScheduler scheduler = new NetworkScheduler();
    private final RequestManager requestManager = new RequestManager(ENDPOINT);

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

    CompletableFuture<Boolean> authorize(String name, String password) {
        return CompletableFuture.supplyAsync(() -> {
            String response = requestManager.get("users");
            System.out.println(response);
            return true;

        }, scheduler.getExecutor());
    }
}
