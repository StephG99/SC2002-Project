package View;

import Helper.Helper;
//import Helper from "../src/Helper.java"
import Entity.*;

import java.util.ArrayList;
import java.util.Collections;

import Controller.MovieController;
import Controller.BookingController;
import Controller.DatabaseController;

public class ViewMoviePage {
    public static void printSingleMovie(movie Movie) {
        System.out.println("Movie ID: " + Movie.getMovieID());
        System.out.println("Movie Title: " + Movie.getTitle());
        System.out.println("Movie Type: " + Movie.getMovieType());
        System.out.println("Viewer Advisory Rating: " + Movie.getAdvisoryRating());
        if (Movie.getStatus() == 0) {
            System.out.println("Movie Status: ADVANCE SALES AVAILABLE");
        } else if (Movie.getStatus() == 1) {
            System.out.println("Movie Status: NOW SHOWING");
        } else if (Movie.getStatus() == 2) {
            System.out.println("Movie Status: COMING SOON");
        } else {
            System.out.println("Movie Status: UNAVAILABLE");
        }
        System.out.println("Synopsis: " + Movie.getSynopsis());
        System.out.println("Director: " + Movie.getDirector());
        System.out.println("Cast: " + Movie.getCastList());
        System.out.println("Overall Rating Score: " + Movie.getOverallRating());
    }

    // print movie list
    public static void printMovieList(User loginUser) {
        ArrayList<movie> MovieList = MovieController.getAllMovie();
        Helper.line(80, "=");
        System.out.println("List of All Movies");
        Helper.line(80, "=");
        for (int i = 0; i < MovieList.size(); i++) {
            printSingleMovie(MovieList.get(i));
            System.out.println();
        }

        int option = 0;
        do {
            System.out.println("1. View All Showtimes");
            System.out.println("2. View Review");
            System.out.println("3. Search Movie");
            System.out.println("4. View top 5 movie");
            System.out.println("5. View Blockbuster");
            System.out.println("6. Back");
            option = Helper.readInt("Enter your choice: ");
            if (option == 1) {
                printAllShowTimes();
            } else if (option == 2) {
                printReview();
            } else if (option == 3) {
                searchMovie();
                System.out.println("Search movie");
            } else if (option == 4) {
                Helper.line(80, "=");
                System.out.println("Top 5 movies (as rated by viewers)");
                Helper.line(80, "=");
                printTopFiveMovies(MovieList,loginUser);
            } else if (option == 5) {
                Helper.line(80, "=");
                System.out.println("List of Blockbusters");
                Helper.line(80, "=");
                printBlockBuster(MovieList);
            } else {
                System.out.println("redirecting to home page");
            }
        } while (option != 6);

    }

    // print all showtimes
    public static void printAllShowTimes() {
        Helper.line(80, "=");
        System.out.println("All Available Showtimes");
        Helper.line(80, "=");
        ArrayList<Shows> allShowsList = BookingController.getAllShows();
        // ArrayList<movie> movieList = MovieController.getAllMovie();
        ArrayList<Cineplex> cineplexList = DatabaseController.getAllCineplex();
        int count = 1;
        for (Cineplex cineplex : cineplexList) {
            System.out.println(cineplex.getCineName() + " Cineplex");
            System.out.println();
            for (int cinemaID : AdminMoviePage.getCinemaIDsList(cineplex.getCinemas())) {
                Cinema cinemaEntry = DatabaseController.getCinema(cinemaID);
                for (Shows oneShow : allShowsList) {
                    if (oneShow.getCinemaId() == cinemaID) {
                        movie movieEntry = MovieController.searchById(oneShow.getMovieId());
                        System.out.println(count + ") Show ID: " + oneShow.getShowId() + " | Cinema ID: " + cinemaID
                                + " | Class: " + cinemaEntry.getMovieClass().getClassName() + " | "
                                + oneShow.getTiming() + " | " + movieEntry.getTitle() + " ("
                                + movieEntry.getAdvisoryRating() + ")");
                        count++;
                    }
                }
            }
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
    public static void printTopFiveMovies(ArrayList<movie> MovieList,User user) {
        ArrayList<movie> ratingScoreList = new ArrayList<movie>();
        if(user == null ){
            ratingScoreList = MovieController.rankMovie(MovieList, 1);
        }
        else if(!user.checkAdmin()){
            ratingScoreList = MovieController.rankMovie(MovieList, 1);
        }
        else{
        int option = 0;
        
        do{
        System.out.println("1.) Rank by Rating");
        System.out.println("2.) Rank by Transaction number");
        System.out.println("3.) Rank by Sales");
        System.out.println("4.) Quit");
        option = Helper.readInt("Enter your choice: ");
        if(option >0 && option < 4){
            System.out.println("Triggered");
            ratingScoreList = MovieController.rankMovie(MovieList,option);
            option = 4;
        }
        else if(option == 4){
            System.out.println("Exiting... ");
        }
        else{
            System.out.println("Invalid Option!!");
        }
        }while(option != 4);
    }
        int ranking =1;
       // System.out.println(ratingScoreList.size());
        for (movie Movie : ratingScoreList) {
            System.out.println("MOVIE RANKING: " + ranking);
            printSingleMovie(Movie);
            System.out.println();
            ranking++;  
                
            }
        }
    

    

    // filter only by blockbuster
    public static void printBlockBuster(ArrayList<movie> MovieList) {
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
            System.out.println(
                    "Movie ID " + Movie.getMovieID() + ": " + Movie.getTitle() + " (" + Movie.getAdvisoryRating()
                            + ") Overall Rating Score: " + Movie.getOverallRating());
        }
    }

    public static void printReview() {
        int movieId = Helper.readInt("Enter the movie Id: ");
        System.out.println();
        ArrayList<Review> reviewList = MovieController.getReview(movieId);
        Helper.line(80, "=");
        System.out.println("List of Reviews for movie ID " + movieId);
        Helper.line(80, "=");
        for (int i = 0; i < reviewList.size(); i++) {
            Review temp = reviewList.get(i);
            System.out.println("Rating: " + String.valueOf(temp.getRating()));
            System.out.println("Review: " + temp.getReviewText());
            System.out.println("By " + temp.getUserEmail());
            System.out.println();
        }

    }

    public static void searchMovie() {
        int option = 0;

        do {
            System.out.println("1. Search by Id");
            System.out.println("2. Search by name");
            System.out.println("3. Exit Search");
            System.out.println();
            option = Helper.readInt("Enter your choice: ");
            if (option == 1) {
                int movieId = Helper.readInt("Enter the movie Id: ");
                movie searchMovie = MovieController.searchById(movieId);
                if (searchMovie != null) {
                    printSingleMovie(searchMovie);
                    System.out.println();
                } else {
                    System.out.println("Sorry No movie with the Id " + movieId);
                    System.out.println();
                }
            } else if (option == 2) {
                String movieId = Helper.readString("Enter the movie name: ");
                movie searchMovie = MovieController.searchByName(movieId);
                if (searchMovie != null) {
                    printSingleMovie(searchMovie);
                    System.out.println();
                } else {
                    System.out.println("Sorry No movie with the Id " + movieId);
                    System.out.println();
                }

            } else {
                System.out.println("Redirecting ...");
            }
        } while (option != 3);
    }

}