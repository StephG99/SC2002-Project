package Controller;

import java.io.IOException;
import java.util.*;

import Entity.*;
import Helper.Helper;

public class BookingController {
    // import relevant methods from DatabaseController
    public static ArrayList<Shows> getAllShows() {
        return DatabaseController.getAllShows();
    }

    public static Shows getShow(int showID) {
        return DatabaseController.getShow(showID);
    }

    public static ArrayList<showSeat> getShowSeats(int showId) {
        return DatabaseController.getShowSeats(showId);
    }

    public static void bookShow(Cinema chosenCinema, Shows chosenShow, double price, ArrayList<Integer> seatIDs,
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

    }

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

    public static double getPrice(Shows chosenShow, User loginUser, int option) {
        Cinema cinema = DatabaseController.getCinema((chosenShow.getCinemaId()));
        double premium = cinema.getMovieClass().getPricePremium();
        if (isPublicHoliday(chosenShow.getTiming()) || Helper.getDayWeek(chosenShow.getTiming()) == 1 ||Helper.getDayWeek(chosenShow.getTiming()) == 7) {
            option = 4;
        }
        double price = DatabaseController.getPrice(option);
        return price * premium;

    }

}