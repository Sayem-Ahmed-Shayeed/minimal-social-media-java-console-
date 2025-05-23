package main;

import models.*;
import services.*;
import utilities.auth_util;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static UserService userService = new UserService();
    static PostService postService = new PostService(userService);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== Main Menu ====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Forgot Password?");
            System.out.println("0. Exit\n");
            System.out.print("Your response: ");
            int responseFromUser = scanner.nextInt();
            scanner.nextLine();

            switch (responseFromUser) {
                case 1 -> login();
                case 2 -> register();
                case 3 -> forgotPassword();
                case 0 -> {
                    System.out.println("Thank you bye bye...");
                    return;
                }
                default -> System.out.println("Invalid input!");
            }
        }
    }

    static void login() {
        // blank space for formatting
        System.err.println("\n");

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        if (auth_util.checkEmailValidity(email) && auth_util.checkPasswordValidity(password)) {
            User user = userService.login(email, password);
            if (user != null) {
                System.out.println("Login successful!\n");
                appMenu(user);
            } else {
                System.out.println("Invalid credentials.\n");
            }
        } else {
            System.out.println("Please enter valid email and password");
        }

    }

    static void register() {
        System.out.print("Enter Name : ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Password hints: ");
        String passwordHints = scanner.nextLine();

        userService.register(name, email, password, passwordHints);
    }

    static void forgotPassword() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your hints: ");
        String hints = scanner.nextLine();
        userService.resetPassword(email, hints);
    }

    static void appMenu(User user) {
        while (true) {
            System.out.println("\n==== App Menu ====\n");
            System.out.println("1. Feed");
            System.out.println("2. Profile");
            System.out.println("3. Settings");
            System.out.println("4. Logout");
            System.out.println("5. Show all users");
            System.out.println("0. Exit\n");

            System.out.print("Your response : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    postService.showFeed();

                }
                case 2 -> {
                    System.out.println("Name: " + user.getName());
                    System.out.println("Email: " + user.getEmail());
                    profileMenu(user);

                }
                case 3 -> {
                    settingsMenu(user);

                }
                case 4 -> {
                    System.out.println("Logged out.");
                    return;
                }
                case 5 -> {
                    userService.showAllUsers();
                }
                case 0 -> {
                    System.out.println("Thank you !");
                    System.exit(0);
                }
                default -> System.out.println("Invalid input!");
            }
        }
    }

    static void profileMenu(User user) {
        while (true) {
            System.out.println("\nProfile Menu:");
            System.out.println("1. Create Post");
            System.out.println("2. Show My Posts");
            System.out.println("3. Back");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter your post: ");
                    String content = scanner.nextLine();
                    postService.createPost(user, content);
                }
                case 2 -> postService.showUserPosts(user);
                case 3 -> {
                    return;
                }
                default -> System.out.println("Invalid input.");
            }
        }
    }

    static void settingsMenu(User user) {
        while (true) {
            System.out.println("\nSettings Menu:\n");
            System.out.println("1. Change Name");
            System.out.println("2. Change Password");
            System.out.println("3. Back");
            System.out.print("Your response : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter New name: ");
                    String name = scanner.nextLine();
                    user.setName(name);
                    System.out.println("Name updated.\n");
                }
                case 2 -> {
                    System.out.print("Enter New password: ");
                    String pass = scanner.nextLine();

                    System.out.print("Enter New password hints: ");
                    String passHints = scanner.nextLine();
                    user.setPassword(pass, passHints);
                    System.out.println("Password updated.\n");
                }
                case 3 -> {
                    return;
                }
                default -> System.out.println("Invalid input.\n");
            }
        }
    }
}