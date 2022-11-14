package Entity;

public class movieClass {
    private int classId;
    private String className;
    private double pricePremium;

    /**
     * Constructor for movie Class
     * 
     * @param classId      unique ID number for the movie class
     * @param className    name of the movie class
     * @param pricePremium price multiplier for the movie class
     */
    public movieClass(int classId, String className, double pricePremium) {
        this.classId = classId;
        this.className = className;
        this.pricePremium = pricePremium;
    }

    /**
     * gets unique class ID number
     * 
     * @return the class ID of the object
     */
    public int getClassId() {
        return classId;
    }

    /**
     * gets unique movie class name
     * 
     * @return className of the associated class
     */
    public String getClassName() {
        return className;
    }

    /**
     * sets unique class ID number to an input int value
     * 
     * @param classId is the class ID
     */
    public void setClassId(int classId) {
        this.classId = classId;
    }

    /**
     * gets price multiplier value for the movie class
     * 
     * @return the multiplier for the movie
     */
    public double getPricePremium() {
        return pricePremium;
    }

    /**
     * sets price multiplier value to an input int value
     * 
     * @param pricePremium new multiplier to the class
     */
    public void setPricePremium(double pricePremium) {
        this.pricePremium = pricePremium;
    }

}
