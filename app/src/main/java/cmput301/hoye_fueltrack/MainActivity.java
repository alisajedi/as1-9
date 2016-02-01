package cmput301.hoye_fueltrack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private static final String FILENAME = "cmput301.as1.file.sav";
    private static final int REQUEST_CODE = 1;
    private FuelTracker tracker;
    private ArrayAdapter<Log> logsAdapter;
    private ListView logView;
    private TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.logView = (ListView) findViewById(R.id.fuelLogs);
        this.total = (TextView) findViewById(R.id.total);
        this.tracker = new FuelTracker();
        loadFromFile();

        logsAdapter = new ArrayAdapter<Log> (this, R.layout.list_item, tracker.getLogs());
        logView.setAdapter(logsAdapter);
        total.setText("Total: $" + String.format("%.2f", tracker.getTotal()));
    }

    protected void onStart() {
        super.onStart();
        logsAdapter = new ArrayAdapter<Log> (this, R.layout.list_item, tracker.getLogs());
        logView.setAdapter(logsAdapter);
    }

    // Tutorial help on starting sub-activities.
    // http://www.vogella.com/tutorials/AndroidIntent/article.html
    public void addEntry(View view) {
        try {
            Intent intent = new Intent(this, addEntry.class);
            startActivityForResult(intent, REQUEST_CODE);
        } catch (Exception e){

        }
    }

    public void clearEntries(View view) {
        this.tracker.clear();
        saveInFile();
        logsAdapter.notifyDataSetChanged();
        total.setText("Total: $" + String.format("%.2f", tracker.getTotal()));
    }

    // Splitting a string from:
    // http://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java
    // User: BalusC
    // Date: 2016-02-01
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try{
            String buildString = data.getExtras().getString("build");
            String[] split = buildString.split(" ");
            String date = split[0];
            String station = split[1];
            double odometer = Double.parseDouble(split[2]);
            String grade = split[3];
            double amount = Double.parseDouble(split[4]);
            double cost = Double.parseDouble(split[5]);
            Log log = new Log(date, station, odometer, grade, amount, cost);
            this.tracker.addLog(log);
            logsAdapter.notifyDataSetChanged();
            total.setText("Total: $" + String.format("%.2f", tracker.getTotal()));
            saveInFile();
        } catch (Exception e){

        }
    }

    private void loadFromFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Code from:
            // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            // Date: Jan 19, 2016
            Type listType = new TypeToken<ArrayList<Log>>() {}.getType();
            ArrayList<Log> logs = gson.fromJson(in, listType);
            this.tracker.setLogs(logs);
        } catch (Exception e) {

        }
    }

    private void saveInFile(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(this.tracker.getLogs(), out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e){
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
