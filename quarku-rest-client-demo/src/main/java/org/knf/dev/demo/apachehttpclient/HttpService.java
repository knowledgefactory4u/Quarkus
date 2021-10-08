package org.knf.dev.demo.apachehttpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.knf.dev.demo.model.Post;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.List;

@Singleton
public class HttpService {

    CloseableHttpClient httpClient = HttpClients.createDefault();

    public <T> List<T> fetchPosts(String url, Class<Post> responseType)
            throws IOException {
        HttpGet request = new HttpGet(url);
        request.addHeader("content-type", "application/json");
        CloseableHttpResponse response = httpClient.execute(request);
        ObjectMapper objectMapper =
                new ObjectMapper();
        return objectMapper.
                readValue(response.getEntity().getContent(),
                        objectMapper.getTypeFactory().
                                constructCollectionType(List.class, responseType));
    }
}