package Entity;

//import java.lang.reflect.Array;

//import java.util.Arrays;

public class Review {
    // private int reviewId;
    private int movieId;
    private int rating;
    private String reviewText;
    private String userEmail;

    /**
     * Constructor for Review class object
     * 
     * @param movieId    unique ID number for the movie this Review belongs to
     * @param rating     rating score given by a viewer
     * @param reviewText review text
     * @param userEmail  email of the user who wrote this Review
     */
    public Review(int movieId, int rating, String reviewText, String userEmail) {
        // this.reviewId = reviewId;
        this.movieId = movieId;
        this.rating = rating;
        this.reviewText = reviewText;
        this.userEmail = userEmail;
    }

    /**
     * Fetches unique ID number for the movie
     * 
     * @return
     */
    public int getMovieId() {
        return movieId;
    }

    /**
     * Fetches rating score given by the reviewer
     * 
     * @return
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets rating score of review to a new input value
     * 
     * @param score
     */
    public void setRatingScore(int score) {
        this.rating = score;
    }

    /**
     * Fetches the review text
     * 
     * @return
     */
    public String getReviewText() {
        return reviewText;
    }

    /**
     * Sets review text to a new input string
     * 
     * @param review
     */
    public void setReviewText(String review) {
        this.reviewText = review;
    }

    /**
     * Fetches the email of the user who published the review
     * 
     * @return
     */
    public String getUserEmail() {
        return userEmail;
    }

    // no setter method for userEmail because that variable is tied to your user
    // login details

}