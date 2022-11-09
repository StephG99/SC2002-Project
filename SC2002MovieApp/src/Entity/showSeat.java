package Entity;

public class showSeat extends Seat {
    

    private int showId;
    private boolean assigned;
    public showSeat(int seatID, int cinemaID,int showId) {
        super(seatID, cinemaID);
        this.showId = showId;
        assigned = false;
        
    }
    public int getShowId(){
        return showId;
    }
    public void setShowId(int showId){
        this.showId = showId;
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
