package services;

import models.*;

import java.util.*;

public class PostService {
    private List<Post> posts = new ArrayList<>();
    private UserService userService;

    public PostService(UserService userService) {
        this.userService = userService;
    }

    public void createPost(User user, String content) {
        Post post = new TextPost(content, user);
        posts.add(post);
        user.addPost(post);
        System.out.println("Post created.");
    }

    public void showFeed() {
        System.out.println("\n==== FEED ====\n");
        int cnt = 1;
        if(posts.isEmpty()){
            System.out.println("No posts...");
        }
        for (Post post : posts) {
            System.out.println("Post no : " + cnt++);
            post.display();
            System.out.println("___________________________________________");

        }
    }

    public void showUserPosts(User user) {
        System.out.println("\nYour Posts: \n");
        int cnt = 1;
        for (Post p : user.getPosts()) {
            System.out.println("Post no : " + cnt++);
            p.display();
            System.out.println("___________________________________________");
        }
    }
}