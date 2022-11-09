package Entity;

public class movieClass {
    private int classId;
    private double pricePremium;

    public movieClass(int classId, double pricePremium) {
        this.classId = classId;
        this.pricePremium = pricePremium;
    }
    public int getClassId() {
        return classId;
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
