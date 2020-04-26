package main.java.components.searcher;

import main.java.users.User;

import java.util.List;

public class UserSearcher {
    protected List<User> userList;

    public UserSearcher(List<User> userList) {
        this.userList = userList;
    }
}
