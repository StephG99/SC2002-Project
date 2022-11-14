package View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import Controller.AdminController;
import Controller.MovieController;

import Controller.DatabaseController;
import Entity.Settings;
import Entity.movie;
import Entity.*;

import Helper.Helper;

public class AdminMoviePage {
    /**
     * Displays header for the admin page
     */
    public static void displayAdminHeader() {
        Helper.line(80, "=");
        System.out.println("Admin Movie Page");
        Helper.line(80, "=");
    }

    /**
     * Admin user interface to add a new showtime
     * 
     * @throws IOException
     */
    public static void addShow() throws IOException {
        displayAdminHeader();
        /*
         * private int movieId;
         * private String title;
         * private String synopsis;
         * private boolean blockBuster;
         * private String typeOfMovie;
         * private int status;
         * private String ViewerAdvisory;
         * private String director;
         * private ArrayList<String> cast;
         * private double overallRating;
         * private ArrayList<Review> pastReview;
         */
        // int cineplexId = Helper.readInt("Enter the CinePlex ID: ");
        int result = -1;
        System.out.println("List of Cineplexes: ");
        printCineplexList();
        int cinemaId = Helper.readInt("Enter the Cinema ID: ");
        ArrayList<movie> movieList = MovieController.getAllMovie();
        System.out.println("List of Movies: ");
        ViewMoviePage.printSimplifiedView(movieList);
        int movieId = Helper.readInt("Enter the movie Id: ");
        String dateOfShow = Helper
                .readString("Enter the date and time using this format dd/mm/yyyy/hh/min e.g 22/12/2022/22/30: ");
        do {
            result = AdminController.addShow(cinemaId, movieId, dateOfShow);
            if (result == 0) {
                System.out.println("Both Movie and Cinema could not be found please reenter the Id");
                cinemaId = Helper.readInt("Enter the Cinema ID: ");
                movieId = Helper.readInt("Enter the movie Id: ");

            } else if (result == 1) {
                System.out.println("Movie could not be found!");
                movieId = Helper.readInt("Enter the movie Id: ");
            } else if (result == 2) {
                System.out.println("Cinema could not be found");
                cinemaId = Helper.readInt("Enter the Cinema ID: ");
            } else if (result == 3) {
                System.out.println("Invalid dateOfShow");
                dateOfShow = Helper.readString(
                        "Enter the date and time using this format dd/mm/yyyy/hh/min e.g 22/12/2022/22/30: ");
            } else if (result == 4) {
                System.out.println("Show have been successfully added!");
            }

        } while (result != 4);

    }

    /**
     * Function to print out the list of all cineplexes available
     */
    public static void printCineplexList() {
        ArrayList<Cineplex> cineplexList = DatabaseController.getAllCineplex();
        for (Cineplex cineplex : cineplexList) {
            System.out.println(cineplex.getCineplexId() + ") " + cineplex.getCineName() + ": Cinemas : "
                    + getCinemaIDsList(cineplex.getCinemas()) + ", Classes Offered: "
                    + getClassIDsList(cineplex.getClassOffered()));
        }
    }

    /**
     * Fetches an array list of cinema ID numbers to be passed to PrintCineplexList
     * 
     * @param cinemas
     * @return
     */
    public static ArrayList<Integer> getCinemaIDsList(ArrayList<Cinema> cinemas) {
        ArrayList<Integer> newList = new ArrayList<Integer>();
        for (Cinema cine : cinemas) {
            newList.add(cine.getCinemaID());
        }
        return newList;
    }

    /**
     * Fetches an array list of movie class IDs to be passed to printCineplexList
     * 
     * @param classIDs
     * @return
     */
    public static ArrayList<Integer> getClassIDsList(ArrayList<movieClass> classIDs) {
        ArrayList<Integer> newList = new ArrayList<Integer>();
        for (movieClass mclass : classIDs) {
            newList.add(mclass.getClassId());
        }
        return newList;
    }

    /**
     * Admin user interface to display current system settings
     * 
     * @throws IOException
     */
    public static void editSettings() throws IOException {
        displayAdminHeader();
        Settings result = AdminController.getCurrentSettings();
        printSettings(result, "Current Settings");

        boolean option = true;
        do {
            option = Helper.readBoolean("Would you like to make any changes(Y/N)");
            if (!option) {
                break;
            }
            result = EditSettingPage(result);
            option = false;
        } while (option);
        System.out.println("...Updating New Settings Change");
        AdminController.UpdateSetting(result);
    }

    /**
     * Admin user interface to choose a system setting to edit
     * 
     * @param setting Settings class object to be edited
     * @return
     */
    public static Settings EditSettingPage(Settings setting) {
        System.out.println("Which would you like to change?");

        int option = 0;
        do {
            System.out.println("1.) Regular Price");
            System.out.println("2.) Senior Price");
            System.out.println("3.) Student Price");
            System.out.println("4.) Holiday Price");
            System.out.println("5.) Holiday Dates");
            System.out.println("6.) Done");
            option = Helper.readInt("Enter your choice");
            switch (option) {
                case 1:
                    System.out.println("Current regular price: " + setting.getRegularRates());
                    setting.setRegular(Helper.readDouble("Enter new Regular Rate: "));
                    break;
                case 2:
                    System.out.println("Current senior price: " + setting.getSeniorRates());
                    setting.setSeniorRates(Helper.readDouble("Enter new Senior Rate: "));
                    break;
                case 3:
                    System.out.println("Current student price: " + setting.getStudentRates());
                    setting.setStudentRates(Helper.readDouble("Enter new Student Rate: "));
                    break;
                case 4:
                    System.out.println("Current holiday price: " + setting.getHolidayRates());
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

        } while (option != 6);

        return setting;

    }

    /**
     * Admin user interface to edit list of public holidays
     * 
     * @param publicHolidays
     * @return
     */
    private static ArrayList<Date> editPublicHoliday(java.util.ArrayList<Date> publicHolidays) {
        int option = 0;
        while (option != 3) {
            int count = 0;
            System.out.println("Current Dates");
            for (Date date : publicHolidays) {
                count++;
                System.out.println(count + ")" + date);
            }
            System.out.println("1.) Add dates");
            System.out.println("2.) Remove dates");
            System.out.println("3.) Exit");
            option = Helper.readInt("Enter your option: ");
            if (option == 1) {
                String response = "";
                while (!response.equals("-1")) {
                    Date dateToAdd = null;
                    response = Helper.readString("Add date format DD-MM-YYYY, e.g 25-12-2022 type -1 to quit: ");
                    if (response.equals("-1")) {
                        break;
                    } else {
                        dateToAdd = Helper.customDateBuilder(response);
                        if (publicHolidays.contains(dateToAdd)) {
                            System.out.println("Invalid Response");
                        } else {
                            publicHolidays.add(dateToAdd);
                        }
                    }

                }
            } else if (option == 2) {

                int choice = 1;
                while (choice > 0 && choice < publicHolidays.size() + 1) {
                    System.out.println("Which date to remove? any other number to exit: ");
                    int count1 = 0;
                    System.out.println("Current Dates");
                    for (Date date : publicHolidays) {
                        count1++;
                        System.out.println(count1 + ")" + date);
                    }
                    choice = Helper.readInt("Enter the Index of the date you be deleting: ");
                    if (choice > 0 && choice < publicHolidays.size() + 1) {
                        publicHolidays.remove(choice - 1);
                    } else {
                        System.out.println("Exiting remove");
                    }

                }
            } else if (option == 3) {
                System.out.println("Exiting");
            } else {
                System.out.println("Invalid Option");
            }

        }
        return publicHolidays;
    }

    /**
     * Method to print out current list of system settings
     * 
     * @param result
     * @param Header
     */
    public static void printSettings(Settings result, String Header) {

        System.out.println(Header);
        System.out.println("Regular Price: $" + result.getRegularRates());
        System.out.println("Senior Price: $" + result.getSeniorRates());
        System.out.println("Student Price: $" + result.getStudentRates());
        System.out.println("Holiday Price: $" + result.getHolidayRates());
        System.out.println("Current Holidays");
        for (Date holiday : result.getPublicHolidays()) {
            System.out.println(holiday);
        }
        System.out.println();
    }

    /**
     * Admin user interface to choose a movie to edit details
     * 
     * @throws IOException
     */
    public static void editMovieDetails() throws IOException {
        displayAdminHeader();
        ArrayList<movie> movieList = MovieController.getAllMovie();
        // ViewMoviePage.printSimplifiedView(movieList);
        int option = 0;
        do {
            ViewMoviePage.printSimplifiedView(movieList);
            option = Helper.readInt("Enter the Movie ID that you would like to edit(Enter any other number to exit): ");
            if (option > movieList.size() || option < 1) {
                System.out.println("Exiting...");
                break;
            }

            ViewMoviePage.printSingleMovie(movieList.get(option - 1));

            boolean choice = Helper.readBoolean("Would you like to edit this movie(Y/N)? ");
            if (choice) {
                editMovie(movieList, option - 1);
            } else {
                System.out.println("going back");
                option = 1;
            }

        } while (option < movieList.size() && option > 0);

    }

    /**
     * Admin user interface to edit specific details of a chosen movie
     * 
     * @param movieList list of all movies
     * @param index     index ID of chosen movie
     * @throws IOException
     */
    public static void editMovie(ArrayList<movie> movieList, int index) throws IOException {
        // Why we take in an array instead of one movie is so that we can shortcut the
        // updating process
        // we take out first from the movieList later we are going to set it back.
        movie edit = movieList.get(index);
        int choice = 0;
        do {
            System.out.println();
            System.out.println("What would you like to edit? ");
            System.out.println("1.) Edit Title");
            System.out.println("2.) Edit Sypnosis");
            System.out.println("3.) Edit Blockbuster Status ");
            System.out.println("4.) Edit type of movie");
            System.out.println("5.) Edit Showing Status");
            System.out.println("6.) Edit Viewer Advisory");
            System.out.println("7.) Exit");
            choice = Helper.readInt("");
            switch (choice) {
                case 1:
                    edit.setTitle(Helper.readString("New title: "));
                    break;
                case 2:
                    edit.setSynopsis(Helper.readString("New Sypnosis: "));
                    break;
                case 3:
                    edit.setBlockBuster(Helper.readBoolean("Blockbuster?(Y/N) "));
                    break;
                case 4:
                    edit.setMovieType("Movie type: Regular,3D");
                    break;
                case 5:
                    edit.setStatus(Helper.readInt(
                            "new Movie Status\n1.)Preview\n2.)Now Showing\n3.)Coming Soon\n4.)End Of Showing\n ") - 1);
                    break;
                case 6:
                    edit.setAdvisoryRating(Helper.readString("Set Viewer Advisory(PG,NC16,M18,R21): "));
                    break;
                case 7:
                    System.out.println("Exit");

            }
        } while (choice != 7);
        movieList.set(index, edit);
        AdminController.updateMovie(movieList);

    }

}
