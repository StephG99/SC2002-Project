package View;

import Helper.Helper;
import Entity.*;
import Controller.BookingController;
import Controller.MovieController;
import Controller.CinemaController;

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
        Cinema chosenCinema = CinemaController.getCinema(chosenShow.getCinemaId());
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
        // iterative loop to print out the entire seat display
        for (int i = 0; i < 10; i++) {
            System.out.printf(i + " ");
            for (int j = 0; j < 5; j++) {
                seatIndex = (i * 10) + j;
                tempSeat = listShowSeats.get(seatIndex);
                if (tempSeat.isOccupied()) {
                    System.out.printf("X ");
                } else {
                    System.out.printf("O ");
                }
            }
            System.out.printf("| | ");
            for (int k = 5; k < 10; k++) {
                seatIndex = (i * 10) + k;
                tempSeat = listShowSeats.get(seatIndex);
                if (tempSeat.isOccupied()) {
                    System.out.printf("X ");
                } else {
                    System.out.printf("O ");
                }

            }
            System.out.printf("\n");
        }
        // end of seats display
        System.out.println("Would you like to proceed with seat booking? (Y/N)");
        char answer = Helper.readChar("Enter your answer: ");
        if (answer == 'Y') {
            seatBookingView(chosenCinema, chosenShow, listShowSeats);
        } else {
            System.out.println("Returning to previous menu.");
        }

    }

    //
    // once user confirms they would lik e to make a booking, then this function
    // will be called
    public static void seatBookingView(Cinema chosenCinema, Shows chosenShow, ArrayList<showSeat> listShowSeats) {
        // System.out.println("Test view");
        int choice = 0;
        ArrayList<Integer> chosenSeats = new ArrayList<Integer>();
        showSeat tempSeat = null;
        do {
            choice = Helper.readInt("Enter a seat number of your choice to book, or -1 to close your selection: ");
            if (choice > 0) {
                tempSeat = listShowSeats.get(choice - 1);
                if (tempSeat.isOccupied()) {
                    System.out.println("Seat is already occupied!");
                } else {
                    chosenSeats.add(choice);
                    System.out.println("Seat has been entered into selection.");
                }
            }
        } while (choice != -1);

        if (chosenSeats.isEmpty()) {
            System.out.println("You did not book any seats.");
        } else {
            System.out.println("You have chosen the following seats: ");
            System.out.println(chosenSeats);
            System.out.println("Cinema ID: " + chosenCinema.getCinemaID());
            System.out.println("Cinema Class: " + chosenCinema.getCinemaClass());
            double ticketPrice = chosenCinema.getCinemaClass().getPricePremium();
            System.out.println("Price for each ticket: " + ticketPrice);
            double totalPrice = chosenSeats.size() * ticketPrice;
            System.out.println("Total price: " + totalPrice);
            int confirmChoice = 0;
            confirmChoice = Helper.readInt("Enter 1 to confirm your payment, or any other number to reset.");
            if (confirmChoice == 1) {
                System.out.println("TEST: Booking confirmed!");
            } else {
                System.out.println("Booking has been reset. Returning to booking menu.");
            }
        }

    }

}