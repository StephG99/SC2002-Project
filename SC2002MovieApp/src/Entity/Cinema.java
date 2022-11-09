package Entity;

//import java.util.Arrays;

public class Cinema {
    private int cinemaID;
    private movieClass cinemaClass;
    private Seat[][] seats = new Seat[10][10];

    public Cinema(int cinemaID, movieClass cinemaClass) {
        this.cinemaID = cinemaID;
        this.cinemaClass = cinemaClass;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.seats[i][j] = new Seat(((i * 10) + j + 1), cinemaID);
            }
        }

    }

    public int getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    public int getSeatsAvailable() {
        int counter = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!seats[i][j].isOccupied()) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public movieClass getCinemaClass() {
        return cinemaClass;
    }

    public void setCinemaClass(movieClass cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

}