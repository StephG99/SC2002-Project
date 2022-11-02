package Entity;

import java.util.Date;

public class Shows {
    private int movieId;
    private int cinemaID;
    private Date timing[];
   
    public Shows(int movieId, int cinemaID, Date[] timing) {
        this.movieId = movieId;
        this.cinemaID = cinemaID;
        this.timing = timing;
    }
    //Method returns Pairing of movieID along with cinemaID.
    public int getShowId(){
        String result= String.valueOf(movieId) + String.valueOf(cinemaID);
        return Integer.parseInt(result);
    }

    public Date[] getTiming() {
        return timing;
    }
    public void setTiming(Date[] timing) {
        this.timing = timing;
    }
  
   
    

    
}
