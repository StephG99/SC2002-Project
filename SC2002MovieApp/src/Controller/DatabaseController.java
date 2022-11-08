package Controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import com.opencsv.CSVWriter;

import Entity.*;
//Database controller is the only controller allowed to touch the different files.
public class DatabaseController {
    public static ArrayList<User> getAllUser(){
        ArrayList<User> nex = new ArrayList<>();
        String line = "";  
        String splitBy = ",";  
        try   
        {  
        //parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/user.csv"));  
        while ((line = br.readLine()) != null)   //returns a Boolean value  
        {  
        String[] user = line.split(splitBy);    // use comma as separator  
         nex.add(new User(user[0],user[1],user[2],Boolean.parseBoolean(user[3]))); 

        }  
        br.close();
        }   
        catch (IOException e)   
        {  
        e.printStackTrace();  
        }  
        return nex;
    }
    public static User registerUser(User newUser) throws IOException{
        try (
            Writer mFileWriter = new FileWriter("SC2002MovieApp/src/Database/user.csv", true);

            CSVWriter csvWriter = new CSVWriter(mFileWriter,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
        ) {
            //String[] headerRecord = {"Name", "Email", "Phone", "Country"};
            //csvWriter.writeNext(headerRecord);

            //csvWriter.writeNext(new String[]{"Sundar Pichai ♥", "sundar.pichai@gmail.com", "+1-1111111111", "India"});
            csvWriter.writeNext(new String[]{newUser.getName(), newUser.getEmail(), newUser.getPassword(), String.valueOf(newUser.checkAdmin())});
            return newUser;
        }

    }
    public static User getUser(String email){
        //Fake data right here we need to get read from a file to retrieve this info and return it back.
        User nex = new User("Yan","yannarojc@outlook.com","123456",true);
        return nex;
    }
    public static movie[] getAllMovie(){
        movie[] aList = [];
        return aList;

    }
}
