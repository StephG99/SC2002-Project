package Controller;

import java.util.*;
import Entity.*;

public class BookingController {
    // import relevant methods from DatabaseController
    public static ArrayList<Shows> getAllShows() {
        return DatabaseController.getAllShows();
    }

    public static Shows getShow(int showID) {
        return DatabaseController.getShow(showID);
    }

    public static ArrayList<showSeat> getShowSeats(int showId) {
        return DatabaseController.getShowSeats(showId);
    }

    public static void bookShow(Cinema chosenCinema, Shows chosenShow, double price, ArrayList<Integer> seatIDs) {

    }

}