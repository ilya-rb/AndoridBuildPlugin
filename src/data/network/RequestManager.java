package data.network;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public final class RequestManager {

    public static final String REQUEST_ERROR = "";

    /**
     * 10 Seconds
     */
    private static final int CONNECTION_TIMEOUT = 10000;

    /**
     * Base url for all requests
     */
    private final String baseEndpoint;

    public RequestManager(String endpoint) {
        this.baseEndpoint = endpoint;
    }

    public String performRequest(@NotNull String path,
                                 @NotNull RequestType type,
                                 @Nullable List<Pair> params,
                                 @Nullable List<Pair> headers) {

        HttpsURLConnection connection = null;
        BufferedReader input = null;

        try {
            URL url = new URL(baseEndpoint + path);
            connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod(type.name());
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(CONNECTION_TIMEOUT);

            if (params != null) {
                for (Pair param : params) {
                    connection.addRequestProperty(param.getFirst(), param.getSecond());
                }
            }

            if (headers != null) {
                for (Pair param : headers) {
                    connection.setRequestProperty(param.getFirst(), param.getSecond());
                }
            }

            input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            return input.lines().collect(Collectors.joining("\n"));

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return REQUEST_ERROR;
    }
}
