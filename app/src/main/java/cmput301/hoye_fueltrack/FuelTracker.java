package cmput301.hoye_fueltrack;

import java.util.ArrayList;

/**
 * Created by Stu on 2016-02-01.
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
        return logs;
    }

    public void setLogs(ArrayList<Log> newLogs){
        this.logs = newLogs;
    }

    public double getTotal(){
        double total = 0;
        for (int i = 0; i < logs.size(); i++){
            total += logs.get(i).getFuel_total_cost();
        }
        return total;
    }

    public void clear() {
        this.logs.clear();
    }
}
