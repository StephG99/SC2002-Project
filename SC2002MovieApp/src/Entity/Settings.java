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
    
    

    public Settings(double regularPrice, double seniorPrice, double studentPrice, ArrayList<Date> publicHolidays,
            double holidayRates,double weekendRates) {
        this.regularPrice = regularPrice;
        this.seniorPrice = seniorPrice;
        this.studentPrice = studentPrice;
        PublicHolidays = publicHolidays;
        this.holidayRates = holidayRates;
        this.weekendRates = weekendRates;
    }
    public double getRegularRates() {
        return regularPrice;
    }

    public void setRegular(double regularPrice) {
        this.regularPrice = regularPrice;
    }
    public double getSeniorRates() {
        return seniorPrice;
    }

    public void setSeniorRates(double seniorRates) {
        this.seniorPrice = seniorRates;
    }
    public double getStudentRates() {
        return studentPrice;
    }

    public void setStudentRates(double studentPrice) {
        this.studentPrice = studentPrice;
    }
    public ArrayList<Date> getPublicHolidays() {
        return PublicHolidays;
    }

    public void setPublicHolidays(ArrayList<Date> publicHolidays) {
        PublicHolidays = publicHolidays;
    }

    public double getHolidayRates() {
        return holidayRates;
    }

    public void setHolidayRates(double holidayRates) {
        this.holidayRates = holidayRates;
    }
    public double getWeekendRates() {
        return regularPrice;
    }

    public void setWeekendRates(double weekendRates) {
        this.weekendRates = weekendRates;
    }

    
}
