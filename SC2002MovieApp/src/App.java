import Entity.*;
//import Helper.Helper;

//import java.util.Scanner;

//import Controller.*;
import View.*;
import Helper.Helper;

//routing page
public class App {
    public static void main(String[] args) throws Exception {
        int option;
        int exit = 5;
        User loginUser;
        // isLogin differentiate whether is guest/RegisteredCustomer/Staff 0/1/2
        int isLogin = 0;
        do {
            option = 0;
            HomePage.displayMenu(isLogin);

            option = Helper.readInt("");

            if (option == 1) {
                System.out.println("This is option 1 for guest");
                
            } else if (option == 2) {
                loginUser = LoginPage.getLogin();
                if (loginUser == null) {
                    System.out.println("You did not login");
                } else {
                    if (loginUser.checkAdmin()) {
                        // Set to admin view
                        isLogin = 2;
                        // set the termination of program to option 6
                        exit = 6;
                    } else {
                        // set to registered customer view
                        isLogin = 1;
                        // set the termination of program to option 6
                        exit = 6;
                    }
                    System.out.println(loginUser.getName());
                    System.out.println("You have login successfully");
                }

            }
            else if(option == 3){
                loginUser = RegisterPage.registerUser();
                if(loginUser != null){
                    isLogin = 1;
                    exit = 6;
                }
            }
             else if (option == exit) {
                System.out.println("Thank you for using MovieBoss.com!");
                System.exit(0);
            } else {
                System.out.println("Invalid option");
            }

        } while (option != exit);

    }
}
