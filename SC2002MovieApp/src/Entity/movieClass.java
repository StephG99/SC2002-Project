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
     * @return
     */
    public int getClassId() {
        return classId;
    }

    /**
     * gets unique movie class name
     * 
     * @return
     */
    public String getClassName() {
        return className;
    }

    /**
     * sets unique class ID number to an input int value
     * 
     * @param classId
     */
    public void setClassId(int classId) {
        this.classId = classId;
    }

    /**
     * gets price multiplier value for the movie class
     * 
     * @return
     */
    public double getPricePremium() {
        return pricePremium;
    }

    /**
     * sets price multiplier value to an input int value
     * 
     * @param pricePremium
     */
    public void setPricePremium(double pricePremium) {
        this.pricePremium = pricePremium;
    }

}
