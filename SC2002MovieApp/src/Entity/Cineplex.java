package Entity;

import java.util.*;

public class Cineplex {
    private int cineplexId;
    private ArrayList<Cinema> cinemas;
    private ArrayList<movieClass> classOffered;

    public Cineplex(int cineplexId, ArrayList<Cinema> cinemas, ArrayList<movieClass> classOffered) {
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

    public ArrayList<movieClass> getClassOffered() {
        return classOffered;
    }

    public void setClassOffered(ArrayList<movieClass> classOffered) {
        this.classOffered = classOffered;
    }

}
