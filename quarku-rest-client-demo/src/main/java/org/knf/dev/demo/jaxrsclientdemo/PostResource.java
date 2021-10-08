package org.knf.dev.demo.jaxrsclientdemo;

import org.knf.dev.demo.model.Post;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.util.List;

@Path("/api/v1/jaxrsclient")
public class PostResource {

    private final Client client = ClientBuilder.newBuilder().build();
    private final String url = "https://jsonplaceholder.typicode.com/posts";

    @GET
    @Path("/posts")
    public List<Post> fetchAllPosts() {
        List<Post> posts = client.target(url).
                request().get(new GenericType<List<Post>>() {
        });
        return posts;
    }
}
