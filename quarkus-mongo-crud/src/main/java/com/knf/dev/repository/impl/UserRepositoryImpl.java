package com.knf.dev.repository.impl;

import com.knf.dev.model.User;
import com.knf.dev.repository.UserRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {
    private final MongoClient mongoClient;

    @Inject
    public UserRepositoryImpl(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    private MongoCollection getCollection() {
        return mongoClient.getDatabase("user-test").getCollection("user-test");
    }

    @Override
    public User saveUser(User user) {
        Document document = new Document()
                .append("firstName", user.getFirstName()).
                append("lastName", user.getLastName()).
                append("emailId", user.getEmailId());
        getCollection().insertOne(document);
        user.setId(document.getObjectId("_id").toString());
        return user;
    }

    @Override
    public User updateUser(String id, User user) {
        Optional<Document> optionalDocument = findDocumentById(id);
        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            document.put("firstName", user.getFirstName());
            document.put("lastName", user.getLastName());
            document.put("emailId", user.getEmailId());
            getCollection().replaceOne(eq(new ObjectId(id)), document);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                User user = new User();
                user.setLastName(document.getString("lastName"));
                user.setFirstName(document.getString("firstName"));
                user.setEmailId(document.getString("emailId"));
                user.setId(String.valueOf(document.getObjectId("_id")));
                users.add(user);
            }
        } finally {
            cursor.close();
        }
        return users;
    }

    @Override
    public Optional<User> findUserById(String id) {
        return findDocumentById(id).map(this::mapUser);
    }

    @Override
    public void deleteUserById(String id) {
        getCollection().deleteOne(eq(new ObjectId(id)));
    }

    private Optional<Document> findDocumentById(String id) {
        MongoCursor<Document> iterator =
                getCollection().find(eq(new ObjectId(id))).iterator();
        return iterator.hasNext() ?
                Optional.of(iterator.next()) : Optional.empty();
    }

    private User mapUser(Document document) {
        User user = new User();
        user.setId(document.getObjectId("_id").
                toString());
        user.setEmailId(document.getString("emailId"));
        user.setLastName(document.getString("lastName"));
        user.setFirstName(document.getString("firstName"));
        return user;
    }
}
