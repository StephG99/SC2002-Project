package View;
import Helper.Helper;
//import Helper from "../src/Helper.java";

public class HomePage {
    public static void displayMenu(){
        Helper.line(80, "=");
		System.out.println("WELCOME TO MOVIE WORLD");
		Helper.line(80, "=");
		System.out.println("1. View All Movie");
		System.out.println("2. Login");
		System.out.println("3. Register");
		System.out.println("4. Book a Movie");
		System.out.println("5. Quit");
    }
    
}
