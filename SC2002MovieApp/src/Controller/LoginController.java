package Controller;

import Entity.User;




public class LoginController {
    public static User loginUser(String email,String password){
        User[] tempArray = DatabaseController.getAllUser();
        for(int i = 0 ; i < tempArray.length ; i++){
            if (tempArray[i].isRegisteredUser(email,password)){
                return tempArray[i];
                //break;
            }
        }
        return null;
    }
    
}
