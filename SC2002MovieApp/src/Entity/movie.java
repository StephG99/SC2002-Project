package Entity;

import java.lang.reflect.Array;

import java.util.Arrays;

public class movie {
  private String movieId;
  private String title;
  private boolean blockBuster;
  private String typeOfMovie[];
  private int status;
  private String director;
  private String cast[];
  private float overallRating;
  private Review[] pastReview;

  public movie(String movieId, String title, boolean blockBuster, String typeOfMovie[], int status, String director,
      String cast[]) {
    this.movieId = movieId;
    this.title = title;
    this.blockBuster = blockBuster;
    this.typeOfMovie = typeOfMovie;
    this.status = status;
    this.director = director;
    this.cast = cast;
  }

  public String getMovieID() {
    return movieId;
  }

  public String getTitle() {
    return title;
  }

  public boolean isBlockBuster() {
    return blockBuster;
  }

  public String[] getMovieType() {
    return typeOfMovie;
  }

  public int getStatus() {
    return status;
  }

  public String getDirector() {
    return director;
  }

  public String[] getCastList() {
    return cast;
  }

  public float getOverallRating() {
    return overallRating;
  }

  public Review[] getPastReviews() {
    return pastReview;
  }

  public void setStatus(int status){
    this.status = status;
  }

}
