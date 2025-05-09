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

public class InputOrderActivity extends AppCompatActivity {
    EditText etdate, ettime, etemployee, etprovidor, etMealId;
    SQLiteDatabase db;
    HelperDB hlp;
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

    public void back(View view) {
        finish();
    }

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
}