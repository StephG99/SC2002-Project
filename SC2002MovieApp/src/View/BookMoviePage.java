package View;

import Helper.Helper;
import Entity.*;
import Controller.BookingController;

import java.util.*;

public class BookMoviePage {
    // display header
    public static void displayBookingHeader() {
        Helper.line(80, "=");
        System.out.println("Book a Movie Ticket");
        Helper.line(80, "=");
    }

    // show available timings for selected movie and cinema
    public static void printShowtimes(int movieId, int cinemaID) {
        ArrayList<Shows> showList = BookingController.getAllShows();
        for (Shows showtime : showList) {
            // incomplete
        }
    }

    // show available seats for selected showtime

}