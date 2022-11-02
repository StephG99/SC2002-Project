import Entity.*;
import Helper.Helper;

import java.util.Scanner;

import Controller.*;
import View.*;


public class App {
    public static void main(String[] args) throws Exception {
        int option;
        do {
            Scanner sc = new Scanner(System.in);
            option = 0;
            customerView.displayMenu();
            // sc.nextLine();
            option = sc.nextInt();
            if (option == 1) {
                System.out.println("Option 1!");
            } else if (option == 2) {
                System.out.println("Option 2!");
            } else if (option == 3) {
                System.out.println("Thank you for using MOBLIMA!");
                System.exit(0);
            } else {
                System.out.println("Invalid option");
            }
        } while (option != 3);

    }
}
