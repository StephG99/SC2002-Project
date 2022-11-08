package Controller;

import Entity.User;

import java.io.IOException;
import java.util.ArrayList;



//Login Controller settles login for all User. we do not store payment information for customer
public class UserController {
    public static User loginUser(String email,String password){
        ArrayList<User> tempArray = DatabaseController.getAllUser();
        for(int i = 0 ; i < tempArray.size(); i++){
            if (tempArray.get(i).isRegisteredUser(email,password)){
                //found User we shall use him for the rest of the session
                return tempArray.get(i);
                //break;
            }
        }
        //no User found Proceed to return null object.
        return null;
    }
    public static User registerUser(String name,String email,String password) throws IOException{
        return DatabaseController.registerUser(new User(name,email,password,false));

    }

    
}
