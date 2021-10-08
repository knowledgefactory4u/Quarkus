package org.knf.dev.demo.data;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public
class UserData {

    public User getUserById(Long id) {
        User user = genearteDummyData().get(id);
        return user;
    }

    //Generate Dummy Users
    private Map<Long, User> genearteDummyData() {
        Map<Long, User> dummyUsers = new HashMap<>();
        User user1 = new User("user-1", "user-1@gmail.com");
        User user2 = new User("user2", "user2@gmail.com");
        User user3 = new User("user3", "user3@gmail.com");
        dummyUsers.put(22l, user1);
        dummyUsers.put(13l, user1);
        dummyUsers.put(19l, user1);
        return dummyUsers;
    }
}
