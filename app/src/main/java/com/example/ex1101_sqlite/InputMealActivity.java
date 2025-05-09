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

public class InputMealActivity extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;

    EditText etStarter, etMainMeal, etSide, etDesert;

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

    public void back(View view) {
        finish();
    }
}