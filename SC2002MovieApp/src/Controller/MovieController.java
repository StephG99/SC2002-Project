package Controller;

import Entity.movie;
import Entity.Review;
import Entity.Transaction;

//import Entity.movieClass;
import java.util.*;

public class MovieController {
    public static ArrayList<movie> getAllMovie(){
        return DatabaseController.getAllMovie();
    }
    // add movie
    public static void addMovie(int movieId, String title, String synopsis,
            boolean blockBuster, String typeOfMovie, int status,
            String ViewerAdvisory,
            String director,
            ArrayList<String> cast, float overallRating, ArrayList<Review> pastReview) {
        movie newMovie = new movie(movieId, title, synopsis, blockBuster, typeOfMovie, status, ViewerAdvisory, director,
                cast, overallRating, pastReview);
        boolean exists = false;
        ArrayList<movie> MovieList = getAllMovie();
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
                        Movie.setBlockBuster(false);
                        System.out.println(" Movie successfully set to non-blockbuster");
                        break;
                    case 1:
                        Movie.setBlockBuster(true);
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
   
    // check if movie exists
    public static movie searchMovie(ArrayList<movie> MovieList, String movieTitle) {
        for (movie Movie : MovieList) {
            if (Movie.getTitle().equalsIgnoreCase(movieTitle)) {
                return Movie;
            }
        }
        return null;
    }
    public static ArrayList<Review> getReview(int movieId){
        return DatabaseController.getReviews(movieId);
    }
    public static movie searchById(int movieId) {
        return DatabaseController.getMoviebyId(movieId);
    }
    public static movie searchByName(String movieName){
        return DatabaseController.getMoviebyName(movieName);
    }
    public static ArrayList<movie> rankMovie(ArrayList<movie> movieList, int option) {
        ArrayList<movie> resultList = new ArrayList<movie>();
        if(option == 1){
        ArrayList<Double> ratingScoreList = new ArrayList<Double>(movieList.size());
        for (movie movie : movieList) {
            ratingScoreList.add(movie.getOverallRating());
        }
        // Sort rating Score by descending order so the top 5 rated movies come first
        Collections.sort(ratingScoreList,Collections.reverseOrder());
        int ranking = 1;
        for (Double score : ratingScoreList) {
            if(ranking == 5){
                break;
            }
            for (movie Movie : movieList) {
                if (Movie.getOverallRating() == score) {
                    //System.out.println("MOVIE RANKING: " + ranking);
                    //printSingleMovie(Movie);
                    resultList.add(Movie);
                    ranking++;
                    break;
                }
            }
        }

    }
    else if(option == 2){
        Map<Integer, Integer> movieCount = new HashMap<Integer, Integer>();
        ArrayList<Transaction> transactionList = TransactionController.getAllTransactions();
        for(Transaction transaction: transactionList){
            if(movieCount.containsKey(transaction.getMovieId())){
                movieCount.put(transaction.getMovieId(),movieCount.get(transaction.getMovieId())+1);
            }
            else{
                movieCount.put(transaction.getMovieId(),1);
            }
        }
         
 
//Use Comparator.reverseOrder() for reverse ordering
    ArrayList<movie> tempList = new ArrayList<movie>();
    movieCount.entrySet()
  .stream()
  .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
  .forEachOrdered(x -> tempList.add(searchById(x.getKey())));
  int stop =5;
  if(tempList.size() < 5){
    stop = tempList.size();
  }

  for(int i = 0 ; i< stop;i++){
    resultList.add(tempList.get(i));
  }

    }
    else if(option == 3){
        Map<Integer, Double> movieCount = new HashMap<Integer, Double>();
        ArrayList<Transaction> transactionList = TransactionController.getAllTransactions();
        for(Transaction transaction: transactionList){
            if(movieCount.containsKey(transaction.getMovieId())){
                movieCount.put(transaction.getMovieId(),movieCount.get(transaction.getMovieId())+transaction.getPrice());
            }
            else{
                movieCount.put(transaction.getMovieId(),transaction.getPrice());
            }
        }
         
 
//Use Comparator.reverseOrder() for reverse ordering
    ArrayList<movie> tempList = new ArrayList<movie>();
    movieCount.entrySet()
  .stream()
  .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
  .forEachOrdered(x -> tempList.add(searchById(x.getKey())));
  int stop =5;
  if(tempList.size() < 5){
    stop = tempList.size();
  }

  for(int i = 0 ; i< stop;i++){
    resultList.add(tempList.get(i));
  }

    }

    

        
        return resultList;
    }

}