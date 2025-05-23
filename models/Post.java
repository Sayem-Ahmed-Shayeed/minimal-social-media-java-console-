package models;

public abstract class Post {
    protected String content;
    protected User author;

    public Post(String content, User author) {
        this.content = content;
        this.author = author;
    }

    public abstract void display();
}