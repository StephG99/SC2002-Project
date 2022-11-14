package Entity;

import java.util.*;

public class Transaction {
    private String transactionId;
    private String email;
    private int phoneNo;
    private String name;
    private int movieId;
    private int cineplexId;
    private int cinemaID;
    private ArrayList<Integer> seatID;
    private Date timing;
    private double price;
    /**
     * 
     * @param transactionId Unique Transaction ID based on requirement
     * @param email Email of User who book the ticket
     * @param phoneNo Phone no of User who book the ticket
     * @param name Movie Name 
     * @param movieId Movie ID
     * @param cineplexId Cineplex ID of cineplex
     * @param cinemaID Hall of Cineplex
     * @param seatID Seat booked
     * @param timing Timing of movie
     * @param price Total price paid by user
     * Constructor for a transaction object to be inserted into database
     */
    public Transaction(String transactionId, String email, int phoneNo, String name, int movieId, int cineplexId,
            int cinemaID, ArrayList<Integer> seatID, Date timing, double price) {
        this.transactionId = transactionId;
        this.email = email;
        this.phoneNo = phoneNo;
        this.name = name;
        this.movieId = movieId;
        this.cineplexId = cineplexId;
        this.cinemaID = cinemaID;
        this.seatID = seatID;
        this.timing = timing;
        this.price = price;
    }
    /**
     * 
     * @return transaction Id
     */
    public String getTransactionId() {
        return transactionId;
    }
    /**
     * 
     * @param transactionId new Transaction ID
     * Set new Transaction ID 
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    /**
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }
    /**
     * 
     * @param email new Email to set for ticket
     * 
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * 
     * @return phone No
     */
    public int getPhoneNo() {
        return phoneNo;
    }
    /**
     * 
     * @param phoneNo new phone no
     * set new phone no
     */
    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }
    /**
     * 
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * 
     * @param name new Movie Title
     * set new movie title for transaction
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 
     * @return movieId
     */
    public int getMovieId() {
        return movieId;
    }

    /**
     * 
     * @param movieId new Movie ID to set
     * set movieId
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    /**
     * 
     * @return cineplexId
     */
    public int getCineplexId() {
        return cineplexId;
    }
    /**
     * 
     * @param cineplexId new cineplexId
     * set new cineplex ID
     */
    public void setCineplexId(int cineplexId) {
        this.cineplexId = cineplexId;
    }
    /**
     * 
     * @return cinemaId
     */
    public int getCinemaID() {
        return cinemaID;
    }
    /**
     * 
     * @param cinemaID new cinema Id
     * set new CinemaID
     */
    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }
    /**
     * 
     * @return seatId
     */
    public ArrayList<Integer> getSeatID() {
        return seatID;
    }
    /**
     * 
     * @param seatID new SeatID
     * set new seat for transaction
     */
    public void setSeatID(ArrayList<Integer> seatID) {
        this.seatID = seatID;
    }
    /**
     * 
     * @return timing
     */
    public Date getTiming() {
        return timing;
    }
    /**
     * 
     * @param timing new Timing for transaction
     */
    public void setTiming(Date timing) {
        this.timing = timing;
    }
    /**
     * 
     * @return price paid by user
     */
    public double getPrice() {
        return price;
    }
    /**
     * 
     * @param price set the new price
     */
    public void setPrice(double price) {
        this.price = price;
    }

}
