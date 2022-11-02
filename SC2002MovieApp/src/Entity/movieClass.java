package Entity;

public class movieClass {
    private int classId;
    

    private float pricePremium;

    public movieClass(int classId, float pricePremium) {
        this.classId = classId;
        this.pricePremium = pricePremium;
    }
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public float getPricePremium() {
        return pricePremium;
    }

    public void setPricePremium(float pricePremium) {
        this.pricePremium = pricePremium;
    }


}
