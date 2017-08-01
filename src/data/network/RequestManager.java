package data.network;

import org.jetbrains.annotations.Nullable;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

public final class RequestManager {

    private static final String REQUEST_GET = "GET";
    private static final String REQUEST_POST = "POST";

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

    public String get(String path, @Nullable RequestParam ...params) {
        try {
            URL url = new URL(baseEndpoint + path);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod(REQUEST_POST);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(CONNECTION_TIMEOUT);

            if (params != null) {
                for (RequestParam param : params) {
                    connection.addRequestProperty(param.getName(), param.getValue());
                }
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            return in.lines().collect(Collectors.joining("\n"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static class RequestParam {

        private final String name;

        private final String value;

        public RequestParam(String name, String value) {
            this.name = name;
            this.value = value;
        }

        String getName() {
            return name;
        }

        String getValue() {
            return value;
        }
    }

}
