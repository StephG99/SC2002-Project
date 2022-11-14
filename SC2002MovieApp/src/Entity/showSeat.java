package Entity;

public class showSeat extends Seat {
    

    private int showId;
    private boolean assigned;
    /**
     * 
     * @param seatID seatID to be assigned
     * @param cinemaID cinemaID to be assigned
     * @param showId Show ID to tag to show
     * auto create seats based on parent class
     */
    public showSeat(int seatID, int cinemaID,int showId) {
        super(seatID, cinemaID);
        this.showId = showId;
        assigned = false;
        
    }
    /**
     * 
     * @param seatID SeatID of the seats
     * @param cinemaID Cinema ID for tagging to cinema
     * @param showId show ID to get the show
     * @param assigned setting the assigned to the new value
     * this is for when we are updating the seat in database.
     */
    public showSeat(int seatID, int cinemaID,int showId,boolean assigned) {
        super(seatID, cinemaID);
        this.showId = showId;
        this.assigned = assigned;
        
    }
    /**
     * 
     * @return show Id
     */
    public int getShowId(){
        return showId;
    }
    /**
     * 
     * @param showId showId change show
     */
    public void setShowId(int showId){
        this.showId = showId;
    }
    

    /**
     * 
     * @return boolean of whether seat is occupied
     */
    public boolean isOccupied() {
        return assigned;
    }
    /**
     * change object to occupied
     */
    public void assignSeat() {
        this.assigned = true;
    }
    /**
     * change object to unoccupied
     */
    public void unassignSeat() {
        this.assigned = false;
    }

    
}
