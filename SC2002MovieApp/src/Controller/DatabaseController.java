package Controller;
//import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.ArrayList;
//import java.util.Scanner;
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
    //Insertion into database accepts a User object and append into csv.
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
    //Get a User by email address. if no user is found return NULL object.
    public static User getUser(String email){

        String line = "";  
        String splitBy = ",";
        User result = null;  
        try   
        {  
        //parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/user.csv"));  
        while ((line = br.readLine()) != null)   //returns a Boolean value  
        {  
        String[] user = line.split(splitBy);    // use comma as separator  
        if(user[1].equalsIgnoreCase(email)){
            result = new User(user[0],user[1],user[2],Boolean.parseBoolean(user[3]));
            break;
        }

        }  
        br.close();
        }   
        catch (IOException e)   
        {  
        e.printStackTrace();  
        } 
        return result;
    }
    public static ArrayList<movie> getAllMovie(){
        ArrayList<movie> nex = new ArrayList<movie>();
        String line = "";  
        String splitBy = ",";  
        try   
        {  
        //parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/movie.csv"));  
        while ((line = br.readLine()) != null)   //returns a Boolean value  
        {  
        String[] result = line.split(splitBy);    // use comma as separator  
         //nex.add(
        //System.out.println(result[8]);
        //System.out.println(result[8].split("|")[0]);
        nex.add(new movie(Integer.valueOf(result[0]),result[1],result[2],Boolean.parseBoolean(result[3]),result[4],Integer.valueOf(result[5]),result[6],result[7],decodeString(result[8]),Double.parseDouble(result[9]),decodeReviews(result[10])));

        

        }  
        br.close();
        }   
        catch (IOException e)   
        {  
        e.printStackTrace();  
        }  
        return nex;
        

    }
    public static void addMovie(movie newMovie){

    }
    public static ArrayList<Review> decodeReviews(String encodedString){
        ArrayList<Review> result = new ArrayList<Review>();
        String[] processMethod = encodedString.split(";");
        for(int i = 0; i< processMethod.length;i++){
          String[] temp = processMethod[i].split("|");
          result.add(new Review(Integer.valueOf(temp[0]),temp[1],temp[2]));
        }
        return result;
    
      }
    public static ArrayList<String> decodeString(String encodedString){
        ArrayList<String> result = new ArrayList<String>();
        String[] processMethod = encodedString.split("/");
        for(int i = 0; i< processMethod.length;i++){
           // System.out.println(processMethod[0]);
            //System.out.println(processMethod[i]);
          result.add(processMethod[i]);
        }
        return result;

    }
    public static String encodeString(ArrayList<String> decodedString){
        String result = "";
        //String[] processMethod = encodedString.split("/");
        for(int i = 0; i< decodedString.size();i++){
           // System.out.println(processMethod[0]);
            //System.out.println(processMethod[i]);
          result+=decodedString.get(i);
          if(i != decodedString.size()-1){
            result+="/";
          }
        }
        return result;

    }
}
