package Entity;

//import java.lang.reflect.Array;

//import java.util.Arrays;

public class Review {
    private int rating;
    private String reviewText;
    private String userEmail;

    public Review(int rating, String reviewText, String userEmail) {
        this.rating = rating;
        this.reviewText = reviewText;
        this.userEmail = userEmail;
    }

    public int getRating() {
        return rating;
    }

    public void setRatingScore(int score) {
        this.rating = score;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String review) {
        this.reviewText = review;
    }

    public String getUserEmail() {
        return userEmail;
    }

    // no setter method for userEmail because that variable is tied to your user
    // login details

}