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

import Entity.*;

//Database controller is the only controller allowed to touch the different files.
public class DatabaseController {
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
                nex.add(new User(user[0], user[1], user[2], Boolean.parseBoolean(user[3])));

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nex;
    }

    // Insertion into database accepts a User object and append into csv.
    public static User registerUser(User newUser) throws IOException {
        try (
                Writer mFileWriter = new FileWriter("SC2002MovieApp/src/Database/user.csv", true);

                CSVWriter csvWriter = new CSVWriter(mFileWriter,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);) {

            csvWriter.writeNext(new String[] { newUser.getName(), newUser.getEmail(), newUser.getPassword(),
                    String.valueOf(newUser.checkAdmin()) });
            return newUser;
        }

    }

    // Get a User by email address. if no user is found return NULL object.
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
                    result = new User(user[0], user[1], user[2], Boolean.parseBoolean(user[3]));
                    break;
                }

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

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

    public static ArrayList<Shows> getAllShows(){
        ArrayList<Shows> nex = new ArrayList<Shows>();
        String line = "";
        String splitBy = ",";
        try{
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/shows.csv"));
            while ((line = br.readLine()) != null){
                String[] result = line.split(splitBy);

                ArrayList<showSeat> seats = getShowSeats(Integer.valueOf(result[0]));
                nex.add(new Shows(Integer.valueOf(result[0]),Integer.valueOf(result[1]),Integer.valueOf(result[2]),,seats));
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return nex;

    }

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

    public static ArrayList<movie> getAllMovie() {
        ArrayList<movie> nex = new ArrayList<movie>();
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("SC2002MovieApp/src/Database/movie.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] result = line.split(splitBy); // use comma as separator

                ArrayList<Review> reviewList = getReviews(Integer.valueOf(result[0]));
                nex.add(new movie(Integer.valueOf(result[0]), result[1], result[2], Boolean.parseBoolean(result[3]),
                        result[4], Integer.valueOf(result[5]), result[6], result[7], decodeString(result[8]),
                        Double.parseDouble(result[9]), reviewList));

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nex;

    }

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
                if (Integer.valueOf(result[0]) == movieId) {
                    nex.add(new Review(Integer.valueOf(result[0]), Integer.valueOf(result[1]), result[2], result[3]));
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nex;

    }

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

            csvWriter.writeNext(new String[] { String.valueOf(newMovie.getMovieID()), newMovie.getTitle(),
                    newMovie.getSynopsis(), String.valueOf(newMovie.isBlockBuster()), newMovie.getMovieType(),
                    String.valueOf(newMovie.getStatus()), newMovie.getAdvisoryRating(), newMovie.getDirector(),
                    encodeString(newMovie.getCastList()), String.valueOf(newMovie.getOverallRating()) });
            ArrayList<Review> toWrite = newMovie.getPastReviews();
            for (int i = 0; i < toWrite.size(); i++) {
                Review temp = toWrite.get(i);
                csvWriter2.writeNext(new String[] { String.valueOf(temp.getMovieId()), String.valueOf(temp.getRating()),
                        temp.getReviewText(), temp.getUserEmail() });

            }

        }

    }

    public static ArrayList<Review> decodeReviews(String encodedString) {
        ArrayList<Review> result = new ArrayList<Review>();
        String[] processMethod = encodedString.split(";");
        for (int i = 0; i < processMethod.length; i++) {
            // String[] temp = processMethod[i].split("|");
            // result.add(new Review(Integer.valueOf(temp[0]),temp[1],temp[2]));
        }
        return result;

    }

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

    public static String encodeString(ArrayList<String> decodedString) {
        String result = "";
        // String[] processMethod = encodedString.split("/");
        for (int i = 0; i < decodedString.size(); i++) {
            // System.out.println(processMethod[0]);
            // System.out.println(processMethod[i]);
            result += decodedString.get(i);
            if (i != decodedString.size() - 1) {
                result += "/";
            }
        }
        return result;

    }

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
                    resultClass = new movieClass(Integer.valueOf(result[0]), Double.valueOf(result[1]));
                    break;
                }

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultClass;

    }

    public static void insertShow(int cinemaId, int movieId, String dateOfShow) throws IOException {
        int showId = getLastId("shows.csv");
        insertSeat(showId, cinemaId);
        try (
                Writer mFileWriter = new FileWriter("SC2002MovieApp/src/Database/shows.csv", true);

                CSVWriter csvWriter = new CSVWriter(mFileWriter,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);) {

            csvWriter.writeNext(new String[] { String.valueOf(showId + 1), String.valueOf(cinemaId),
                    String.valueOf(movieId), dateOfShow });

        }
    }

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

    private static int getLastId(String filePath) {
        int resultId = 1;
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
        return resultId;

    }
}
