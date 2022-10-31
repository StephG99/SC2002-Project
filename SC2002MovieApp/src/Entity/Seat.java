package Entity;

import java.util.Arrays;

public class Seat {
    private int seatID;
    private int cinemaID;
    private boolean assigned;

    public Seat(int seatID, int cinemaID){ 
        this.seatID = seatID;
        this.cinemaID = cinemaID;
        this.assigned = false;
    }

    public int getSeatID() {
        return seatID;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public boolean isOccupied() {
        return assigned;
    }

    public void assignSeat() {
        this.assigned = true;
    }

    public void unassignSeat() {
        this.assigned = false;
    }

}
