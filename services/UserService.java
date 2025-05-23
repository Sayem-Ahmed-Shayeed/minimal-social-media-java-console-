package services;

import models.*;
import utilities.auth_util;

import java.util.*;

public class UserService {
    private List<User> users = new ArrayList<>();

    public void register(String name, String email, String password, String passwordHints) {

        if (auth_util.checkEmailValidity(email) && auth_util.checkPasswordValidity(password)) {
            users.add(new User(name, email, password, passwordHints));
            System.out.println("User registered.");
        } else {
            System.out.println("Enter valid email and password");
        }
    }

    public User login(String email, String password) {
        if (auth_util.checkEmailValidity(email) && auth_util.checkPasswordValidity(password)) {
            for (User u : users) {
                if (u.getEmail().equals(email) && u.checkPassword(password)) {
                    return u;
                }
            }
        } else {
            System.out.println("Enter valid email and password");
        }

        return null;
    }

    public void resetPassword(String email, String hints) {
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                if (u.getHints().equals(hints)) {
                    Scanner s = new Scanner(System.in);
                    System.out.print("Enter new password: ");
                    String newPass = s.nextLine();
                    System.out.print("Enter new password hints: ");
                    String newPassHints = s.nextLine();
                    u.setPassword(newPass, newPassHints);
                    System.out.println("Password updated.\n");
                    s.close();
                    return;
                } else {
                    System.out.println("Hints did not matched");
                }
            }
        }
        System.out.println("Email not found.");
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void showAllUsers() {
        System.out.println("---- All Registered Users ----\n");
        for (User u : users) {
            System.out.println("- " + u.getName() + " (" + u.getEmail() + ")");
        }
        System.err.println("\n");
    }
}

