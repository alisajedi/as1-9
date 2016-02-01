package cmput301.hoye_fueltrack;

import java.util.ArrayList;

/**
 * Created by Stu on 2016-02-01.
 *
 * This class holds an arraylist of logs, and calculates the total cost of all
 * fuel accounted for in the logs.
 *
 */
public class FuelTracker {
    private ArrayList<Log> logs;

    public FuelTracker(){
        this.logs = new ArrayList<Log>();
    }

    public void addLog(Log log){
        this.logs.add(log);
    }

    public ArrayList<Log> getLogs(){
        return this.logs;
    }

    public void setLogs(ArrayList<Log> newLogs){
        this.logs = newLogs;
    }

    public double getTotal(){
        double total = 0;
        for (int i = 0; i < this.logs.size(); i++){
            total += this.logs.get(i).getFuel_total_cost();
        }
        return total;
    }

    public void clear() {
        this.logs.clear();
    }
}
