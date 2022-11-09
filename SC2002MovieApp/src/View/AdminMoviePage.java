package View;
import java.io.IOException;

import Controller.AdminController;
import Helper.Helper;

public class AdminMoviePage {
    public static void displayAdminHeader(){
        Helper.line(80, "=");
		System.out.println("Admin Movie Page");
		Helper.line(80, "=");
    }
    public static void addShow() throws IOException{
        displayAdminHeader();
        /* 
        private int movieId;
        private String title;
        private String synopsis;
        private boolean blockBuster;
        private String typeOfMovie;
        private int status;
        private String ViewerAdvisory;
        private String director;
        private ArrayList<String> cast;
        private double overallRating;
        private ArrayList<Review> pastReview;
        */
        //int cineplexId = Helper.readInt("Enter the CinePlex ID: ");
        int result = -1;
        int cinemaId = Helper.readInt("Enter the Cinema ID: ");
        int movieId = Helper.readInt("Enter the movie Id: ");
        String dateOfShow = Helper.readString("Enter the date and time using this format dd/mm/yyyy/hh/min e.g 22/12/2022/22/30: ");
        do{
            result = AdminController.addShow(cinemaId, movieId, dateOfShow);
            if(result == 0){
                System.out.println("Both Movie and Cinema could not be found please reenter the Id");
                cinemaId = Helper.readInt("Enter the Cinema ID: ");
                movieId = Helper.readInt("Enter the movie Id: ");

            }
            else if(result == 1){
                System.out.println("Movie could not be found!");
                movieId = Helper.readInt("Enter the movie Id: ");
            }
            else if(result == 2){
                System.out.println("Cinema could not be found");
                cinemaId = Helper.readInt("Enter the Cinema ID: ");
            }
            else if(result == 3){
                System.out.println("Invalid dateOfShow");
                dateOfShow = Helper.readString("Enter the date and time using this format dd/mm/yyyy/hh/min e.g 22/12/2022/22/30: ");
            }
            else if(result == 4){
                System.out.println("Show have been successfully added!");
            }
            
        

        }while(result != 4);
            
        

    }
    public static void editMovie(){
        displayAdminHeader();
    }
    public static void editSettings(){
        displayAdminHeader();
    }
    
}
