package com.example.ex1101_sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InputWorkerActivity extends AppCompatActivity {
    EditText etCard, etName, etLastName, etPhone, etId, etCompany;
    SQLiteDatabase db;
    HelperDB hlp;

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

    public void goBack(View view) {
        finish();
    }
}