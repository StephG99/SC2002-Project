import Entity.*;
//import Helper.Helper;

//import java.util.Scanner;

//import Controller.*;
import View.*;
import Helper.Helper;


public class App {
    public static void main(String[] args) throws Exception {
        int option;
        int exit = 3;
        User loginUser;
        int isLogin = 0;
        do {
            //Scanner sc = new Scanner(System.in);
            option = 0;
            HomePage.displayMenu(isLogin);
            // sc.nextLine();
            option = Helper.readInt("");
            //sc.close();
            if (option == 1) {
                System.out.println("Option 1!");
            } else if (option == 2) {
                loginUser = LoginPage.getLogin();
                if(loginUser == null){
                    System.out.println("You did not login");
                }
                else{
                    if(loginUser.checkAdmin()){
                        isLogin = 2;
                        exit = 4;
                    }
                    else{
                        isLogin = 1;
                        exit = 6;
                    }
                    System.out.println("You have login successfully");
                }

            } else if (option == exit) {
                System.out.println("Thank you for using MOBLIMA!");
                System.exit(0);
            } else {
                System.out.println("Invalid option");
            }
        } while (option != exit);

    }
}
