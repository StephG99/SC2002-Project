package Controller;

import Entity.*;
import java.util.*;

public class ReviewController {
    // add review
    public static void addReview(movie targetMovie, int rating, String reviewText, String userEmail) {
        Review newReview = new Review(targetMovie.getMovieID(),rating, reviewText, userEmail);
        targetMovie.getPastReviews().add(newReview);
        System.out.println("Review successfully added");
    }

    // remove review
    public static void removeReview(movie targetMovie, String userEmail) {
        boolean exists = false;
        for (Review review : targetMovie.getPastReviews()) {
            if (review.getUserEmail().equals(userEmail)) {
                exists = true;
                targetMovie.getPastReviews().remove(review);
                System.out.println("Review successfully removed");
                break;
            }
        }
        if (!exists) {
            System.out.println("Review does not exist");
        }
    }

    // check if review by customer already exists
    public static Review searchReview(movie targetMovie, String userEmail) {
        for (Review review : targetMovie.getPastReviews()) {
            if (review.getUserEmail().equals(userEmail)) {
                return review;
            }
        }
        return null;
    }

    // update review
    public static void updateReview(Review targetReview, int rating, String reviewText) {
        targetReview.setRatingScore(rating);
        targetReview.setReviewText(reviewText);
        System.out.println("Review updated successfully");
    }

    // print single review
    public static void printSingleReview(Review review) {
        System.out.println("User: " + review.getUserEmail());
        System.out.println("Review: " + review.getReviewText());
        System.out.println("Rating Score: " + review.getRating());
    }

    // print all reviews by movie
    public void printMovieReviews(movie Movie) {
        for (Review review : Movie.getPastReviews()) {
            printSingleReview(review);
            System.out.println();
        }
        if (Movie.getPastReviews() == null) {
            System.out.println("No reviews have been made for this movie");
        }
    }

    // print all reviews by customer
    public void printCustomerReviews(ArrayList<movie> MovieList, String userEmail) {
        boolean exists = false;
        for (movie Movie : MovieList) {
            for (Review review : Movie.getPastReviews()) {
                if (review.getUserEmail().equals(userEmail)) {
                    exists = true;
                    printSingleReview(review);
                }
            }
        }
        if (!exists) {
            System.out.println("User has not made any reviews yet");
        }
    }

    // get overall rating for movie
    public float getOverallRating(movie targetMovie) {
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