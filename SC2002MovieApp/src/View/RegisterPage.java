package View;
import java.io.IOException;

import Controller.UserController;
import Entity.User;
import Helper.Helper;

public class RegisterPage {
    public static User registerUser() throws IOException{

        String email;
        String password;
        String name;
        //Scanner sc = new Scanner(System.in);
        Helper.line(80, "=");
		System.out.println("Register Page");
		Helper.line(80, "=");
		//System.out.println("Enter your email");
        name = Helper.readString("Enter your name: ");
        email = Helper.readString("Enter your email: ");
        password = Helper.readString("Enter your password: ");
        User loginUser = UserController.registerUser(name,email,password);
        if(loginUser != null){
            return loginUser;
        }
        else{
            return null;
        }
        
    }
    
}
