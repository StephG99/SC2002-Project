package View;

import Helper.Helper;
import java.util.*;
import java.io.IOException;
import Entity.*;
import Controller.ReviewController;
import Controller.MovieController;

public class ReviewMoviePage {
    // display header
    public static void ReviewMenu(User loginUser) throws IOException {
        Helper.line(80, "=");
        System.out.println("Review Menu");
        Helper.line(80, "=");
        int option = 0;
        do {
            System.out.println("1. View Movie List");
            System.out.println("2. View Reviews");
            System.out.println("3. Make a Review");
            System.out.println("4. Back");
            option = Helper.readInt("Enter your choice: ");
            if (option == 1) {
                ArrayList<movie> MovieList = MovieController.getAllMovie();
                ViewMoviePage.printSimplifiedView(MovieList);
            } else if (option == 2) {
                ViewMoviePage.printReview();
            } else if (option == 3) {
                // leads to method to insert review
                publishReview(loginUser);
            } else {
                System.out.println("redirecting to home page");
            }

        } while (option != 4);
    }

    // review insertion display
    public static void publishReview(User loginUser) throws IOException {
        String email = loginUser.getEmail();
        int movieID = Helper.readInt("Enter movie ID: ");
        boolean validScore = false;
        int score;
        do {
            score = Helper.readInt("Enter your rating score for this movie (from 0 to 5): ");
            if (score >= 0 && score <= 5) {
                validScore = true;
            } else {
                System.out.println("Please enter a valid score!");
            }
        } while (!validScore);
        String reviewText = Helper.readString("Enter your review text here: ");
        Review newReview = new Review(movieID, score, reviewText, email);
        if (ReviewController.doesReviewExist(movieID, email)) {
            System.out.println("You have already reviewed this movie!");
        } else {
            ReviewController.addReview(newReview, movieID);
            System.out.println("Review successfully added!");
        }
    }
}