package utilities;

public class auth_util {
    public static boolean checkEmailValidity(String email) {
        if (!email.isEmpty() && email.contains("@") && email.contains(".")) {
            return true;
        }
        return false;
    }

    // password should be at least 8 char long to be valid
    public static boolean checkPasswordValidity(String password) {
        if (!password.isEmpty() && password.length() >= 8) {
            return true;
        }
        return false;
    }
}
