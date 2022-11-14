package Entity;

//import java.lang.reflect.Array;

import java.util.*;

public class movie {
  private int movieId;
  private String title;
  private String synopsis;
  private boolean blockBuster;
  private String typeOfMovie;
  private int status;
  private String ViewerAdvisory;
  private String director;
  private ArrayList<String> cast;
  private double overallRating;
  private ArrayList<Review> pastReview;

  /**
   * Constructor for movie class
   * 
   * @param movieId        unique ID number for movie
   * @param title          movie title
   * @param synopsis       movie synopsis
   * @param blockBuster    indicates if a movie is blockbuster or not
   * @param typeOfMovie    movie genre
   * @param status         indicates movie status, if it is advance sales, now
   *                       showing, or coming soon
   * @param ViewerAdvisory viewer advisory rating for movie (G, PG, PG13, NC16,
   *                       etc)
   * @param director       lists movie director
   * @param cast           cast list
   * @param d              overall rating score by viewers
   * @param pastReview     all reviews for this movie
   */
  public movie(int movieId, String title, String synopsis, boolean blockBuster, String typeOfMovie, int status,
      String ViewerAdvisory,
      String director,
      ArrayList<String> cast, double d, ArrayList<Review> pastReview) {
    this.movieId = movieId;
    this.title = title;
    this.synopsis = synopsis;
    this.blockBuster = blockBuster;
    this.typeOfMovie = typeOfMovie;
    this.status = status;
    this.ViewerAdvisory = ViewerAdvisory;
    this.director = director;
    this.cast = cast;
    this.overallRating = d;
    this.pastReview = pastReview;
  }

  /**
   * gets ID number for movie
   * 
   * @return movieId
   */
  public int getMovieID() {
    return movieId;
  }

  /**
   * change ID number for movie
   * 
   * @param movieId ID number you want to set to
   */
  public void setMovieID(int movieId) {
    this.movieId = movieId;
  }

  /**
   * gets title of the movie
   * 
   * @return title
   */
  public String getTitle() {
    return title;
  }

  /**
   * change the title of the movie
   * 
   * @param title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * gets the synopsis for the movie
   * 
   * @return synopsis
   */
  public String getSynopsis() {
    return synopsis;
  }

  /**
   * sets the synopsis for the movie to an inputted text
   * 
   * @param synopText
   */
  public void setSynopsis(String synopText) {
    this.synopsis = synopText;

  }

  /**
   * checks if movie is a blockbuster
   * 
   * @return blockBuster
   */
  public boolean isBlockBuster() {
    return blockBuster;
  }

  /**
   * sets this movie to be a blockbuster
   * 
   * @param blockbuster
   */
  public void setBlockBuster(boolean blockbuster) {
    this.blockBuster = blockbuster;
  }
  /*
   * // set blockbuster to be no
   * public void setNonBlockBuster() {
   * this.blockBuster = false;
   * }
   */

  /**
   * gets genre of movie
   * 
   * @return typeOfMovie
   */
  public String getMovieType() {
    return typeOfMovie;
  }

  /**
   * sets genre of movie to an input string
   * 
   * @param typeOfMovie
   */
  public void setMovieType(String typeOfMovie) {
    this.typeOfMovie = typeOfMovie;
  }

  /**
   * fetches the status of a movie (in the form of an int value: 0 = Advance
   * Sales, 1 = Now Showing, 2 = Coming Soon)
   * 
   * @return
   */
  public int getStatus() {
    return status;
  }

  /**
   * sets the status of a movie to an int value: 0 = Advance Sales, 1 = Now
   * Showing, 2 = Coming Soon
   * 
   * @param status
   */
  public void setStatus(int status) {
    this.status = status;
  }

  /**
   * fetches the viewer advisory rating for a movie
   * 
   * @return
   */
  public String getAdvisoryRating() {
    return ViewerAdvisory;
  }

  /**
   * sets the viewer advisory rating for a movie to an input string
   * 
   * @param AdvisoryRating
   */
  public void setAdvisoryRating(String AdvisoryRating) {
    this.ViewerAdvisory = AdvisoryRating;
  }

  /**
   * gets director of a movie
   * 
   * @return
   */
  public String getDirector() {
    return director;
  }

  /**
   * change the director of a movie
   * 
   * @param directorname
   */
  public void setDirector(String directorname) {
    this.director = directorname;
  }

  /**
   * fetches the cast list of a movie in the form of an arraylist
   * 
   * @return
   */
  public ArrayList<String> getCastList() {
    return cast;
  }

  /**
   * sets a new arraylist to be the castlist of a movie
   * 
   * @param castList
   */
  public void setCastList(ArrayList<String> castList) {
    this.cast = castList;
  }

  /**
   * gets overall rating score for a movie
   * 
   * @return
   */
  public double getOverallRating() {
    return overallRating;
  }

  /**
   * sets overall rating score for a movie to an input int value
   * 
   * @param score
   */
  public void setOverallRating(float score) {
    this.overallRating = score;
  }

  /**
   * fetches list of past reviews for a movie
   * 
   * @return
   */
  public ArrayList<Review> getPastReviews() {
    return pastReview;
  }

}
