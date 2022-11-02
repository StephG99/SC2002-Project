package Controller;
import Entity.*;
public class DatabaseController {
    public static User[] getAllUser(){
        User[] nex = {new User("Yan","yannarojc@outlook.com","123456",true),new User("Fabbian","Favc@outlook.com","123456",false)};
        return nex;
    }
}
