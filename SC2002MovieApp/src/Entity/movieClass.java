package Entity;

public class movieClass {
    private int classId;
    private String className;
    private double pricePremium;

    public movieClass(int classId,String className, double pricePremium) {
        this.classId = classId;
        this.className = className;
        this.pricePremium = pricePremium;
    }
    public int getClassId() {
        return classId;
    }
    public String getClassName(){
        return className;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public double getPricePremium() {
        return pricePremium;
    }

    public void setPricePremium(double pricePremium) {
        this.pricePremium = pricePremium;
    }


}
