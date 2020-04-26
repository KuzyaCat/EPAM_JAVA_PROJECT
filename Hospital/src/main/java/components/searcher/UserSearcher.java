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

    public List<User> findUsersBySurname(String surname) {
        Stream<User> userStream = this.userList.stream();

        return userStream
                .filter(u -> u.getSurname().equals(surname))
                .collect(Collectors.toList());
    }

    public List<User> findUsersByFullName(String firstName, String surname) {
        Stream<User> userStream = this.userList.stream();

        return userStream
                .filter(u -> u.getSurname().equals(surname) && u.getName().equals(firstName))
                .collect(Collectors.toList());
    }

    public List<User> findUsersByAge(int age) {
        Stream<User> userStream = this.userList.stream();

        return userStream
                .filter(u -> u.getAge() == age)
                .collect(Collectors.toList());
    }
}
