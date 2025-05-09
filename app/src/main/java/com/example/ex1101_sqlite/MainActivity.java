package com.example.ex1101_sqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * The main activity of the application.
 * This activity serves as the entry point and provides navigation
 * to different parts of the application, such as inputting worker,
 * company, order, and meal details, and viewing statistics.
 */
public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;


    /**
     * Called when the activity is first created.
     * This is where you initialize your UI, get references to views,
     * and set up initial data. In this case, it initializes the database helper
     * and gets a readable database instance (though it's immediately closed).
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();
        db.close();

    }

    /**
     * Starts the activity for inputting worker details.
     * This method is typically called when a button related to worker input is clicked.
     *
     * @param view The view that triggered this method (e.g., a button).
     */
    public void inputWorker(View view) {
        Intent si = new Intent(this, InputWorkerActivity.class);
        startActivity(si);
    }

    /**
     * Starts the activity for inputting company details.
     * This method is typically called when a button related to company input is clicked.
     *
     * @param view The view that triggered this method (e.g., a button).
     */
    public void inputCompany(View view) {
        Intent si = new Intent(this, inputCompanyActivity.class);
        startActivity(si);

    }

    /**
     * Starts the activity for inputting order details.
     * This method is typically called when a button related to order input is clicked.
     *
     * @param view The view that triggered this method (e.g., a button).
     */
    public void getprder(View view) {
        Intent si = new Intent(this, InputOrderActivity.class);
        startActivity(si);
    }

    /**
     * Starts the activity for inputting meal details.
     * This method is typically called when a button related to meal input is clicked.
     *
     * @param view The view that triggered this method (e.g., a button).
     */
    public void getmeal(View view) {
        Intent si = new Intent(this, InputMealActivity.class);
        startActivity(si);
    }

    /**
     * Starts the activity for showing statistics.
     * This method is typically called when a button related to viewing statistics is clicked.
     *
     * @param view The view that triggered this method (e.g., a button).
     */
    public void goStats(View view) {
        Intent si = new Intent(this, showStatsActivity.class);
        startActivity(si);

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