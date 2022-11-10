package View;

import Helper.Helper;
import Entity.*;
import Controller.BookingController;
import Controller.MovieController;

import java.util.*;

public class BookMoviePage {
    // display header
    public static void displayBookingHeader() {
        Helper.line(80, "=");
        System.out.println("Booking Menu");
        Helper.line(80, "=");
        int option = 0;
        do {
            System.out.println("1. View Movie List");
            System.out.println("2. Book Ticket");
            System.out.println("3. Back");
            option = Helper.readInt("Enter your choice: ");
            if (option == 1) {
                ArrayList<movie> MovieList = MovieController.getAllMovie();
                ViewMoviePage.printSimplifiedView(MovieList);
            } else if (option == 2) {
                printShowtimes();
            } else {
                System.out.println("redirecting to home page");
            }

        } while (option != 3);
    }

    // show available timings for selected movie and cinema
    public static void printShowtimes() {
        ArrayList<Shows> showList = BookingController.getAllShows();
        int movieID = 0;
        int count = 0;
        movieID = Helper.readInt("Enter movie ID: ");
        boolean isFound = false;
        for (Shows showtime : showList) {
            if (showtime.getMovieId() == movieID) {
                isFound = true;
                count++;
                System.out.println(count + ") Show ID: " + showtime.getShowId());
                System.out.println("Cinema ID: " + showtime.getCinemaId());
                System.out.println("Showtime: " + showtime.getTiming());
                System.out.println();
            }
        }
        if (!isFound) {
            System.out.println("No showtimes were found for selected movie. ");
        } else {
            int showChoice = 0;
            showChoice = Helper.readInt("Enter your preferred showtime ID: ");
            printShowSeats(showChoice);
        }
    }

    // show available seats for selected showtime
    public static void printShowSeats(int showID) {
        Shows chosenShow = BookingController.getShow(showID);
        System.out.println("Your chosen timeslot: " + chosenShow.getTiming());
        System.out.println("Number of available seats: " + chosenShow.getSeatsAvailability());
        ArrayList<showSeat> listShowSeats = BookingController.getShowSeats(showID);
        
    }

}