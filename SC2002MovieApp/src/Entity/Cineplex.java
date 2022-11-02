package Entity;

public class Cineplex {
    private int cineplexId;
    private Cinema cinemas[];
    private int classOffered[];
    
    public Cineplex(int cineplexId, Cinema[] cinemas, int[] classOffered) {
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
    public Cinema[] getCinemas() {
        return cinemas;
    }
    public void setCinemas(Cinema[] cinemas) {
        this.cinemas = cinemas;
    }
    public int[] getClassOffered() {
        return classOffered;
    }
    public void setClassOffered(int[] classOffered) {
        this.classOffered = classOffered;
    }
    
}
