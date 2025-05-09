package com.example.ex1101_sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Activity for inputting worker details into the database.
 * This activity allows users to enter information about a worker,
 * including their card number, name, last name, phone, ID, and company,
 * and save it to the SQLite database.
 */
public class InputWorkerActivity extends AppCompatActivity {
    EditText etCard, etName, etLastName, etPhone, etId, etCompany;
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
        setContentView(R.layout.activity_input_worker);

        etCard = findViewById(R.id.etCard);
        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLastName);
        etPhone = findViewById(R.id.etPhone);
        etId = findViewById(R.id.etId);
        etCompany = findViewById(R.id.etCompany);

        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();
        db.close();

    }

    /**
     * Inserts the entered worker details into the database.
     * This method is typically called when a button is clicked.
     * It retrieves the text from the EditText fields, validates that
     * none are empty, and then inserts the data into the "Worker" table
     * in the database.
     *
     * @param view The view that triggered this method (e.g., a button).
     */
    public void InserttoDB(View view)
    {

        String cardId,firstName,lastName,company,id,phone;
        cardId = etCard.getText().toString();
        firstName = etName.getText().toString();
        lastName = etLastName.getText().toString();
        company = etCompany.getText().toString();
        id = etId.getText().toString();
        phone = etPhone.getText().toString();

        if (cardId.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || company.isEmpty() || id.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Input must have a value", Toast.LENGTH_SHORT).show();
        }
        else{
            ContentValues cv = new ContentValues();

            cv.put(Worker.CARD_NUMBER, cardId);
            cv.put(Worker.NAME, firstName);
            cv.put(Worker.LAST_NAME, lastName);
            cv.put(Worker.COMPANY, company);
            cv.put(Worker.ID, id);
            cv.put(Worker.PHONE_NUMBER, phone);

            db = hlp.getWritableDatabase();
            db.insert(Worker.TABLE_WORKER, null, cv);
            db.close();
            etCard.setText("");
            etName.setText("");
            etLastName.setText("");
            etCompany.setText("");
            etId.setText("");
            etPhone.setText("");
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * Finishes the current activity and returns to the previous one.
     * This method is typically called when a "Back" button is clicked.
     *
     * @param view The view that triggered this method (e.g., a button).
     */
    public void goBack(View view) {
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