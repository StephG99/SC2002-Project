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

    public static void bookShow(Cinema chosenCinema, Shows chosenShow, double price, ArrayList<Integer> seatIDs,User loginUser) {

    }
    public static ArrayList<Cineplex> getCineplexByMovieId(int movieId){
        ArrayList<Shows> showById = getShowsByMovieId(movieId);
        ArrayList<Integer> cinemaId  = new ArrayList<Integer>();
        ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
        for (Shows show:showById){
            if(!cinemaId.contains(show.getCinemaId())){
                cinemaId.add(show.getCinemaId());
            }
        }
        for(Integer cineId:cinemaId){
            cinemaList.add(DatabaseController.getCinema(cineId));
        }
        ArrayList<Cineplex> allCineplex = DatabaseController.getAllCineplex();
        ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
        for(Cineplex cineplex:allCineplex){
            for(Cinema temp:cinemaList){
                for(Cinema cineplexTemp:cineplex.getCinemas()){
                    if(temp.getCinemaID() == cineplexTemp.getCinemaID()){
                        if(!cineplexList.contains(cineplex)){
                            cineplexList.add(cineplex);
                        }
                    }
                }
                
            }
        }

        return cineplexList;
    }

    public static ArrayList<Shows> getShowsByMovieId(int movieID) {
        ArrayList<Shows> allShows = getAllShows();
        ArrayList<Shows> showById = new ArrayList<Shows>();
        for (Shows showTime : allShows) {
            if (showTime.getMovieId() == movieID) {
                showById.add(showTime);
            }
        }
        return showById;


    }

}