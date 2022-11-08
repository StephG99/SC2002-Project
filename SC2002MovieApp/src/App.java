import java.util.ArrayList;

import Controller.DatabaseController;
import Controller.MovieController;
import Entity.*;
//import Helper.Helper;

//import java.util.Scanner;

//import Controller.*;
import View.*;
import Helper.Helper;
import Entity.*;
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


            //routing Page
            if(isLogin == 0){
            if (option == 1) {
                //TODO View Movie
                MovieController.printMovieList(DatabaseController.getAllMovie());
                ArrayList<String> a = new ArrayList<String>();
                a.add("Hitler");
                a.add("Herman Goerring");
                ArrayList<Review> b = new ArrayList<Review>();
                b.add(new Review(1,"Hello","yannarojc@outlook.com"));
                b.add(new Review(2,"Hello","yann@outlook.com"));
                movie c = new movie(1,"Tenet","Testing",true,"Action",2,"PG13","PornHub",a,4.8,b);
                System.out.println("View All Movie Page");
                System.out.println(c.encodeReviews());
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
                else{
                    System.out.println("You already have an account!");
                }
            }
            else if(option == 4){
                //TODO book a Movie
                System.out.println("Book a Movie Page");
            }
             else if (option == exit) {
                System.out.println("Thank you for using MovieBoss.com!");
                System.exit(0);
            } else {
                System.out.println("Invalid option");
            }
        }
        else if(isLogin == 1){
            if (option == 1) {
                //TODO View Movie
                System.out.println("View All Movie Page");
                
            } else if (option == 2) {
                //TODO view Transaction
                System.out.println("View Transaction page");

            }
            else if(option == 3){
                //TODO review Movie
                System.out.println("Review Movie");
            }
            else if(option == 4){
                //TODO book a Movie
                System.out.println("Book a Movie Page");
            }
            else if(option == 5){
                isLogin = 0;
                option = 0;
                exit = 5;
                loginUser = null;
                System.out.println("User is logged out");
            }
             else if (option == exit) {
                System.out.println("Thank you for using MovieBoss.com!");
                System.exit(0);
            } else {
                System.out.println("Invalid option");
            }
        }
        else if(isLogin == 2){
            if (option == 1) {
                //TODO View Movie
                System.out.println("View All Movie Page");
                
            } else if (option == 2) {
                //TODO view ALL Transaction(Admin)
                System.out.println("View ALL Transaction page");

            }
            else if(option == 3){
                //TODO Edit and view System Settings;
                System.out.println("Edit System Settings");
            }
            else if(option == 4){
                //TODO edit Movie details
                System.out.println("Edit a Movie ");
            }
            else if(option == 5){
                isLogin = 0;
                option = 0;
                exit = 5;
                loginUser = null;
                System.out.println("User is logged out");
            }
             else if (option == exit) {
                System.out.println("Thank you for using MovieBoss.com!");
                System.exit(0);
            } else {
                System.out.println("Invalid option");
            }

        }

        } while (option != exit);

    }
}
