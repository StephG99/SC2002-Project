package Controller;

import java.util.*;
import Entity.*;

public class BookingController{
    // import relevant methods from DatabaseController
    public static ArrayList<Shows> getAllShows(){
        return DatabaseController.getAllShows();
    }
    public static ArrayList<showSeat> getShowSeats(int showId){
        return DatabaseController.getShowSeats(showId);
    }

    public static void bookShow(int movieId, int cinemaID, int showId, Date bookingDate, double price, ArrayList<Integer> seatIDs) {

    }



}