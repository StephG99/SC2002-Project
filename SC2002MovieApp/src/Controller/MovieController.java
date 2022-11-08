package Controller;

import Entity.movie;
import Entity.Review;
//import Entity.movieClass;
import java.util.*;

public class MovieController {
    // add movie
    public static void addMovie(ArrayList<movie> MovieList, int movieId, String title, String synopsis,
            boolean blockBuster, String typeOfMovie, int status,
            String ViewerAdvisory,
            String director,
            ArrayList<String> cast, float overallRating, ArrayList<Review> pastReview) {
        movie newMovie = new movie(movieId, title, synopsis, blockBuster, typeOfMovie, status, ViewerAdvisory, director,
                cast, overallRating, pastReview);
        boolean exists = false;
        for (movie Movie : MovieList) {
            if (Movie.getMovieID() == movieId) {
                System.out.println("Movie already exists.");
                exists = true;
                break;
            }
        }
        if (!exists) {
            MovieList.add(newMovie);
            System.out.println("Movie successfully added.");
        }
    }

    // remove movie
    public static void removeMovie(ArrayList<movie> MovieList, int movieId) {
        boolean removed = false;
        for (movie Movie : MovieList) {
            if (Movie.getMovieID() == movieId) {
                MovieList.remove(Movie);
                removed = true;
                System.out.println("Movie successfully removed.");
                break;
            }
        }
        if (!removed) {
            System.out.println("Movie does not exist.");
        }
    }

    // update movie status
    public static void updateStatus(ArrayList<movie> MovieList, int movieId, int inputValue) {
        boolean success = false;
        for (movie Movie : MovieList) {
            if (Movie.getMovieID() == movieId) {
                if (inputValue < 0 || inputValue > 2) {
                    success = true;
                    System.out.println("Invalid input");
                } else {
                    Movie.setStatus(inputValue);
                    success = true;
                    System.out.println("Movie status successfully updated");
                }
                break;
            }
        }
        if (!success) {
            System.out.println("Movie ID not found");
        }
    }

    // set blockbuster status
    public static void updateBlockBuster(ArrayList<movie> MovieList, int movieId, int inputValue) {
        boolean success = false;
        for (movie Movie : MovieList) {
            if (Movie.getMovieID() == movieId) {
                success = true;
                switch (inputValue) {
                    case 0:
                        Movie.setNonBlockBuster();
                        System.out.println(" Movie successfully set to non-blockbuster");
                        break;
                    case 1:
                        Movie.setBlockBuster();
                        System.out.println("Movie successfully set to blockbuster");
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
                break;
            }
        }
        if (!success) {
            System.out.println("Movie ID not found");
        }
    }

    // update individual movie details using String variables
    public static void updateIndivMovieDetail(ArrayList<movie> MovieList, int movieId, String inputString, int choice) {
        boolean success = false;
        for (movie Movie : MovieList) {
            if (Movie.getMovieID() == movieId) {
                success = true;
                switch (choice) {
                    case 1:
                        Movie.setTitle(inputString);
                        System.out.println("Updated successfully");
                        break;
                    case 2:
                        Movie.setSynopsis(inputString);
                        System.out.println("Updated successfully");
                        break;
                    case 3:
                        Movie.setMovieType(inputString);
                        System.out.println("Updated successfully");
                        break;
                    case 4:
                        Movie.setAdvisoryRating(inputString);
                        System.out.println("Updated successfully");
                        break;
                    case 5:
                        Movie.setDirector(inputString);
                        System.out.println("Updated successfully");
                        break;
                    case 6:
                        // this option will only be to add a new cast member to the list
                        Movie.getCastList().add(inputString);
                        System.out.println("Updated successfully");
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
                break;
            }
        }
        if (!success) {
            System.out.println("Movie ID not found");
        }

    }

    // update entire cast list
    public static void updateWholeCast(ArrayList<movie> MovieList, int movieId, ArrayList<String> newCast) {
        boolean success = false;
        for (movie Movie : MovieList) {
            if (Movie.getMovieID() == movieId) {
                success = true;
                Movie.setCastList(newCast);
                System.out.println("Cast list updated successfully");
                break;
            }
        }
        if (!success) {
            System.out.println("Movie ID not found");
        }
    }

    // update whole movie
    public static void updateWholeMovie(ArrayList<movie> MovieList, int movieId, String title, String synopsis,
            String typeOfMovie, String ViewerAdvisory, String director, ArrayList<String> cast) {
        boolean success = false;
        for (movie Movie : MovieList) {
            if (Movie.getMovieID() == movieId) {
                success = true;
                Movie.setTitle(title);
                Movie.setSynopsis(synopsis);
                Movie.setMovieType(typeOfMovie);
                Movie.setAdvisoryRating(ViewerAdvisory);
                Movie.setDirector(director);
                Movie.setCastList(cast);
                System.out.println("All movie detail text fields updated successfully");
                break;
            }
        }
        if (!success) {
            System.out.println("Movie ID not found");
        }
    }

    // print individual movie details
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
    public static void printMovieList(ArrayList<movie> MovieList) {
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

    // check if movie exists
    public static movie searchMovie(ArrayList<movie> MovieList, String movieTitle) {
        for (movie Movie : MovieList) {
            if (Movie.getTitle().equalsIgnoreCase(movieTitle)) {
                return Movie;
            }
        }
        return null;
    }

}
