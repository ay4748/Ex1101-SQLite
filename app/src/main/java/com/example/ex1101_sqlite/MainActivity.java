package com.example.ex1101_sqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();
        db.close();

    }

    public void inputWorker(View view) {
        Intent si = new Intent(this, InputWorkerActivity.class);
        startActivity(si);
    }

    public void inputCompany(View view) {
        Intent si = new Intent(this, inputCompanyActivity.class);
        startActivity(si);

    }

    public void getprder(View view) {
        Intent si = new Intent(this, InputOrderActivity.class);
        startActivity(si);
    }

    public void getmeal(View view) {
        Intent si = new Intent(this, InputMealActivity.class);
        startActivity(si);
    }

    public void goStats(View view) {
        Intent si = new Intent(this, showStatsActivity.class);
        startActivity(si);

    }
}