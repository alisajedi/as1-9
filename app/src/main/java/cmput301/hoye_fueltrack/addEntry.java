package cmput301.hoye_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

/*
*  This class handles input from the user and passes it back to the main activity.
*  This class does a rudimentary job of validating the user's input.
*/

public class addEntry extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
    }
    public void acceptEntry(View view){
        finish();
    }

    // Tutorial help on finishing activities.
    // http://www.vogella.com/tutorials/AndroidIntent/article.html
    @Override
    public void finish() {
        Intent data = new Intent();
        String result = validate();

        if (result == "") {
            data.putExtra("build", "");
            setResult(RESULT_CANCELED, data);
            super.finish();
        } else {
            data.putExtra("build", result);
            setResult(RESULT_OK, data);
            super.finish();
        }
    }

    public String validate(){
        String result = "";
        EditText editText = (EditText) findViewById(R.id.date);
        String temp = editText.getText().toString();
        System.out.print(temp);
        if (temp == "") {
            return temp;
        } else {
            result += temp;
        }
        editText = (EditText) findViewById(R.id.station);
        temp = editText.getText().toString();
        if (temp == "") {
            return temp;
        } else {
            result += " " + temp;
        }
        editText = (EditText) findViewById(R.id.odometer);
        temp = editText.getText().toString();
        if (temp == "") {
            return temp;
        } else {
            result += " " +  temp;
        }
        editText = (EditText) findViewById(R.id.grade);
        temp = editText.getText().toString();
        if (temp == "") {
            return temp;
        } else {
            result += " " + temp;
        }
        editText = (EditText) findViewById(R.id.amount);
        temp = editText.getText().toString();
        if (temp == "") {
            return temp;
        } else {
            result += " " + temp;
        }
        editText = (EditText) findViewById(R.id.cost);
        temp = editText.getText().toString();
        if (temp == "") {
            return temp;
        } else {
            result += " " + temp;
        }
        return result;
    }
}
