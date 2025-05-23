package models;

import java.util.*;

public class User {
    private String name;
    private String email;
    private String password;
    private String passwordHints;
    private List<Post> posts = new ArrayList<>();

    public User(String name, String email, String password, String passwordHints) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.passwordHints = passwordHints;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getHints() {
        return passwordHints;
    }

    public boolean checkPassword(String input) {
        return password.equals(input);
    }

    public boolean checkHints(String hints) {
        return hints.equals(passwordHints);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password, String hints) {
        this.password = password;
        passwordHints = hints;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public List<Post> getPosts() {
        return posts;
    }
}