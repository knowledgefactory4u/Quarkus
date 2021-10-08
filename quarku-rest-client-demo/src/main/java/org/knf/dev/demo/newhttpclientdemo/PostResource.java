package org.knf.dev.demo.newhttpclientdemo;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.knf.dev.demo.model.Post;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;
import java.util.List;

@Path("/api/v1/httpclient")
public class PostResource {

    @Inject
    HttpService httpService;

    @GET
    @Path("/posts")
    public List<Post> fetchAllPosts() throws IOException, InterruptedException {
        HttpService httpService = new HttpService();
        List<Post> posts = httpService.
                fetchPosts("https://jsonplaceholder.typicode.com/posts", Post.class);
        return posts;
    }
}
