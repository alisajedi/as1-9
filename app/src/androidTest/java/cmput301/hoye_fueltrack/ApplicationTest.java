package cmput301.hoye_fueltrack;

import android.app.Application;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;


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
}

