package View;
import Helper.Helper;
import Controller.UserController;
import Entity.User;
//import java.util.Scanner;
public class LoginPage {
    public static User getLogin(){

        String email;
        String password;
        //Scanner sc = new Scanner(System.in);
        Helper.line(80, "=");
		System.out.println("Login Page");
		Helper.line(80, "=");
		//System.out.println("Enter your email");
        
        email = Helper.readString("Enter your email: ");
        password = Helper.readString("Enter your password: ");
        User loginUser = UserController.loginUser(email,password);
        if(loginUser != null){
            return loginUser;
        }
        else{
            return null;
        }
        
    }
}
