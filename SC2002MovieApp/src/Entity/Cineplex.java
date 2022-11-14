package Entity;

import java.util.*;

public class Cineplex {
    private int cineplexId;
    private String cineName;
    private ArrayList<Cinema> cinemas;
    private ArrayList<movieClass> classOffered;

    /**
     * Constructor for cineplex object
     * 
     * @param cineplexId   unique ID number for cineplex
     * @param cineName     name of cinplex
     * @param cinemas      arraylist containing all cinema objects under this
     *                     cineplex
     * @param classOffered arraylist containing all classes offered by this cineplex
     */
    public Cineplex(int cineplexId, String cineName, ArrayList<Cinema> cinemas, ArrayList<movieClass> classOffered) {
        this.cineplexId = cineplexId;
        this.cineName = cineName;
        this.cinemas = cinemas;
        this.classOffered = classOffered;
    }

    /**
     * Gets unique ID for cineplex
     * 
     * @return cineplexID
     */
    public int getCineplexId() {
        return cineplexId;
    }

    /**
     * Gets unique name for cinplex
     * 
     * @return cineName
     */
    public String getCineName() {
        return cineName;
    }

    /**
     * Sets a new ID number for cinplex
     * 
     * @param cineplexId ID number you want to set to
     */
    public void setCineplexId(int cineplexId) {
        this.cineplexId = cineplexId;
    }

    /**
     * Fetches arraylist of cinemas under this cineplex
     * 
     * @return cinemas
     */
    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    /**
     * Sets a new list of cinemas for this cineplex
     * 
     * @param cinemas arraylist of cinemas you want to set to
     */
    public void setCinemas(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    /**
     * Fetches arraylist of movie classes offered by this cineplex
     * 
     * @return classOffered
     */
    public ArrayList<movieClass> getClassOffered() {
        return classOffered;
    }

    /**
     * Sets a new list of classes offered for this cineplex
     * 
     * @param classOffered
     */
    public void setClassOffered(ArrayList<movieClass> classOffered) {
        this.classOffered = classOffered;
    }

}
