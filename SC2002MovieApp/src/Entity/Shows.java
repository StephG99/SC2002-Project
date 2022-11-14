package Entity;

import java.util.ArrayList;
import java.util.Date;

public class Shows {
    private int showId;
    private int cinemaId;
    private int movieId;
    private Date timing;
    private ArrayList<showSeat> seats;
    /**
     * 
     * @param showId showId
     * @param cinemaId cinemaId
     * @param movieId movieId
     * @param timing timing of show
     * @param seats array of seats available
     * construct a new show object and insert into database
     */
    public Shows(int showId, int cinemaId, int movieId, Date timing, ArrayList<showSeat> seats) {
        this.showId = showId;
        this.cinemaId = cinemaId;
        this.movieId = movieId;
        this.timing = timing;
        this.seats = seats;
    }

    /**
     * 
     * @return showId of show
     */
    public int getShowId() {
        // String result= String.valueOf(movieId) + String.valueOf(cinemaID);
        return showId;
    }
    /**
     * 
     * @return movie Id of show
     */
    public int getMovieId() {
        return movieId;
    }
      /**
     * 
     * @return cinema Id of show
     */
    public int getCinemaId() {
        return cinemaId;
    }
      /**
     * 
     * @return timing of show
     */
    public Date getTiming() {
        return timing;
    }
    /**
     * 
     * @param timing new Timing to be set
     */
    public void setTiming(Date timing) {
        this.timing = timing;
    }
    /**
     * 
     * @return a integer of the amount of seats available
     */
    public int getSeatsAvailability() {
        int counter = 0;
        for (int i = 0; i < seats.size(); i++) {
            if (!seats.get(i).isOccupied()) {
                counter++;
            }
        }
        return counter;
    }

}
