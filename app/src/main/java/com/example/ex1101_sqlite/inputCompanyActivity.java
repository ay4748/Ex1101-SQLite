package com.example.ex1101_sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class inputCompanyActivity extends AppCompatActivity {

    EditText etIdCompany;
    EditText etCompanyName;
    EditText etMainPhone;
    EditText etSecPhone;
    SQLiteDatabase db;
    HelperDB hlp;


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

    public void back(View view) {
        finish();
    }
}