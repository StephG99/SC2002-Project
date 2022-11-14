package Entity;

//import java.util.Arrays;

public class Cinema {
    private int cinemaID;
    private movieClass cinemaClass;

    /**
     * Constructor for Cinema object
     * 
     * @param cinemaID    unique ID number for object
     * @param cinemaClass indicates class of cinema
     */
    public Cinema(int cinemaID, movieClass cinemaClass) {
        this.cinemaID = cinemaID;
        this.cinemaClass = cinemaClass;
        /*
         * for (int i = 0; i < 10; i++) {
         * for (int j = 0; j < 10; j++) {
         * this.seats[i][j] = new Seat(((i * 10) + j + 1), cinemaID);
         * }
         * }
         */

    }

    /**
     * gets class of cinema object
     * 
     * @return cinemaClass
     */
    public movieClass getMovieClass() {
        return cinemaClass;
    }

    /**
     * gets unique ID number for cinema object
     * 
     * @return cinemaID
     */
    public int getCinemaID() {
        return cinemaID;
    }

}