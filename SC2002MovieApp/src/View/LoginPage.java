package View;

import Helper.Helper;
import Controller.UserController;
import Entity.User;

//import java.util.Scanner;
public class LoginPage {
    /**
     * Main user login page
     * 
     * @return checks if user has already registered an account then returns the
     *         associated user object
     */
    public static User getLogin() {

        String email;
        String password;
        // Scanner sc = new Scanner(System.in);
        Helper.line(80, "=");
        System.out.println("Login Page");
        Helper.line(80, "=");
        // System.out.println("Enter your email");

        email = Helper.readString("Enter your email: ");
        password = Helper.readString("Enter your password: ");
        User loginUser = UserController.loginUser(email, password);
        if (loginUser != null) {
            return loginUser;
        } else {
            return null;
        }

    }
}
