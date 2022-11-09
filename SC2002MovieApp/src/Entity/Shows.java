package Entity;

import java.util.ArrayList;
import java.util.Date;

public class Shows {
    private int showId;
    private int movieId;
    private int cinemaId;
    private Date timing;
    private ArrayList<showSeat> seats;
   
    public Shows(int showId, int movieId, int cinemaId, Date timing,ArrayList<showSeat> seats) {
        this.showId = showId;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.timing = timing;
        this.seats = seats;
    }

    //Method returns Pairing of movieID along with cinemaID.
    public int getShowId(){
        //String result= String.valueOf(movieId) + String.valueOf(cinemaID);
        return showId;
    }
    public int getMovieId(){
        return movieId;
    }
    public int getCinemaId(){
        return cinemaId;
    }

    public Date getTiming() {
        return timing;
    }
    public void setTiming(Date timing) {
        this.timing = timing;
    }
    public int getSeatsAvailability(){
        int counter = 0;
        for (int i = 0; i< seats.size();i++){
            if(!seats.get(i).isOccupied()){
                counter++;
            }
        }
        return counter;
    }
  
   
    

    
}
