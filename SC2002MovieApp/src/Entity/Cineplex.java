package Entity;

import java.util.*;

public class Cineplex {
    private int cineplexId;
    private ArrayList<Cinema> cinemas;
    private movieClass classOffered[];

    public Cineplex(int cineplexId, ArrayList<Cinema> cinemas, movieClass[] classOffered) {
        this.cineplexId = cineplexId;
        this.cinemas = cinemas;
        this.classOffered = classOffered;
    }

    public int getCineplexId() {
        return cineplexId;
    }

    public void setCineplexId(int cineplexId) {
        this.cineplexId = cineplexId;
    }

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public movieClass[] getClassOffered() {
        return classOffered;
    }

    public void setClassOffered(movieClass[] classOffered) {
        this.classOffered = classOffered;
    }

}
