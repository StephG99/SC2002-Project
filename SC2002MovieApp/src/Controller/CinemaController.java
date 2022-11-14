package Controller;

import Entity.*;
//import java.util.*;

public class CinemaController {
    /**
     * Gets cinema object using unique CinemaID
     * 
     * @param cinemaID unique ID number for the cinema hall
     * @return cinema object
     */
    public static Cinema getCinema(int cinemaID) {
        return DatabaseController.getCinema(cinemaID);
    }
}