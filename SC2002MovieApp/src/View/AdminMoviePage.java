package View;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import Controller.AdminController;
import Entity.Settings;
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
    public static void editSettings() throws IOException{
        displayAdminHeader();
        Settings result = AdminController.getCurrentSettings();
        printSettings(result,"Current Settings");
        
        boolean option = true;
        do{
            option = Helper.readBoolean("Would you like to make any changes(Y/N)");
            if(!option){
                break;
            }
            result = EditSettingPage(result);
            option = false;
        }while(option);
        System.out.println("...Updating New Settings Change");
        AdminController.UpdateSetting(result);
    }
    public static Settings EditSettingPage(Settings setting){
        System.out.println("Which would you like to change?");
        
        int option =0;
        do{
            System.out.println("1.) Regular Price");
            System.out.println("2.) Senior Price");
            System.out.println("3.) Student Price");
            System.out.println("4.) Holiday Price");
            System.out.println("5.) Holiday Dates");
            System.out.println("6.) Done");
            option = Helper.readInt("Enter your choice");
            switch (option) {
                case 1:
                    setting.setRegular(Helper.readDouble("Enter new Regular Rate: "));
                    break;
                case 2:
                    setting.setSeniorRates(Helper.readDouble("Enter new Senior Rate: "));
                    break;
                case 3:
                    setting.setStudentRates(Helper.readDouble("Enter new Student Rate: "));
                    break;
                case 4:
                    setting.setHolidayRates(Helper.readDouble("Enter new Holiday Rate: "));
                    break;
                case 5:
                    ArrayList<Date> newDates = editPublicHoliday(setting.getPublicHolidays());
                    setting.setPublicHolidays(newDates);
                case 6:
                    System.out.println("Exiting Edit Settings");
                    break;
                default:
                    break;
            }

        }while(option !=6);

        return setting;


    }
    private static ArrayList<Date> editPublicHoliday(java.util.ArrayList<Date> publicHolidays) {
        int option =0;
        while(option != 3){
            int count = 0;
            System.out.println("Current Dates");
            for(Date date:publicHolidays){
                count++;
                System.out.println(count+")" +date);
            }
            System.out.println("1.) Add dates");
            System.out.println("2.) Remove dates");
            System.out.println("3.) Exit");
            option = Helper.readInt("Enter your option: ");
            if(option == 1){
                String response ="";
                while(!response.equals("-1")){
                    Date dateToAdd = null;
                    response = Helper.readString("Add date format DD/MM/YYYY, e.g 25-12-2022 type -1 to quit: ");
                    if(response.equals("-1")){
                        break;
                    }
                    else{
                        dateToAdd = Helper.customDateBuilder(response);
                        if(publicHolidays.contains(dateToAdd)){
                            System.out.println("Invalid Response");
                        }
                        else{
                            publicHolidays.add(dateToAdd);
                        }
                    }

                }
            }
            else if(option ==2){
                
                int choice = 1;
                while(choice > 0 && choice < publicHolidays.size()+1){
                    System.out.println("Which date to remove? any other number to exit: ");
                    int count1 = 0;
                    System.out.println("Current Dates");
                    for(Date date:publicHolidays){
                        count1++;
                    System.out.println(count1+")" +date);
            }
                    choice = Helper.readInt("Enter the Index of the date you be deleting: ");
                    if(choice > 0 && choice< publicHolidays.size()+1){
                    publicHolidays.remove(choice-1);
                    }
                    else{
                        System.out.println("Exiting remove");
                    }

                }
            }
            else if(option == 3){
                System.out.println("Exiting");
            }
            else{
                System.out.println("Invalid Option");
            }

        }
        return publicHolidays;
    }
    public static void printSettings(Settings result,String Header){
        
        
        System.out.println(Header);
        System.out.println("Regular Price: $"+ result.getRegularRates());
        System.out.println("Senior Price: $"+ result.getSeniorRates());
        System.out.println("Student Price: $"+ result.getStudentRates());
        System.out.println("Holiday Price: $"+ result.getHolidayRates());
        System.out.println("Current Holidays");
        for(Date holiday: result.getPublicHolidays()){
            System.out.println(holiday);
        }
        System.out.println();
    }
    
}
