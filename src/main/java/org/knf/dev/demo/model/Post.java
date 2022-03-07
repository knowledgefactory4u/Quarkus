package org.knf.dev.demo.model;

public class Post {

    private String userId;
    private String id;
    private String body;
    private String title;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Post() {
    }

    public Post(String userId,
                String id, String body, String title) {
        this.userId = userId;
        this.id = id;
        this.body = body;
        this.title = title;
    }
}
