package Entity;

import java.util.ArrayList;
import java.util.Date;

public class Settings {
    private double regularPrice;
    private double seniorPrice;
    private double studentPrice;
    private ArrayList<Date> PublicHolidays;
    private double holidayRates;
    private double weekendRates;
    
    
    /**
     * 
     * @param regularPrice
     * @param seniorPrice
     * @param studentPrice
     * @param publicHolidays
     * @param holidayRates
     * @param weekendRates
     */
    public Settings(double regularPrice, double seniorPrice, double studentPrice, ArrayList<Date> publicHolidays,
            double holidayRates,double weekendRates) {
        this.regularPrice = regularPrice;
        this.seniorPrice = seniorPrice;
        this.studentPrice = studentPrice;
        PublicHolidays = publicHolidays;
        this.holidayRates = holidayRates;
        this.weekendRates = weekendRates;
    }
    /**
     * 
     * @return a double of the regular price
     */
    public double getRegularRates() {
        return regularPrice;
    }
     
    /**
     * 
     * @param regularPrice set new regular price
     */
    public void setRegular(double regularPrice) {
        this.regularPrice = regularPrice;
    }
    /**
     * 
     * @return a double of the senior price
     */
    public double getSeniorRates() {
        return seniorPrice;
    }
    /**
     * 
     * @param seniorRates set new senior rates
     */
    public void setSeniorRates(double seniorRates) {
        this.seniorPrice = seniorRates;
    }
    /**
     * 
     * @return a double of the Student price
     */
    public double getStudentRates() {
        return studentPrice;
    }
    /**
     * 
     * @param studentPrice set new Student rates
     */
    public void setStudentRates(double studentPrice) {
        this.studentPrice = studentPrice;
    }
    /**
     * 
     * @return a array of public holiday rate
     */
    public ArrayList<Date> getPublicHolidays() {
        return PublicHolidays;
    }

    /**
     * 
     * @param publicHolidays set new public holiday dates for setting
     */
    public void setPublicHolidays(ArrayList<Date> publicHolidays) {
        PublicHolidays = publicHolidays;
    }
    /**
     * 
     * @return holiday rates
     */
    public double getHolidayRates() {
        return holidayRates;
    }
    /**
     * 
     * @param holidayRates set new Holiday rates
     */
    public void setHolidayRates(double holidayRates) {
        this.holidayRates = holidayRates;
    }
    /**
     * 
     * @return weekend price
     */
    public double getWeekendRates() {
        return regularPrice;
    }
    /**
     * 
     * @param weekendRates weekend prices
     */
    public void setWeekendRates(double weekendRates) {
        this.weekendRates = weekendRates;
    }

    
}
