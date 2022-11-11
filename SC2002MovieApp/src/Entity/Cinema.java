package Entity;

//import java.util.Arrays;

public class Cinema {
    private int cinemaID;
    private movieClass cinemaClass;
    

    public Cinema(int cinemaID,movieClass cinemaClass) {
        this.cinemaID = cinemaID;
        this.cinemaClass = cinemaClass;
       /*  for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.seats[i][j] = new Seat(((i * 10) + j + 1), cinemaID);
            }
        }*/

    }
    public movieClass getMovieClass(){
        return cinemaClass;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    

}