package Controller;

import java.io.IOException;
import java.util.*;

import Entity.*;
import Helper.Helper;

public class BookingController {
    // import relevant methods from DatabaseController
    /**
     * fetches list of all showtimes from the database
     * 
     * @return list of shows called from DatabaseController function
     */
    public static ArrayList<Shows> getAllShows() {
        return DatabaseController.getAllShows();
    }

    /**
     * fetches a particular showtime using showID
     * 
     * @param showID ID number for the show session
     * @return show object with ID matching showID
     */
    public static Shows getShow(int showID) {
        return DatabaseController.getShow(showID);
    }

    /**
     * fetches all of the show seats for a particular showtime using showID
     * 
     * @param showId ID number for the show session
     * @return ArrayList of show seats
     */
    public static ArrayList<showSeat> getShowSeats(int showId) {
        return DatabaseController.getShowSeats(showId);
    }

    /**
     * Books a movie ticket by assigning selected seats and updating the transaction
     * 
     * @param chosenCinema chosen cinema hall for the showtime
     * @param chosenShow   chosen showtime for the booking
     * @param price        total price of the booking
     * @param seatIDs      list of unique seat IDs that the user has chosen for the
     *                     booking
     * @param loginUser    user object
     * @return newly created transaction object with the relevant details
     * @throws IOException
     */
    public static Transaction bookShow(Cinema chosenCinema, Shows chosenShow, double price, ArrayList<Integer> seatIDs,
            User loginUser) throws IOException {
        /*
         * newTransaction.getTransactionId(), newTransaction.getEmail(),
         * String.valueOf(newTransaction.getPhoneNo()), newTransaction.getName(),
         * String.valueOf(newTransaction.getMovieId()),
         * String.valueOf(newTransaction.getCineplexId()),
         * String.valueOf(newTransaction.getCinemaID()),
         * encodeIntList(newTransaction.getSeatID()), strDate,
         * String.valueOf(newTransaction.getPrice()
         */
        Cineplex cineplex = DatabaseController.getCineplexByCinemaId(chosenCinema.getCinemaID());
        String cineCode = cineplex.getCineplexId() + cineplex.getCineName().substring(0, 2);
        Entity.movie movie = DatabaseController.getMoviebyId(chosenShow.getMovieId());
        Date now = Helper.now();
        String tid = cineCode + Helper.getYear(now) + Helper.getMonth(now) + Helper.getDay(now) + Helper.getHour(now)
                + Helper.getMin(now);
        Transaction ticket = new Transaction(tid, loginUser.getEmail(), loginUser.getMobileNo(), movie.getTitle(),
                movie.getMovieID(), cineplex.getCineplexId(), chosenCinema.getCinemaID(), seatIDs,
                chosenShow.getTiming(), price);
        DatabaseController.insertTransaction(ticket);
        DatabaseController.updateShowSeat(chosenShow.getShowId(), seatIDs);
        return ticket;

    }

    /**
     * Fetches list of all cineplexes where the movie is being shown
     * 
     * @param movieId unique ID number for the movie
     * @return list of cineplexes showing the movie
     */
    public static ArrayList<Cineplex> getCineplexByMovieId(int movieId) {
        ArrayList<Shows> showById = getShowsByMovieId(movieId);
        ArrayList<Integer> cinemaId = new ArrayList<Integer>();
        ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
        for (Shows show : showById) {
            if (!cinemaId.contains(show.getCinemaId())) {
                cinemaId.add(show.getCinemaId());
            }
        }
        for (Integer cineId : cinemaId) {
            cinemaList.add(DatabaseController.getCinema(cineId));
        }
        ArrayList<Cineplex> allCineplex = DatabaseController.getAllCineplex();
        ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
        for (Cineplex cineplex : allCineplex) {
            for (Cinema temp : cinemaList) {
                for (Cinema cineplexTemp : cineplex.getCinemas()) {
                    if (temp.getCinemaID() == cineplexTemp.getCinemaID()) {
                        if (!cineplexList.contains(cineplex)) {
                            cineplexList.add(cineplex);
                        }
                    }
                }

            }
        }

        return cineplexList;
    }

    /**
     * Fetches list of all showtimes for a chosen movie
     * 
     * @param movieID unique ID number for the chosen movie
     * @return arraylist of all shows
     */
    public static ArrayList<Shows> getShowsByMovieId(int movieID) {
        ArrayList<Shows> allShows = getAllShows();
        ArrayList<Shows> showById = new ArrayList<Shows>();
        for (Shows showTime : allShows) {
            if (showTime.getMovieId() == movieID) {
                showById.add(showTime);
            }
        }
        return showById;

    }

    /**
     * Fetches list of all showtimes within senior/student ticket timing
     * 
     * @param movieID unique ID number for the chosen movie
     * @param option  option to select senior/student ticket
     * @return arraylist of showtimes
     */
    public static ArrayList<Shows> getShowsByMovieId(int movieID, int option) {
        ArrayList<Shows> allShows = getAllShows();
        ArrayList<Shows> showById = new ArrayList<Shows>();
        for (Shows showTime : allShows) {
            // int cinemaId = showTime.getCinemaId();
            Cinema showClass = CinemaController.getCinema(showTime.getCinemaId());
            int showClassId = showClass.getMovieClass().getClassId();
            // 4 things to check Is Week day, Timing is between 1200 - 1800 ,if is public
            // holiday and whether it is regular class
            // System.out.println(Helper.getHour(showTime.getTiming()));
            if (showTime.getMovieId() == movieID && Helper.isWeekDay(showTime.getTiming())
                    && Helper.getHour(showTime.getTiming()) < 19 && Helper.getHour(showTime.getTiming()) > 11
                    && !isPublicHoliday(showTime.getTiming()) && showClassId == 1) {
                showById.add(showTime);
            }
        }
        return showById;
    }

    /**
     * Check if a selected date is a public holiday
     * 
     * @param inputDate chosen date
     * @return boolean value (yes or no)
     */
    public static boolean isPublicHoliday(Date inputDate) {
        ArrayList<Date> publicHoliDates = DatabaseController.getAllPublicHoliday();
        boolean isHoliday = false;
        for (Date date : publicHoliDates) {
            if (Helper.sameDate(date, inputDate)) {
                isHoliday = true;
                break;
            }
        }
        return isHoliday;
    }

    /**
     * Calculates single ticket price
     * 
     * @param chosenShow chosen showtimes
     * @param loginUser  user identity object
     * @param option     option for senior/student ticket price
     * @return ticket price value
     */
    public static double getPrice(Shows chosenShow, User loginUser, int option) {
        Cinema cinema = DatabaseController.getCinema((chosenShow.getCinemaId()));
        double premium = cinema.getMovieClass().getPricePremium();
        if (isPublicHoliday(chosenShow.getTiming()) || Helper.getDayWeek(chosenShow.getTiming()) == 1
                || Helper.getDayWeek(chosenShow.getTiming()) == 7) {
            option = 4;
        }
        double price = DatabaseController.getPrice(option);
        return price * premium;

    }

}