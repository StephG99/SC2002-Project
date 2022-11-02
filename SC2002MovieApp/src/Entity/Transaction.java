package Entity;

public class Transaction {
    private String transactionId;
    private String email;
    private int phoneNo;
    private String name;
    private int movieId;
    private int cineplexId;
    private int cinemaID;
    private float price;

    public Transaction(String transactionId, String email, int phoneNo, String name, int movieId, int cineplexId,
            int cinemaID, float price) {
        this.transactionId = transactionId;
        this.email = email;
        this.phoneNo = phoneNo;
        this.name = name;
        this.movieId = movieId;
        this.cineplexId = cineplexId;
        this.cinemaID = cinemaID;
        this.price = price;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMovieId() {
        return movieId;
    }
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    public int getCineplexId() {
        return cineplexId;
    }
    public void setCineplexId(int cineplexId) {
        this.cineplexId = cineplexId;
    }
    public int getCinemaID() {
        return cinemaID;
    }
    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    

}
