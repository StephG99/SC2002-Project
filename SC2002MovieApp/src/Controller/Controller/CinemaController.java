package Controller;

import Entity.*;
//import java.util.*;

public class CinemaController {
    public static Cinema getCinema(int cinemaID) {
        return DatabaseController.getCinema(cinemaID);
    }
}