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
    /**
     * @param movieId Unique Id of the movie
     * @param title Title of the movie
     * @param synopsis Summary of the sypnosis of the movie
     * @param blockBuster whether movie is a blockbuster or not
     * @param typeOfMovie Genre of the movie
     * @param status Showing status of the movie
     * @param ViewerAdvisory View Rating e.g PG13/NC16/M18
     * @param director Director of the movie
     * @param cast Array of String that contains the cast of the movie
     * @param overallRating Review ratings score
     * @param pastReview Past Reviewer Text and score.
     * Functions Create a Movie Object and calls databaseController to insert into CSV
     */
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
    /**
     * @param MovieList get an array of Movie object 
     * @param movieId get the Unique Movie ID that the user want to remove
     * Calls DatabaseController and remove movie from Csv.
     */
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

  
    /**
     * @param movieId Unique ID of the movie
     * @return An Array of review of the specified movie
     */
    public static ArrayList<Review> getReview(int movieId){
        return DatabaseController.getReviews(movieId);
    }
    /**
     * @param movieId Unique ID of the movie
     * @return the movie with the Unique ID
     */
    public static movie searchById(int movieId) {
        return DatabaseController.getMoviebyId(movieId);
    }
    /**
     * @param movieName Title of the movie
     * @return the movie with the tile
     */
    public static movie searchByName(String movieName){
        return DatabaseController.getMoviebyName(movieName);
    }
    /**
     * @param movieList an Array of Movie
     * @param option user option to sort by different metrics
     * @return a list of top 5 movie according to user option.
     */
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
