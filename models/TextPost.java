package models;

public class TextPost extends Post {
    public TextPost(String content, User author) {
        super(content, author);
    }

    @Override
    public void display() {
        System.out.println("Author : " + author.getName() + "\n" + content);
    }
}