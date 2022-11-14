
package Controller;

import Entity.*;
import java.util.*;
import java.io.IOException;

public class ReviewController {
    // add review
    /**
     * @param newReview review object to be added into database
     
     * @throws IOException
     * Function insert into review csv and update the movie csv with the new ratings
     */
    public static void addReview(Review newReview) throws IOException {
        DatabaseController.insertReview(newReview);
        int movieID = newReview.getMovieId();
        movie targetMovie = DatabaseController.getMoviebyId(movieID);
        float overallRating = getOverallRating(targetMovie);
        DatabaseController.updateOverallRating(movieID, overallRating);

    }


    // check if review by customer already exists
    /**
     * @param movieID Unique ID of the movie
     * @param userEmail Unique Id for the user
     * @return a boolean that checks whether User is writing duplicate reviews
     */
    public static boolean doesReviewExist(int movieID, String userEmail) {
        ArrayList<Review> reviewList = DatabaseController.getReviews(movieID);
        for (Review review : reviewList) {
            if (review.getUserEmail().equals(userEmail)) {
                return true;
            }
        }
        return false;
    }


    // get overall rating for movie
    /**
     * @param targetMovie movie that we be calculating and updating review
     * @return a float that is the targetMovie new rating.
     */
    public static float getOverallRating(movie targetMovie) {
        float totalScore = 0;
        int count = 0;
        if (targetMovie.getPastReviews() == null) {
            return -1;
        }
        for (Review review : targetMovie.getPastReviews()) {
            totalScore += review.getRating();
            count++;
        }
        return totalScore / count;

    }
}