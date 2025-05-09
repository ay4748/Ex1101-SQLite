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
 * Activity for inputting meal details into the database.
 * This activity allows users to enter information about a meal,
 * including the starter, main meal, side, and dessert, and save it
 * to the SQLite database.
 */
public class InputMealActivity extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;

    EditText etStarter, etMainMeal, etSide, etDesert;

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
        setContentView(R.layout.activity_input_meal);

        etStarter = findViewById(R.id.etStarter);
        etMainMeal = findViewById(R.id.etMainMeal);
        etSide = findViewById(R.id.etSide);
        etDesert = findViewById(R.id.etDesert);

        hlp = new HelperDB(this);

    }

    /**
     * Inserts the entered meal details into the database.
     * This method is typically called when a button is clicked.
     * It retrieves the text from the EditText fields, validates that
     * none are empty, and then inserts the data into the "Meal" table
     * in the database.
     *
     * @param view The view that triggered this method (e.g., a button).
     */
    public void inserttoDB(View view) {
        db = hlp.getWritableDatabase();
        String starter = etStarter.getText().toString();
        String mainMeal = etMainMeal.getText().toString();
        String side = etSide.getText().toString();
        String desert = etDesert.getText().toString();

        if(starter.isEmpty() || mainMeal.isEmpty() || side.isEmpty() || desert.isEmpty())
        {
            Toast.makeText(this, "one or more is empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ContentValues values = new ContentValues();
            values.put(Meal.STARTER, starter);
            values.put(Meal.MAIN_MEAL, mainMeal);
            values.put(Meal.SIDE_MEAL, side);
            values.put(Meal.DESSERT, desert);
            db.insert(Meal.TABLE_MEAL, null, values);
            db.close();

            etStarter.setText("");
            etMainMeal.setText("");
            etSide.setText("");
            etDesert.setText("");
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();


        }
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