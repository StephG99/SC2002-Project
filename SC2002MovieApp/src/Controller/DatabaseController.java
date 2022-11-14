package Controller;

//import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
//import java.util.Scanner;
import java.io.BufferedReader;
import com.opencsv.CSVWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.Calendar;

import Entity.*;
import Helper.Helper;

//Database controller is the only controller allowed to touch the different files.
public class DatabaseController {
    /**
     * Fetches list of all users from the CSV database and parses them into a usable
     * ArrayList.
     * 
     * @return ArrayList containing User objects.
     */
    public static ArrayList<User> getAllUser() {
        ArrayList<User> nex = new ArrayList<>();
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/user.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {

                String[] user = line.split(splitBy); // use comma as separator
                // System.out.println(user);
                nex.add(new User(user[0], user[1], Integer.parseInt(user[2]), user[3], Boolean.parseBoolean(user[4])));

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nex;
    }

    /**
     * Insertion into database accepts a User object and appends to a CSV File.
     * 
     * @param newUser
     * @return
     * @throws IOException
     */
    public static User registerUser(User newUser) throws IOException {
        try (
                Writer mFileWriter = new FileWriter("SC2002MovieApp/src/Database/user.csv", true);

                CSVWriter csvWriter = new CSVWriter(mFileWriter,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);) {

            csvWriter.writeNext(new String[] { newUser.getName(), newUser.getEmail(),
                    String.valueOf(newUser.getMobileNo()), newUser.getPassword(),
                    String.valueOf(newUser.checkAdmin()) });
            return newUser;
        }

    }

    /**
     * Fetches a User by email address. if no user is found return NULL object.
     * 
     * @param email User's email address
     * @return User object associated with the email address.
     */
    public static User getUser(String email) {

        String line = "";
        String splitBy = ",";
        User result = null;
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/user.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {

                String[] user = line.split(splitBy); // use comma as separator
                if (user[1].equalsIgnoreCase(email)) {
                    result = new User(user[0], user[1], Integer.parseInt(user[2]), user[3],
                            Boolean.parseBoolean(user[4]));
                    break;
                }

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Uses the inputted movieID to check with the database and fetch a movie from
     * the list
     * 
     * @param movieId unique movie ID number
     * @return if a movie is found, it will be parsed into a Movie object.
     */
    public static movie getMoviebyId(int movieId) {
        movie resultMovie = null;
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/movie.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] result = line.split(splitBy); // use comma as separator

                if (Integer.valueOf(result[0]) == movieId) {
                    ArrayList<Review> reviewList = getReviews(Integer.valueOf(result[0]));
                    String processedText = result[2].replace('/', ',');
                    resultMovie = new movie(Integer.valueOf(result[0]), result[1], processedText,
                            Boolean.parseBoolean(result[3]), result[4], Integer.valueOf(result[5]), result[6],
                            result[7], decodeString(result[8]), Double.parseDouble(result[9]), reviewList);
                    break;
                }

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMovie;

    }

    /**
     * Fetches a list of all show sessions available from the csv file database.
     * 
     * @return result will be parsed into an ArrayList containing Shows objects.
     */
    public static ArrayList<Shows> getAllShows() {
        ArrayList<Shows> nex = new ArrayList<Shows>();
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/shows.csv"));
            while ((line = br.readLine()) != null) {
                String[] result = line.split(splitBy);

                ArrayList<showSeat> seats = getShowSeats(Integer.valueOf(result[0]));
                String[] StringArray = result[3].split("/");
                // thisDate(int year, int month, int day, int hour, int min)
                Date dateOfShow = Helper.thisDate(Integer.parseInt(StringArray[2]), Integer.parseInt(StringArray[1]),
                        Integer.parseInt(StringArray[0]), Integer.parseInt(StringArray[3]),
                        Integer.parseInt(StringArray[4]));

                nex.add(new Shows(Integer.valueOf(result[0]), Integer.valueOf(result[1]), Integer.valueOf(result[2]),
                        dateOfShow, seats));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nex;

    }

    /**
     * Uses a showID to fetch its associated seats in the show session.
     * 
     * @param showId unique ID for a show session
     * @return Result will be parsed into an ArrayList containg showSeat objects.
     */
    public static ArrayList<showSeat> getShowSeats(int showId) {
        ArrayList<showSeat> nex = new ArrayList<showSeat>();
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/showSeat.csv"));
            while ((line = br.readLine()) != null) {
                String[] result = line.split(splitBy);

                if (Integer.valueOf(result[2]) == showId) {
                    nex.add(new showSeat(Integer.valueOf(result[0]), Integer.valueOf(result[1]),
                            Integer.valueOf(result[2]), Boolean.parseBoolean(result[3])));
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nex;

    }

    /**
     * Fetches a list of all movies from the CSV file database.
     * 
     * @return Result will be parsed into an ArrayList containing all available
     *         movie objects.
     */
    public static ArrayList<movie> getAllMovie() {
        ArrayList<movie> nex = new ArrayList<movie>();
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/movie.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                // System.out.println(line);
                String[] result = line.split(splitBy); // use comma as separator
                // System.out.println(result[0]);
                if (result.length > 1) {
                    ArrayList<Review> reviewList = getReviews(Integer.valueOf(result[0]));
                    String processedText = result[2].replace('/', ',');
                    nex.add(new movie(Integer.valueOf(result[0]), result[1], processedText,
                            Boolean.parseBoolean(result[3]),
                            result[4], Integer.valueOf(result[5]), result[6], result[7], decodeString(result[8]),
                            Double.parseDouble(result[9]), reviewList));

                }

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nex;

    }

    /**
     * Fetches a list of all reviews associated with a specific movieID from the csv
     * file database.
     * 
     * @param movieId unique movie ID number
     * @return Result will be parsed into an ArrayList containing required Review
     *         objects.
     */
    public static ArrayList<Review> getReviews(int movieId) {
        ArrayList<Review> nex = new ArrayList<Review>();
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/review.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] result = line.split(splitBy); // use comma as separator
                // nex.add(
                // System.out.println(result[8]);
                String processedText = result[2].replace('/', ',');
                if (Integer.valueOf(result[0]) == movieId) {
                    nex.add(new Review(Integer.valueOf(result[0]), Integer.valueOf(result[1]), processedText,
                            result[3]));
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nex;

    }

    /**
     * Adds a new movie into the CSV File database.
     * 
     * @param newMovie new movie object to be parsed into the database
     * @throws IOException
     */
    public static void addMovie(movie newMovie) throws IOException {
        try (
                Writer mFileWriter = new FileWriter("SC2002MovieApp/src/Database/movie.csv", true);
                Writer reviewfileWriter = new FileWriter("SC2002MovieApp/src/Database/review.csv", true);

                CSVWriter csvWriter = new CSVWriter(mFileWriter,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
                CSVWriter csvWriter2 = new CSVWriter(reviewfileWriter,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);) {
            /*
             * private int movieId;
             * private String title;
             * private String synopsis;
             * private boolean blockBuster;
             * private String typeOfMovie;
             * private int status;
             * private String ViewerAdvisory;
             * private String director;
             * private ArrayList<String> cast;
             * private double overallRating;
             * private ArrayList<Review> pastReview;
             */
            String synopsisText = newMovie.getSynopsis();
            String processedText = synopsisText.replace(',', '/');
            csvWriter.writeNext(new String[] { String.valueOf(newMovie.getMovieID()), newMovie.getTitle(),
                    processedText, String.valueOf(newMovie.isBlockBuster()), newMovie.getMovieType(),
                    String.valueOf(newMovie.getStatus()), newMovie.getAdvisoryRating(), newMovie.getDirector(),
                    encodeString(newMovie.getCastList()), String.valueOf(newMovie.getOverallRating()) });
            ArrayList<Review> toWrite = newMovie.getPastReviews();
            for (int i = 0; i < toWrite.size(); i++) {
                Review temp = toWrite.get(i);
                String reviewText = temp.getReviewText();
                String processedText2 = reviewText.replace(',', '/');
                csvWriter2.writeNext(new String[] { String.valueOf(temp.getMovieId()), String.valueOf(temp.getRating()),
                        processedText2, temp.getUserEmail() });

            }

        }

    }

    /**
     * Inserts a new transaction recordinto the csv file database.
     * 
     * @param newTransaction new Transaction object to be parsed into the database.
     * @throws IOException
     */
    public static void insertTransaction(Transaction newTransaction) throws IOException {
        try (
                Writer mFileWriter = new FileWriter("SC2002MovieApp/src/Database/transaction.csv", true);

                CSVWriter csvWriter = new CSVWriter(mFileWriter,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);) {
            // convert Date to dd/mm/yyyy/hh/min format
            Date date = newTransaction.getTiming();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy/HH/mm");
            String strDate = dateFormat.format(date);

            csvWriter.writeNext(new String[] {
                    newTransaction.getTransactionId(), newTransaction.getEmail(),
                    String.valueOf(newTransaction.getPhoneNo()), newTransaction.getName(),
                    String.valueOf(newTransaction.getMovieId()), String.valueOf(newTransaction.getCineplexId()),
                    String.valueOf(newTransaction.getCinemaID()), encodeIntList(newTransaction.getSeatID()), strDate,
                    String.valueOf(newTransaction.getPrice())
            });
        }
    }

    /**
     * Adds a new review into the CSV file database.
     * 
     * @param newReview new Review object to be parsed into the database.
     * @throws IOException
     */
    public static void insertReview(Review newReview) throws IOException {
        try (
                Writer mFileWriter = new FileWriter("SC2002MovieApp/src/Database/review.csv", true);

                CSVWriter csvWriter = new CSVWriter(mFileWriter,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);) {
            // replace any instances of commas in the review text to avoid errors when
            // parsing
            String reviewText = newReview.getReviewText();
            String processedText = reviewText.replace(',', '/');
            csvWriter.writeNext(new String[] {
                    String.valueOf(newReview.getMovieId()), String.valueOf(newReview.getRating()), processedText,
                    newReview.getUserEmail()
            });
        }
    }

    /**
     * Fetches a list of all transaction records from the CSV File database.
     * 
     * @return Result will be parsed into an ArrayList containing all available
     *         Transaction objects.
     */
    public static ArrayList<Transaction> getAllTransactions() {
        ArrayList<Transaction> nex = new ArrayList<Transaction>();
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/transaction.csv"));
            while ((line = br.readLine()) != null) {
                String[] result = line.split(splitBy);
                ArrayList<Integer> seatList = decodeIntList(result[7]);
                String[] StringArray = result[8].split("/");
                // thisDate(int year, int month, int day, int hour, int min)
                Date dateOfTxn = Helper.thisDate(Integer.parseInt(StringArray[2]), Integer.parseInt(StringArray[1]),
                        Integer.parseInt(StringArray[0]), Integer.parseInt(StringArray[3]),
                        Integer.parseInt(StringArray[4]));
                nex.add(new Transaction(result[0], result[1], Integer.valueOf(result[2]), result[3],
                        Integer.valueOf(result[4]), Integer.valueOf(result[5]), Integer.valueOf(result[6]), seatList,
                        dateOfTxn, Float.valueOf(result[9])));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nex;
    }

    /**
     * Used to decode movie castlists from the csv file database.
     * 
     * @param encodedString String taken from the csv file
     * @return Result will be parsed into a string array for the cast list.
     */
    public static ArrayList<String> decodeString(String encodedString) {
        ArrayList<String> result = new ArrayList<String>();
        String[] processMethod = encodedString.split("/");
        for (int i = 0; i < processMethod.length; i++) {
            // System.out.println(processMethod[0]);
            // System.out.println(processMethod[i]);
            result.add(processMethod[i]);
        }
        return result;

    }

    /**
     * Used to encode movie castlists to be inserted into the csv file database.
     * 
     * @param decodedString string array taken from the movie object
     * @return Result will be parsed into an encoded String.
     */
    public static String encodeString(ArrayList<String> decodedString) {
        String result = "";
        for (int i = 0; i < decodedString.size(); i++) {
            result += decodedString.get(i);
            if (i != decodedString.size() - 1) {
                result += "/";
            }
        }
        return result;

    }

    /**
     * Used to encode Date values into the system settings CSV file.
     * 
     * @param decodedDate
     * @return Result will be parsed into an encoded String.
     */
    public static String encodeDate(ArrayList<Date> decodedDate) {
        String result = "";
        // String[] processMethod = encodedString.split("/");
        for (int i = 0; i < decodedDate.size(); i++) {
            // System.out.println(processMethod[0]);
            // System.out.println(processMethod[i]);
            result += Helper.customDateToStringBuilder(decodedDate.get(i));
            if (i != decodedDate.size() - 1) {
                result += "/";

            }
        }
        return result;

    }

    /**
     * Used to encode integer lists of seat IDs to be keyed into the CSV file
     * database.
     * 
     * @param decodedIntList IntList of seat IDs
     * @return Result will be parsed into an encoded String.
     */
    public static String encodeIntList(ArrayList<Integer> decodedIntList) {
        String result = "";
        for (int i = 0; i < decodedIntList.size(); i++) {
            result += decodedIntList.get(i);
            if (i != decodedIntList.size() - 1) {
                result += "/";
            }
        }
        return result;
    }

    /**
     * Used to decode integer lists of seat IDs from the CSV file database.
     * 
     * @param encodedIntList encoded String of seat IDs
     * @return Result will return an ArrayList of Integers containing the seat IDs.
     */
    public static ArrayList<Integer> decodeIntList(String encodedIntList) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        String[] processMethod = encodedIntList.split("/");
        for (int i = 0; i < processMethod.length; i++) {
            // System.out.println(processMethod[0]);
            // System.out.println(processMethod[i]);
            result.add(Integer.valueOf(processMethod[i]));
        }
        return result;
    }

    /**
     * Fetches a movie from the csv database using the movie title.
     * 
     * @param movieName Movie title to check against database
     * @return Result will return a Movie object with title matching the input movie
     *         title.
     */
    public static movie getMoviebyName(String movieName) {
        movie resultMovie = null;
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/movie.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] result = line.split(splitBy); // use comma as separator

                if (result[1].equalsIgnoreCase(movieName)) {
                    ArrayList<Review> reviewList = getReviews(Integer.valueOf(result[0]));
                    resultMovie = new movie(Integer.valueOf(result[0]), result[1], result[2],
                            Boolean.parseBoolean(result[3]), result[4], Integer.valueOf(result[5]), result[6],
                            result[7], decodeString(result[8]), Double.parseDouble(result[9]), reviewList);
                    break;
                }

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMovie;
    }

    /**
     * Fetches a cinema object from the CSV database using the cinema ID.
     * 
     * @param cinemaId cinemaID to check against database.
     * @return Result will return a Cinema object with ID matching the input ID.
     */
    public static Cinema getCinema(int cinemaId) {
        Cinema resultCinema = null;
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/cinema.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] result = line.split(splitBy); // use comma as separator

                if (Integer.valueOf(result[0]) == cinemaId) {
                    movieClass mClass = getMovieClass(Integer.valueOf(result[1]));
                    resultCinema = new Cinema(cinemaId, mClass);
                    break;
                }

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultCinema;

    }

    /**
     * Fetches a show session object from the CSV Database using the show ID.
     * 
     * @param showID showID to check with the database
     * @return Result will a return a Shows object with ID matching the input ID.
     */
    public static Shows getShow(int showID) {
        Shows resultShow = null;
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/shows.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] result = line.split(splitBy); // use comma as separator

                ArrayList<showSeat> seats = getShowSeats(Integer.valueOf(result[0]));
                String[] StringArray = result[3].split("/");
                // thisDate(int year, int month, int day, int hour, int min)
                Date dateOfShow = Helper.thisDate(Integer.parseInt(StringArray[2]), Integer.parseInt(StringArray[1]),
                        Integer.parseInt(StringArray[0]), Integer.parseInt(StringArray[3]),
                        Integer.parseInt(StringArray[4]));

                if (Integer.valueOf(result[0]) == showID) {
                    resultShow = new Shows(Integer.valueOf(result[0]), Integer.valueOf(result[1]),
                            Integer.valueOf(result[2]), dateOfShow, seats);
                    break;
                }

            }
            br.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return resultShow;
    }

    /**
     * Fetches a movie class object from the CSV Database using the class ID.
     * 
     * @param classId classID to be checked against the csv
     * @return Result will return a movieClass object with ID matching the input ID.
     */
    private static movieClass getMovieClass(int classId) {
        movieClass resultClass = null;
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/movieclass.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] result = line.split(splitBy); // use comma as separator

                if (Integer.valueOf(result[0]) == classId) {
                    resultClass = new movieClass(Integer.valueOf(result[0]), result[1], Double.valueOf(result[2]));
                    break;
                }

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultClass;

    }

    /**
     * Inserts a show session into the csv database.
     * 
     * @param cinemaId   cinemaID of the cinema where the showing will take place.
     * @param movieId    movieID of the movie being screened.
     * @param dateOfShow Date and timing of the show session.
     * @throws IOException
     */
    public static void insertShow(int cinemaId, int movieId, String dateOfShow) throws IOException {
        int showId = getGeneratedId("shows.csv");
        insertSeat(showId, cinemaId);
        try (
                Writer mFileWriter = new FileWriter("SC2002MovieApp/src/Database/shows.csv", true);

                CSVWriter csvWriter = new CSVWriter(mFileWriter,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);) {

            csvWriter.writeNext(new String[] { String.valueOf(showId), String.valueOf(cinemaId),
                    String.valueOf(movieId), dateOfShow });

        }
    }

    /**
     * Inserts a seat for a showtime into the csv database.
     * 
     * @param showId   showID for the selected showtime.
     * @param cinemaId cinemaID for the cinema that will be screened for this
     *                 particular session.
     * @throws IOException
     */
    public static void insertSeat(int showId, int cinemaId) throws IOException {

        try (
                Writer mFileWriter = new FileWriter("SC2002MovieApp/src/Database/showSeat.csv", true);

                CSVWriter csvWriter = new CSVWriter(mFileWriter,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);) {

            for (int x = 0; x < 10; x++) {
                for (int j = 0; j < 10; j++) {
                    String temp = String.valueOf((x * 10) + j + 1);
                    csvWriter.writeNext(new String[] { temp, String.valueOf(cinemaId), String.valueOf(showId),
                            String.valueOf(false) });
                }
            }

        }

    }

    /**
     * Gets the last ID in the csv database.
     * 
     * @param filePath name of the csvfile to fetch the last entry from.
     * @return Result will return an integer value.
     */
    private static int getGeneratedId(String filePath) {
        int resultId = 0;
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/" + filePath));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] result = line.split(splitBy); // use comma as separator
                if (result[0].equals("")) {
                    break;
                }
                resultId = Integer.valueOf(result[0]);

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultId + 1;

    }

    /**
     * Fetches all available cineplexes from the csv database.
     * 
     * @return Result will be parsed into an ArrayList of all available Cineplex
     *         objects.
     */
    public static ArrayList<Cineplex> getAllCineplex() {
        ArrayList<Cineplex> allCineplex = new ArrayList<Cineplex>();
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/cineplex.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] result = line.split(splitBy); // use comma as separator
                ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
                ArrayList<movieClass> classList = new ArrayList<movieClass>();
                // Custom Decoding for array stored in CSV
                String[] cineList = result[2].split("/");
                for (String cine : cineList) {
                    // System.out.println(cine);
                    cinemaList.add(getCinema(Integer.parseInt(cine)));
                }
                for (Cinema cine : cinemaList) {
                    // System.out.println(result[0]+" "+cine.getCinemaID());
                    if (!classList.contains(cine.getMovieClass())) {
                        classList.add(cine.getMovieClass());

                    }
                }
                allCineplex.add(new Cineplex(Integer.parseInt(result[0]), result[1], cinemaList, classList));

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allCineplex;
    }

    /**
     * Fetches a list of all public holidays from the system settings databse.
     * 
     * @return Result will be parsed into an ArrayList of Date objects containing
     *         all available PH dates stored in the DB.
     */
    public static ArrayList<Date> getAllPublicHoliday() {
        ArrayList<Date> publicHolidays = new ArrayList<Date>();
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/settings.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] result = line.split(splitBy); // use comma as separator
                String[] dateResult = result[3].split("/");
                // System.out.println(dateResult.length);
                // System.out.println(dateResult[0]);
                for (int i = 0; i < dateResult.length; i++) {

                    publicHolidays.add(Helper.customDateBuilder(dateResult[i]));
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return publicHolidays;
    }

    /**
     * Gets a single ticket price using the selected option for ticket type.
     * 
     * @param option Selected option for senior/student/normal ticket
     * @return
     */
    public static double getPrice(int option) {
        double price = 0;
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/settings.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] result = line.split(splitBy); // use comma as separator
                if (option == 3) {
                    price = Double.parseDouble(result[0]);
                } else {
                    price = Double.parseDouble(result[option]);
                }

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return price;
    }

    /**
     * Fetches a cineplex which a specific cinema belongs to.
     * 
     * @param cinemaId cinemaID to be checked with the cinplexList.
     * @return Result will return a Cineplex object denoting the Cineplex which this
     *         cinema belongs to.
     */
    public static Cineplex getCineplexByCinemaId(int cinemaId) {
        ArrayList<Cineplex> cineplexList = getAllCineplex();
        Cineplex result = null;
        for (Cineplex cine : cineplexList) {
            for (Cinema cinema : cine.getCinemas()) {
                if (cinema.getCinemaID() == cinemaId) {
                    result = cine;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Updates the status of seats for a particular showtime in the csv file
     * database.
     * 
     * @param showId  showID for the chosen show session
     * @param seatIDs seatIDs for the seats to be updated.
     * @throws IOException
     */
    public static void updateShowSeat(int showId, ArrayList<Integer> seatIDs) throws IOException {
        ArrayList<showSeat> previousCopy = getAllShowSeat();

        try (
                Writer mFileWriter = new FileWriter("SC2002MovieApp/src/Database/showSeat.csv");

                CSVWriter csvWriter = new CSVWriter(mFileWriter,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);) {
            for (showSeat seats : previousCopy) {
                for (int seatId : seatIDs) {
                    if (seats.getSeatID() == seatId && seats.getShowId() == showId) {
                        seats.assignSeat();
                        break;
                    }

                }
                csvWriter.writeNext(new String[] { String.valueOf(seats.getSeatID()),
                        String.valueOf(seats.getCinemaID()), String.valueOf(seats.getShowId()),
                        String.valueOf(seats.isOccupied()) });

            }

        }

    }

    /**
     * Updates the overall rating for a particular movie in the CSV file database.
     * 
     * @param movieID       movieID for the selected movie to be updated.
     * @param overallRating new value of the overallRating score to be updated into
     *                      the database.
     * @throws IOException
     */
    public static void updateOverallRating(int movieID, float overallRating) throws IOException {
        ArrayList<movie> previousCopy = getAllMovie();

        try (
                Writer mFileWriter = new FileWriter("SC2002MovieApp/src/Database/movie.csv");

                CSVWriter csvWriter = new CSVWriter(mFileWriter,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);) {
            for (movie Movie : previousCopy) {
                if (Movie.getMovieID() == movieID) {
                    Movie.setOverallRating(overallRating);
                }

                csvWriter.writeNext(new String[] {
                        String.valueOf(Movie.getMovieID()), Movie.getTitle(), Movie.getSynopsis(),
                        String.valueOf(Movie.isBlockBuster()), Movie.getMovieType(), String.valueOf(Movie.getStatus()),
                        Movie.getAdvisoryRating(), Movie.getDirector(), encodeString(Movie.getCastList()),
                        String.valueOf(Movie.getOverallRating())
                });
            }
        }
    }

    /**
     * Fetches a list of all seats for all available showtimes from the csv file
     * database.
     * 
     * @return Result will be parsed into a ArrayList of all available showSeat
     *         objects.
     */
    private static ArrayList<showSeat> getAllShowSeat() {
        ArrayList<showSeat> nex = new ArrayList<showSeat>();
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/showSeat.csv"));
            while ((line = br.readLine()) != null) {
                String[] result = line.split(splitBy);

                nex.add(new showSeat(Integer.valueOf(result[0]), Integer.valueOf(result[1]),
                        Integer.valueOf(result[2]), Boolean.parseBoolean(result[3])));

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nex;
    }

    /**
     * Fetches current system settings from the CSv file database.
     * 
     * @return Result will be parsed into a Settings object.
     */
    public static Settings getSettings() {
        Settings results = null;
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/settings.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] result = line.split(splitBy); // use comma as separator

                results = new Settings(Double.parseDouble(result[0]), Double.parseDouble(result[1]),
                        Double.parseDouble(result[2]), getAllPublicHoliday(), Double.parseDouble(result[4]),
                        Double.parseDouble(result[5]));

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;

    }

    /**
     * Updates new system settings into the CSV file database.
     * 
     * @param result
     * @throws IOException
     */
    public static void updateSettings(Settings result) throws IOException {

        try (
                Writer mFileWriter = new FileWriter("SC2002MovieApp/src/Database/settings.csv");

                CSVWriter csvWriter = new CSVWriter(mFileWriter,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);) {

            csvWriter.writeNext(
                    new String[] { String.valueOf(result.getRegularRates()), String.valueOf(result.getSeniorRates()),
                            String.valueOf(result.getStudentRates()), encodeDate(result.getPublicHolidays()),
                            String.valueOf(result.getHolidayRates()), String.valueOf(result.getWeekendRates()) });
        }
    }

    /**
     * Updates movie details into the csv file database.
     * 
     * @param movieList
     * @throws IOException
     */
    public static void updateMovie(ArrayList<movie> movieList) throws IOException {
        try (
                Writer mFileWriter = new FileWriter("SC2002MovieApp/src/Database/movie.csv");

                CSVWriter csvWriter = new CSVWriter(mFileWriter,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);) {
            for (movie Movie : movieList) {
                String processedText = Movie.getSynopsis().replace(',', '/');
                csvWriter.writeNext(new String[] {
                        String.valueOf(Movie.getMovieID()), Movie.getTitle(), processedText,
                        String.valueOf(Movie.isBlockBuster()), Movie.getMovieType(), String.valueOf(Movie.getStatus()),
                        Movie.getAdvisoryRating(), Movie.getDirector(), encodeString(Movie.getCastList()),
                        String.valueOf(Movie.getOverallRating())
                });
            }
        }

    }

}
