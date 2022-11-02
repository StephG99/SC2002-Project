package View;
import Helper.Helper;
//import Helper from "../src/Helper.java";

public class customerView {
    public static void displayMenu(){
        Helper.line(80, "=");
		System.out.println("WELCOME TO MOVIE WORLD");
		Helper.line(80, "=");
		System.out.println("1. View All Movies");
		System.out.println("2. Search for a Movie by Release Year");
		System.out.println("3. Search for a Movie by keyword");
		System.out.println("4. Quit");
    }
    
}
