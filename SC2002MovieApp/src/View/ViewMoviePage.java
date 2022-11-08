package View;
import Helper.Helper;
//import Helper from "../src/Helper.java"
import Entity.*;

import java.util.ArrayList;
import java.util.Collections;

import Controller.MovieController;
public class ViewMoviePage{
    public static void printSingleMovie(movie Movie) {
        System.out.println("Movie ID: " + Movie.getMovieID());
        System.out.println("Movie Title: " + Movie.getTitle());
        System.out.println("Movie Type: " + Movie.getMovieType());
        System.out.println("Viewer Advisory Rating: " + Movie.getAdvisoryRating());
        System.out.println("Movie Status: " + Movie.getStatus());
        System.out.println("Synopsis: " + Movie.getSynopsis());
        System.out.println("Director: " + Movie.getDirector());
        System.out.println("Cast: " + Movie.getCastList());
        System.out.println("Overall Rating Score: " + Movie.getOverallRating());
    }

    // print movie list
    public static void printMovieList() {
        ArrayList<movie> MovieList = MovieController.getAllMovie();
        for (int i = 0; i < MovieList.size(); i++) {
            printSingleMovie(MovieList.get(i));
            System.out.println();
        }
    }

    // print individual movie details by title
    public void printSingleMovieByTitle(ArrayList<movie> MovieList, String targetTitle) {
        boolean isFound = false;
        for (movie Movie : MovieList) {
            if (Movie.getTitle() == targetTitle) {
                isFound = true;
                System.out.println("Movie title found. Details of movie as follows: ");
                System.out.println();
                printSingleMovie(Movie);
                break;
            }
        }
        if (!isFound)
            System.out.println("Movie title does not match existing movies. ");

    }

    // print individual movie details by ID
    public void printSingleMovieByID(ArrayList<movie> MovieList, int targetID) {
        boolean isFound = false;
        for (movie Movie : MovieList) {
            if (Movie.getMovieID() == targetID) {
                isFound = true;
                System.out.println("Movie ID found. Details of movie as follows: ");
                System.out.println();
                printSingleMovie(Movie);
                break;
            }
        }
        if (!isFound)
            System.out.println("Movie ID does not match existing movies. ");
    }

    // sort movie by overallRating (then list top 5)
    public void printTopFiveMovies(ArrayList<movie> MovieList) {
        ArrayList<Double> ratingScoreList = new ArrayList<Double>(MovieList.size());
        for (movie Movie : MovieList) {
            ratingScoreList.add(Movie.getOverallRating());
        }
        // Sort rating Score by descending order so the top 5 rated movies come first
        Collections.sort(ratingScoreList, Collections.reverseOrder());
        for (int i = 0; i < 5; i++) {
            for (Double score : ratingScoreList) {
                for (movie Movie : MovieList) {
                    if (Movie.getOverallRating() == score) {
                        printSingleMovie(Movie);
                        break;
                    }
                }
            }
        }

    }

    // filter only by blockbuster
    public void printBlockBuster(ArrayList<movie> MovieList) {
        for (movie Movie : MovieList) {
            if (Movie.isBlockBuster()) {
                printSingleMovie(Movie);
            }
        }
    }

    // filter only by status
    public void printMoviesByStatus(ArrayList<movie> MovieList, int targetStatus) {
        boolean isValidInput = true;
        switch (targetStatus) {
            case 0:
                System.out.println("List of movies on Advance Sales: ");
                break;
            case 1:
                System.out.println("List of movies Now Showing: ");
                break;
            case 2:
                System.out.println("List of Movies Coming Soon: ");
                break;
            default:
                isValidInput = false;
                System.out.println("Invalid input!");
                break;
        }
        if (isValidInput) {
            for (movie Movie : MovieList) {
                if (Movie.getStatus() == targetStatus) {
                    printSingleMovie(Movie);
                }
            }
        }
    }

    // simplified view - list only movie ID and title
    public static void printSimplifiedView(ArrayList<movie> MovieList) {
        for (movie Movie : MovieList) {
            System.out.println("Movie ID " + Movie.getMovieID() + ": " + Movie.getTitle());
        }
    }

}