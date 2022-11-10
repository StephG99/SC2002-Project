package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Entity.*;
import Helper.Helper;

public class AdminController {
  public static int addShow(int cinemaId, int movieId, String dateOfShow) throws IOException {
    String[] dateOfShowList = dateOfShow.split("/");
    ArrayList<Integer> dateArrayList = new ArrayList<Integer>();
    // dd/mm/yyyy/hh/min
    if (dateOfShowList.length < 5) {
      return 3;
    } else {
      boolean valid = true;
      try {
        for (int i = 0; i < 5; i++) {
          int temp = Integer.parseInt(dateOfShowList[i]);
          if (temp < 0) {
            return 3;
          }
          dateArrayList.add(temp);
        }
      } catch (NumberFormatException e) {
        return 3;
      }
      if (dateArrayList.get(0) < 1 | dateArrayList.get(0) > 31) {
        valid = false;
      }
      if (dateArrayList.get(1) < 1 | dateArrayList.get(1) > 12) {
        valid = false;
      }
      if (dateArrayList.get(2) < 2022 | dateArrayList.get(2) > 3000) {
        valid = false;
      }
      if (dateArrayList.get(3) > 23) {
        valid = false;
      }
      if (dateArrayList.get(4) > 59) {
        valid = false;
      }
      if (!valid) {
        return 3;
      }

    }
    Cinema cinema = DatabaseController.getCinema(cinemaId);
    movie movie = DatabaseController.getMoviebyId(movieId);
    if (cinema == null & movie == null) {
      return 0;
    }
    if (movie == null) {
      return 1;
    }
    if (cinema == null) {
      return 2;
    }

    else {
      DatabaseController.insertShow(cinemaId, movieId, dateOfShow);
      return 4;
    }

  }
}
