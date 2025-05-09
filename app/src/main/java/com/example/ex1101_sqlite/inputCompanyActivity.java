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
 * Activity for inputting company details into the database.
 * This activity allows users to enter information about a company,
 * including its ID, name, main phone, and secondary phone, and save it
 * to the SQLite database.
 */
public class inputCompanyActivity extends AppCompatActivity {

    EditText etIdCompany;
    EditText etCompanyName;
    EditText etMainPhone;
    EditText etSecPhone;
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
        setContentView(R.layout.activity_input_company);

        hlp = new HelperDB(this);

        etIdCompany = findViewById(R.id.etIdCompany);
        etCompanyName = findViewById(R.id.etCompanyName);
        etMainPhone = findViewById(R.id.etMainPhone);
        etSecPhone = findViewById(R.id.etSecPhone);
    }

    /**
     * Inserts the entered company details into the database.
     * This method is typically called when a button is clicked.
     * It retrieves the text from the EditText fields, validates that
     * none are empty, and then inserts the data into the "Company" table
     * in the database.
     *
     * @param view The view that triggered this method (e.g., a button).
     */
    public void insertIntoDB(View view) {
        db = hlp.getWritableDatabase();
        String id = etIdCompany.getText().toString();
        String name = etCompanyName.getText().toString();
        String main = etMainPhone.getText().toString();
        String sec = etSecPhone.getText().toString();

        if(id.isEmpty() || name.isEmpty() || main.isEmpty() || sec.isEmpty())
        {
            Toast.makeText(this, "invald input", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ContentValues values = new ContentValues();
            values.put(Company.COMPANY_ID, id);
            values.put(Company.NAME_COMPANY, name);
            values.put(Company.MAIN_PHONE, main);
            values.put(Company.SECONDARY_PHONE, sec);

            db.insert(Company.TABLE_COMPANY, null, values);
            db.close();
            etIdCompany.setText("");
            etCompanyName.setText("");
            etMainPhone.setText("");
            etSecPhone.setText("");
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