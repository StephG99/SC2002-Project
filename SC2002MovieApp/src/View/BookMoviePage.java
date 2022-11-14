package View;

import Helper.Helper;
import Entity.*;
import Controller.BookingController;
import Controller.MovieController;
import Controller.CinemaController;

import java.io.IOException;
import java.util.*;

public class BookMoviePage {

    /**
     * Displays the main booking menu
     * 
     * @param loginUser user object associated with bookings, will be passed into
     *                  subsequent functions later
     * @throws IOException
     */
    public static void BookingMenu(User loginUser) throws IOException {
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
                printShowtimes(loginUser);
            } else {
                System.out.println("redirecting to home page");
            }

        } while (option != 3);
    }

    /**
     * Booking menu interface to select a movie and get all of its showtimes
     * 
     * @param loginUser
     * @throws IOException
     */
    public static void printShowtimes(User loginUser) throws IOException {

        int movieID = 0;
        int count = 0;
        boolean movieAvailable = false;
        do {
            movieID = Helper.readInt("Enter movie ID: ");
            movie movieChoice = MovieController.searchById(movieID);
            if (movieChoice.getStatus() == 0 || movieChoice.getStatus() == 1) {
                movieAvailable = true;
            } else if (movieChoice.getStatus() == 2) {
                System.out.println("Movie is coming soon. You cannot book for this movie yet.");
            } else {
                System.out.println("Movie is unavailable.");
            }
        } while (!movieAvailable);
        boolean validResponse = false;
        ArrayList<Cineplex> cineplexList = BookingController.getCineplexByMovieId(movieID);
        int option = 0;
        do {
            System.out.println(
                    "Do you want to book a discounted tickets for senior/student?(Note that timing is only available between 1200 - 1800 hrs on normal weekdays(excluding PH) and only regular movie class) ");
            System.out.println("1.) Senior Ticket");
            System.out.println("2.) Student Ticket: ");
            System.out.println("3.) No discounted Tickets (Will display all available Movie)");
            option = Helper.readInt("Enter your option: ");
            if (option == 1) {
                validResponse = true;
            } else if (option == 2) {
                validResponse = true;
            } else if (option == 3) {
                validResponse = true;
            } else {
                System.out.println("Invalid Option!");
            }

        } while (!validResponse);
        ArrayList<Shows> showList = new ArrayList<Shows>();
        if (option == 3) {
            showList = BookingController.getShowsByMovieId(movieID);
        } else {
            showList = BookingController.getShowsByMovieId(movieID, option);
        }
        ArrayList<Integer> foundShows = new ArrayList<Integer>();
        System.out.println("It is available at");
        for (Cineplex cine : cineplexList) {
            System.out.println(cine.getCineName());
        }

        for (Shows showtime : showList) {
            // I might change this line so that we do the logic processing at the controller
            // side instead of view page.
            foundShows.add(showtime.getShowId());
            count++;
            Cinema cinema = CinemaController.getCinema(showtime.getCinemaId());
            System.out.println(count + ") Show ID: " + showtime.getShowId());
            System.out.println("Cinema ID: " + showtime.getCinemaId());
            System.out.println("Cinema Class: " + cinema.getMovieClass().getClassName());
            System.out.println("Showtime: " + showtime.getTiming());
            System.out.println("Seats available: " + showtime.getSeatsAvailability());
            System.out.println();

        }
        if (showList.size() == 0) {
            System.out.println("No showtimes were found for selected movie. ");
        } else {
            int showChoice = 0;
            // we try to link it by ourself and not let user do the work.
            showChoice = Helper.readInt("Enter your preferred choice, or enter any other number to quit: ");
            if (showChoice > 0 && showChoice < showList.size()) {
                int showId = foundShows.get(showChoice - 1);
                if (foundShows.contains(showId)) {
                    printShowSeats(showId, loginUser, option);
                } else {
                    System.out.println("Returning to previous menu.");
                }
            } else {
                System.out.println("Exiting....");
            }
        }
    }

    /**
     * Displays seat layout and shows the available seats for the selected showtime
     * 
     * @param showID    unique ID for the showtime
     * @param loginUser user object
     * @param Option    option for ticket type
     * @throws IOException
     */
    public static void printShowSeats(int showID, User loginUser, int Option) throws IOException {
        Shows chosenShow = BookingController.getShow(showID);
        Cinema chosenCinema = CinemaController.getCinema(chosenShow.getCinemaId());
        movie chosenMovie = MovieController.searchById(chosenShow.getMovieId());
        System.out.println("You have chosen the following movie: ");
        System.out.println();
        ViewMoviePage.printSingleMovie(chosenMovie);
        System.out.println();
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
        boolean answer = Helper.readBoolean("Enter your answer: ");
        if (answer) {
            seatBookingView(chosenCinema, chosenShow, listShowSeats, loginUser, Option);
        } else {
            System.out.println("Returning to previous menu.");
        }

    }

    //
    // once user confirms they would like to make a booking, then this function
    // will be called
    /**
     * Seat selection view, allows user to choose seats to book and then proceed to
     * payment
     * 
     * @param chosenCinema  cinema selected for the showtime
     * @param chosenShow    selected showtime
     * @param listShowSeats arraylist of showseats for the selected showtime
     * @param loginUser     user object
     * @param option        option chosen for the senior/student/normal ticket
     * @throws IOException
     */
    public static void seatBookingView(Cinema chosenCinema, Shows chosenShow, ArrayList<showSeat> listShowSeats,
            User loginUser, int option) throws IOException {
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
                } else if (chosenSeats.contains(choice)) {
                    System.out.println("This seat is already in your selection!");
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
            System.out.println("Cinema Class: " + chosenCinema.getMovieClass().getClassName());
            double ticketPrice = BookingController.getPrice(chosenShow, loginUser, option);
            System.out.println("Price for each ticket: " + ticketPrice);
            double totalPrice = chosenSeats.size() * ticketPrice;
            System.out.println("Total price: " + totalPrice);
            if (loginUser == null) {
                String email = Helper.readString("Enter your email: ");
                int phoneNo = Helper.readInt("Enter your mobile Number");
                // Guest User creation
                loginUser = new User(email, phoneNo);
            }
            int confirmChoice = 0;
            confirmChoice = Helper.readInt("Enter 1 to confirm your booking, or any other number to reset.");
            if (confirmChoice == 1) {
                Transaction ticket = BookingController.bookShow(chosenCinema, chosenShow, totalPrice, chosenSeats,
                        loginUser);
                System.out.println("Thank you! Your booking has been confirmed.");
                System.out.println();
                ViewTransactionPage.printSingleTransaction(ticket);
                System.out.println();
            } else {
                System.out.println("Booking has been reset. Returning to booking menu.");
            }
        }

    }

}