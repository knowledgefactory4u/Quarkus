package org.knf.dev.demo.newhttpclientdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Singleton
public class HttpService {

    private final HttpClient httpClient = HttpClient.
            newBuilder().version(HttpClient.Version.HTTP_2).build();

    public <T> List<T> fetchPosts(String url, Class<T> responseType)
            throws InterruptedException, IOException {
        HttpRequest request = HttpRequest.newBuilder().GET().
                uri(URI.create(url)).header("Accept",
                        "application/json").build();
        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.
                        BodyHandlers.ofString());
        ObjectMapper objectMapper =
                new ObjectMapper();
        return objectMapper.
                readValue(response.body(),
                        objectMapper.getTypeFactory().
                                constructCollectionType(List.class, responseType));
    }
}
