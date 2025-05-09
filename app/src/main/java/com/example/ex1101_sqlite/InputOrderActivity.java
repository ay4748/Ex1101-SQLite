package com.example.ex1101_sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Activity for inputting order details into the database.
 * This activity allows users to enter information about an order,
 * including the date, time, employee, provider, and meal ID, and save it
 * to the SQLite database.
 */
public class InputOrderActivity extends AppCompatActivity {
    EditText etdate, ettime, etemployee, etprovidor, etMealId;
    SQLiteDatabase db;
    HelperDB hlp;

    /**
     * Called when the activity is first created.
     * This is where you initialize your UI, get references to views,
     * and set up initial data.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_order);

        hlp = new HelperDB(this);
        etdate = findViewById(R.id.etdate);
        ettime = findViewById(R.id.ettime);
        etemployee = findViewById(R.id.etWorker);
        etprovidor = findViewById(R.id.etprovidor);
        etMealId = findViewById(R.id.etMealId);
    }

    /**
     * Finishes the current activity and returns to the previous one.
     * This method is typically called when a "Back" button is clicked.
     *
     * @param view The view that triggered this method (e.g., a button).
     */
    public void back(View view) {
        finish();
    }

    /**
     * Inserts the entered order details into the database.
     * This method is typically called when a button is clicked.
     * It retrieves the text from the EditText fields, validates that
     * none are empty, and then inserts the data into the "Order" table
     * in the database.
     *
     * @param view The view that triggered this method (e.g., a button).
     */
    public void insertToDB(View view) {
        db = hlp.getWritableDatabase();
        String date = etdate.getText().toString();
        String time = ettime.getText().toString();
        String employee = etemployee.getText().toString();
        String providor = etprovidor.getText().toString();
        String mealId = etMealId.getText().toString();

        if(date.isEmpty() || time.isEmpty() || employee.isEmpty() || providor.isEmpty() || mealId.isEmpty())
        {
            Toast.makeText(this, "invald input", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ContentValues values = new ContentValues();
            values.put(Order.DATE, date);
            values.put(Order.TIME, time);
            values.put(Order.EMPLOYEE, employee);
            values.put(Order.PROVIDER, providor);
            values.put(Order.MEAL, mealId);
            db.insert(Order.TABLE_ORDER, null, values);
            db.close();
            Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show();
            ettime.setText("");
            etdate.setText("");
            etemployee.setText("");
            etprovidor.setText("");
            etMealId.setText("");
        }

    }


    /**
     * create the options menu
     *
     * @param menu The options menu
     * @return return true
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Checks the selection in the options menu
     *
     * @param menu The selected menu item.
     * @return return true
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String num1 = menu.getTitle().toString();
        if (num1.equals("credits"))
        {
            Intent si = new Intent(this,credits_menu.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(menu);
    }
}