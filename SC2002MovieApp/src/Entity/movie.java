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

  // movieID getter
  public int getMovieID() {
    return movieId;
  }

  // movieID setter
  public void setMovieID(int movieId) {
    this.movieId = movieId;
  }

  // movie title getter
  public String getTitle() {
    return title;
  }

  // movie title setter
  public void setTitle(String title) {
    this.title = title;
  }

  // get movie synopsis
  public String getSynopsis() {
    return synopsis;
  }

  public void setSynopsis(String synopText) {
    this.synopsis = synopText;

  }

  // get movie blockbuster status
  public boolean isBlockBuster() {
    return blockBuster;
  }

  // set blockbuster to be yes
  public void setBlockBuster(boolean blockbuster) {
    this.blockBuster = blockbuster;
  }
  /* 
  // set blockbuster to be no
  public void setNonBlockBuster() {
    this.blockBuster = false;
  }
  */

  // get movie type
  public String getMovieType() {
    return typeOfMovie;
  }

  // set movie type
  public void setMovieType(String typeOfMovie) {
    this.typeOfMovie = typeOfMovie;
  }

  // get movie status
  public int getStatus() {
    return status;
  }

  // movie status setters: 0 = Advance Sales, 1 = Now Showing, 2 = Coming Soon
  public void setStatus(int status) {
    this.status = status;
  }

  public String getAdvisoryRating() {
    return ViewerAdvisory;
  }

  public void setAdvisoryRating(String AdvisoryRating) {
    this.ViewerAdvisory = AdvisoryRating;
  }

  public String getDirector() {
    return director;
  }

  // set movie director
  public void setDirector(String directorname) {
    this.director = directorname;
  }

  public ArrayList<String> getCastList() {
    return cast;
  }

  // set movie castlist
  public void setCastList(ArrayList<String> castList) {
    this.cast = castList;
  }

  public double getOverallRating() {
    return overallRating;
  }

  public void setOverallRating(float score) {
    this.overallRating = score;
  }

  public ArrayList<Review> getPastReviews() {
    return pastReview;
  }
  
  public String encodeReviews(){
    String result = "";
    for(int i = 0; i<pastReview.size();i++){
      String temp = pastReview.get(i).getRating()+"|"+pastReview.get(i).getReviewText()+"|"+pastReview.get(i).getUserEmail();
      result += temp;
      if(i != pastReview.size()-1){
        result += ";";
      }
    }
    return result;
    
  }
  public ArrayList<Review> decodeReviews(String encodedString){
    ArrayList<Review> result = new ArrayList<Review>();
    String[] processMethod = encodedString.split(";");
    for(int i = 0; i< processMethod.length;i++){
      String[] temp = processMethod[i].split("|");
      //result.add(new Review(Integer.valueOf(temp[0]),temp[1],temp[2]));
    }
    return result;

  }

}
