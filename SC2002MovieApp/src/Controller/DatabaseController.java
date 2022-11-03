package Controller;
import Entity.*;
//Database controller is the only controller allowed to touch the different files.
public class DatabaseController {
    public static User[] getAllUser(){
        //Fake data right here we need to get read from a file to retrieve this info and return it back.
        User[] nex = {new User("Yan","yannarojc@outlook.com","123456",true),new User("Fabbian","Favc@outlook.com","123456",false)};
        return nex;
    }
}
