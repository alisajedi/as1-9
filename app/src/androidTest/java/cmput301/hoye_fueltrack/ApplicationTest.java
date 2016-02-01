package cmput301.hoye_fueltrack;

import android.app.Application;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;

import java.util.ArrayList;


public class ApplicationTest extends ActivityInstrumentationTestCase2 {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testLog() {
        String date = "2016-01-18";
        String station = "Costco";
        double odo = 200123.5;
        String type = "regular";
        double amount = 40.234;
        double cost = 79.4;

        Log log1 = new Log();
        assertNotNull(log1);

        log1.setDate(date);
        log1.setStation(station);
        log1.setOdometer(odo);
        log1.setFuel_grade(type);
        log1.setFuel_amount(amount);
        log1.setFuel_unit_cost(cost);

        Log log2 = new Log(date, station, odo, type, amount, cost);

        // Test that the setters create the same object as the extended constructor.
        assertEquals(log1.getDate(), log2.getDate());
        assertEquals(log1.getStation(), log2.getStation());
        assertEquals(log1.getOdometer(), log2.getOdometer());
        assertEquals(log1.getFuel_grade(), log2.getFuel_grade());
        assertEquals(log1.getFuel_amount(), log2.getFuel_amount());
        assertEquals(log1.getFuel_unit_cost(), log2.getFuel_unit_cost());

        // Test that the total_cost of the log is dynamically updated.
        double newCost = 100.001;
        log1.setFuel_unit_cost(newCost);
        assertEquals((amount * newCost) / 100, log1.getFuel_total_cost());
    }

    public void testFuelTracker() {
        FuelTracker tracker = new FuelTracker();
        ArrayList<Log> logs = new ArrayList<Log>();
        String date = "2016-01-18";
        String station = "Costco";
        double odo = 200123.5;
        String type = "regular";
        double amount = 40.234;
        double cost = 79.4;
        double total = (amount * cost) / 100;
        Log log1 = new Log(date, station, odo, type, amount, cost);

        tracker.addLog(log1);
        logs.add(log1);
        assertEquals(logs, tracker.getLogs());
        assertEquals(total, tracker.getTotal());

        Log log2 = new Log("2016-01-19", station, odo, type, amount, cost);
        logs.add(log2);
        tracker.addLog(log2);
        assertEquals(logs, tracker.getLogs());
        assertEquals((2 * total), tracker.getTotal());

        Log log3 = new Log("2016-02-19", station, odo, type, amount, cost);
        logs.add(log3);
        tracker.setLogs(logs);
        assertEquals(logs, tracker.getLogs());

    }

}

