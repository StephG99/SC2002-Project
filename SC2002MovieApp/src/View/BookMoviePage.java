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
        ArrayList<Integer> foundShows = new ArrayList<Integer>();
        for (Shows showtime : showList) {
            if (showtime.getMovieId() == movieID) {
                isFound = true;
                count++;
                foundShows.add(showtime.getShowId());
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
            showChoice = Helper.readInt("Enter your preferred showtime ID, or enter any other number to quit: ");
            if (foundShows.contains(showChoice)) {
                printShowSeats(showChoice);
            } else {
                System.out.println("Returning to previous menu.");
            }
        }
    }

    // show available seats for selected showtime
    public static void printShowSeats(int showID) {
        Shows chosenShow = BookingController.getShow(showID);
        System.out.println("Your chosen timeslot: " + chosenShow.getTiming());
        System.out.println("Number of available seats: " + chosenShow.getSeatsAvailability());
        ArrayList<showSeat> listShowSeats = BookingController.getShowSeats(showID);
        System.out.println();
        Helper.line(80, "=");
        System.out.println();
        System.out.println("==========SCREEN==========");
        System.out.println();
        System.out.println("  1 2 3 4 5 --- 6 7 8 9 10");
        int seatIndex = 0;
        showSeat tempSeat = null;
        for (int i = 0; i < 10; i++) {
            System.out.printf(i + " ");
            for (int j = 0; j < 5; j++) {
                seatIndex = (i * 10) + j;
                tempSeat = listShowSeats.get(seatIndex);
                if (tempSeat.isOccupied()) {
                } else {
                    System.out.printf("O ");
                }
            }
            System.out.printf("| | ");
            for (int k = 5; k < 10; k++) {
                seatIndex = (i * 10) + k;
                tempSeat = listShowSeats.get(seatIndex);
                if (tempSeat.isOccupied()) {
                } else {
                    System.out.printf("O ");
                }

            }
            System.out.printf("\n");
        }
    }

}