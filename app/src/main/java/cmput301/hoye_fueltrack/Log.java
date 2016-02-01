package cmput301.hoye_fueltrack;

import java.util.Date;

/**
 * Created by Stu on 2016-01-31.
 */
public class Log {
    private String date;
    private String station;
    private double odometer;
    private String fuel_grade;
    private double fuel_amount;
    private double fuel_unit_cost;
    private double fuel_total_cost;

    public Log() {

    }

    public Log(String date, String station, double odometer, String grade, double amount, double cost){
        this.date = date;
        this.station = station;
        this.odometer = odometer;
        this.fuel_grade = grade;
        this.fuel_amount = amount;
        this.fuel_unit_cost = cost;
        this.fuel_total_cost = (amount * cost) / 100;
    }

    // Getters & Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public double getOdometer() {
        return odometer;
    }

    public void setOdometer(double odometer) {
        this.odometer = odometer;
    }

    public String getFuel_grade() {
        return fuel_grade;
    }

    public void setFuel_grade(String fuel_grade) {
        this.fuel_grade = fuel_grade;
    }

    public double getFuel_amount() {
        return fuel_amount;
    }

    public void setFuel_amount(double fuel_amount) {
        this.fuel_amount = fuel_amount;
        setFuel_total_cost();
    }

    public double getFuel_unit_cost() {
        return fuel_unit_cost;
    }

    public void setFuel_unit_cost(double fuel_unit_cost) {
        this.fuel_unit_cost = fuel_unit_cost;
        setFuel_total_cost();
    }

    public double getFuel_total_cost() {
        return fuel_total_cost;
    }

    public void setFuel_total_cost() {
        this.fuel_total_cost = fuel_unit_cost * fuel_amount;
    }

    // double formatting from
    // http://stackoverflow.com/questions/10332546/truncate-a-float-and-a-double-in-java
    // user: Prizoff
    // date: 2016-02-01
    public String toString() {
        String string = this.date + ", " + this.station + ", " + this.odometer + "km\n" +
                        "Fuel: " + this.fuel_grade + ", " + this.fuel_amount + "L @" +
                        this.fuel_unit_cost + "$/L\n" +
                        "Total: $" + String.format("%.2f", this.fuel_total_cost);
        return string;
    }
}
