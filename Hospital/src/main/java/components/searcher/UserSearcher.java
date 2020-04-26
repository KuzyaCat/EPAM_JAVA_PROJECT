package main.java.components.searcher;

import main.java.users.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserSearcher {
    protected List<User> userList;

    public UserSearcher(List<User> userList) {
        this.userList = userList;
    }

    public List<User> findUsersByFirstName(String firstName) {
        Stream<User> userStream = this.userList.stream();

        return userStream
                .filter(u -> u.getName().equals(firstName))
                .collect(Collectors.toList());
    }
}
