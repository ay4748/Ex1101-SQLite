/**
 * Activity for displaying and managing statistics from various database tables.
 * Allows users to view, sort, and delete records from Worker, Company, Meal, and Order tables
 * using a spinner for table selection, a filter spinner for sorting, and a ListView for displaying records.
 */
package com.example.ex1101_sqlite;

import static com.example.ex1101_sqlite.Worker.KEY_ID;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Activity class that handles the display and manipulation of database records.
 * Implements AdapterView.OnItemSelectedListener for spinner interactions and
 * AdapterView.OnItemClickListener for ListView item clicks.
 */
public class showStatsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    /** SQLite database instance for data operations */
    SQLiteDatabase db;
    /** Database helper for managing database creation and version */
    HelperDB hlp;
    /** Cursor for querying database records */
    Cursor crsr;
    /** Array of table names for the spinner */
    String[] names = {"worker", "parkfood", "meal", "order"};
    /** Button to return to the previous activity */
    Button back_btn;
    /** ListView to display database records */
    ListView lvrecords;
    /** Spinner for selecting the table to display */
    Spinner menu;
    /** Spinner for selecting sorting options */
    Spinner spinner_filter;

    /** ArrayList to store records for display */
    ArrayList<String> tbl;
    /** ArrayList to store primary keys of records */
    ArrayList<Integer> keysList;

    /** Tracks the selected table index from the menu spinner */
    int count = 0;
    /** Tracks the selected item index in the ListView */
    int count2 = 0;

    /**
     * Initializes the activity, sets up the UI, and configures listeners for spinners and ListView.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stats);

        lvrecords = findViewById(R.id.lvrecords);
        spinner_filter = findViewById(R.id.spinner_filter);
        menu = findViewById(R.id.menu);
        back_btn = findViewById(R.id.rtn_back);

        menu.setOnItemSelectedListener(this);
        spinner_filter.setOnItemSelectedListener(this);
        lvrecords.setOnItemClickListener(this);
        lvrecords.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, names);
        menu.setAdapter(adp);
    }

    /**
     * Handles item selection events for the menu and filter spinners.
     * Updates the ListView based on the selected table and sorting option.
     *
     * @param parent   The AdapterView where the selection happened
     * @param view     The view within the AdapterView that was clicked
     * @param pos      The position of the view in the adapter
     * @param rowid    The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long rowid) {
        if (parent == menu) {
            if (pos == 0) {
                spinner_filter.setVisibility(View.VISIBLE);
                count = pos;

                String[] filter = {"no_sort", "sort_lastname"};
                ArrayAdapter<String> spinner2adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filter);
                spinner_filter.setAdapter(spinner2adp);
            }
            if (pos == 1) {
                spinner_filter.setVisibility(View.VISIBLE);
                count = pos;

                String[] filter = {"no_sort", "sort_companyname"};
                ArrayAdapter<String> spinner2adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filter);
                spinner_filter.setAdapter(spinner2adp);
            }
            if (pos == 2) {
                tbl = meal_show(lvrecords, tbl, crsr, hlp, db);
                ArrayAdapter<String> spinneradp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(spinneradp);
                spinner_filter.setVisibility(View.GONE);
            }
            if (pos == 3) {
                spinner_filter.setVisibility(View.VISIBLE);
                count = pos;

                String[] filter = {"no_sort", "sort_employee"};
                ArrayAdapter<String> spinner2adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filter);
                spinner_filter.setAdapter(spinner2adp);
            }
        } else if (parent == spinner_filter) {
            if (count == 0 && pos == 0) {
                tbl = worker_show(lvrecords, tbl, crsr, hlp, db);
                ArrayAdapter<String> spinneradp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(spinneradp);
            } else if (count == 0 && pos == 1) {
                tbl = worker_show_sort(lvrecords, tbl, crsr, hlp, db);
                ArrayAdapter<String> spinneradp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(spinneradp);
            }
            if (count == 1 && pos == 0) {
                tbl = parkfood_show(lvrecords, tbl, crsr, hlp, db);
                ArrayAdapter<String> spinneradp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(spinneradp);
            } else if (count == 1 && pos == 1) {
                tbl = parkfood_show_sort(lvrecords, tbl, crsr, hlp, db);
                ArrayAdapter<String> spinneradp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(spinneradp);
            }
            if (count == 3 && pos == 0) {
                tbl = order_show(lvrecords, tbl, crsr, hlp, db);
                ArrayAdapter<String> spinneradp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(spinneradp);
            } else if (count == 3 && pos == 1) {
                tbl = order_show_sort(lvrecords, tbl, crsr, hlp, db);
                ArrayAdapter<String> spinneradp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(spinneradp);
            }
        }
    }

    /**
     * Handles cases where no item is selected in a spinner.
     *
     * @param parent The AdapterView where nothing was selected
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.i("SpinDemo", "Nothing selected");
    }

    /**
     * Retrieves and displays worker data in the ListView without sorting.
     *
     * @param lvrecords The ListView to display the data
     * @param tbl       The ArrayList to store the data
     * @param crsr      The Cursor to read the data from
     * @param hlp       The HelperDB instance
     * @param db        The SQLiteDatabase instance
     * @return The ArrayList containing the worker data
     */
    public ArrayList<String> worker_show(ListView lvrecords, ArrayList<String> tbl, Cursor crsr, HelperDB hlp, SQLiteDatabase db) {
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();

        tbl = new ArrayList<>();
        keysList = new ArrayList<>();

        crsr = db.query(Worker.TABLE_WORKER, null, null, null, null, null, null);

        int col1 = crsr.getColumnIndex(KEY_ID);
        int col2 = crsr.getColumnIndex(Worker.ID);
        int col3 = crsr.getColumnIndex(Worker.NAME);
        int col4 = crsr.getColumnIndex(Worker.CARD_NUMBER);
        int col5 = crsr.getColumnIndex(Worker.LAST_NAME);
        int col6 = crsr.getColumnIndex(Worker.PHONE_NUMBER);
        int col7 = crsr.getColumnIndex(Worker.COMPANY);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String id = crsr.getString(col2);
            String name = crsr.getString(col3);
            String card = crsr.getString(col4);
            String last = crsr.getString(col5);
            String phone = crsr.getString(col6);
            String company = crsr.getString(col7);

            String tmp = "key: " + key + "\n\n id: " + id + "\n\n name: " + name + "\n\nlastname: " + last + "\n\n phone_number:  " + phone + "\n\ncompany_name: " + company;
            tbl.add(tmp);
            keysList.add(key);
            crsr.moveToNext();
        }

        crsr.close();
        db.close();
        return tbl;
    }

    /**
     * Retrieves and displays worker data in the ListView, sorted by last name.
     *
     * @param lvrecords The ListView to display the data
     * @param tbl       The ArrayList to store the data
     * @param crsr      The Cursor to read the data from
     * @param hlp       The HelperDB instance
     * @param db        The SQLiteDatabase instance
     * @return The ArrayList containing the sorted worker data
     */
    public ArrayList<String> worker_show_sort(ListView lvrecords, ArrayList<String> tbl, Cursor crsr, HelperDB hlp, SQLiteDatabase db) {
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();

        tbl = new ArrayList<>();
        keysList = new ArrayList<>();

        crsr = db.query(Worker.TABLE_WORKER, null, null, null, null, null, Worker.LAST_NAME + " ASC");

        int col1 = crsr.getColumnIndex(KEY_ID);
        int col2 = crsr.getColumnIndex(Worker.ID);
        int col3 = crsr.getColumnIndex(Worker.NAME);
        int col4 = crsr.getColumnIndex(Worker.CARD_NUMBER);
        int col5 = crsr.getColumnIndex(Worker.LAST_NAME);
        int col6 = crsr.getColumnIndex(Worker.PHONE_NUMBER);
        int col7 = crsr.getColumnIndex(Worker.COMPANY);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String id = crsr.getString(col2);
            String name = crsr.getString(col3);
            String card = crsr.getString(col4);
            String last = crsr.getString(col5);
            String phone = crsr.getString(col6);
            String company = crsr.getString(col7);

            String tmp = "key: " + key + "\n\n id: " + id + "\n\n name: " + name + "\n\nlastname: " + last + "\n\n phone_number:  " + phone + "\n\ncompany_name: " + company;
            tbl.add(tmp);
            keysList.add(key);
            crsr.moveToNext();
        }

        crsr.close();
        db.close();
        return tbl;
    }

    /**
     * Retrieves and displays company (ParkFood) data in the ListView without sorting.
     *
     * @param lvrecords The ListView to display the data
     * @param tbl       The ArrayList to store the data
     * @param crsr      The Cursor to read the data from
     * @param hlp       The HelperDB instance
     * @param db        The SQLiteDatabase instance
     * @return The ArrayList containing the company data
     */
    public ArrayList<String> parkfood_show(ListView lvrecords, ArrayList<String> tbl, Cursor crsr, HelperDB hlp, SQLiteDatabase db) {
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();

        tbl = new ArrayList<>();
        keysList = new ArrayList<>();

        crsr = db.query(Company.TABLE_COMPANY, null, null, null, null, null, null);

        int col1 = crsr.getColumnIndex(Company.KEY_ID);
        int col2 = crsr.getColumnIndex(Company.COMPANY_ID);
        int col3 = crsr.getColumnIndex(Company.NAME_COMPANY);
        int col4 = crsr.getColumnIndex(Company.MAIN_PHONE);
        int col5 = crsr.getColumnIndex(Company.SECONDARY_PHONE);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String companyId = crsr.getString(col2);
            String companyName = crsr.getString(col3);
            String mainPhone = crsr.getString(col4);
            String secondaryPhone = crsr.getString(col5);

            String tmp = "key: " + key + "\n\nCompany ID: " + companyId + "\n\nCompany Name: " + companyName + "\n\n Main Phone: " + mainPhone + "\n\n Secondary Phone: " + secondaryPhone;
            tbl.add(tmp);
            keysList.add(key);
            crsr.moveToNext();
        }

        crsr.close();
        db.close();
        return tbl;
    }

    /**
     * Retrieves and displays company (ParkFood) data in the ListView, sorted by company name.
     *
     * @param lvrecords The ListView to display the data
     * @param tbl       The ArrayList to store the data
     * @param crsr      The Cursor to read the data from
     * @param hlp       The HelperDB instance
     * @param db        The SQLiteDatabase instance
     * @return The ArrayList containing the sorted company data
     */
    public ArrayList<String> parkfood_show_sort(ListView lvrecords, ArrayList<String> tbl, Cursor crsr, HelperDB hlp, SQLiteDatabase db) {
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();

        tbl = new ArrayList<>();
        keysList = new ArrayList<>();

        crsr = db.query(Company.TABLE_COMPANY, null, null, null, null, null, Company.NAME_COMPANY + " ASC");

        int col1 = crsr.getColumnIndex(Company.KEY_ID);
        int col2 = crsr.getColumnIndex(Company.COMPANY_ID);
        int col3 = crsr.getColumnIndex(Company.NAME_COMPANY);
        int col4 = crsr.getColumnIndex(Company.MAIN_PHONE);
        int col5 = crsr.getColumnIndex(Company.SECONDARY_PHONE);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String companyId = crsr.getString(col2);
            String companyName = crsr.getString(col3);
            String mainPhone = crsr.getString(col4);
            String secondaryPhone = crsr.getString(col5);

            String tmp = "key: " + key + "\n\nCompany ID: " + companyId + "\n\nCompany Name: " + companyName + "\n\n Main Phone: " + mainPhone + "\n\n Secondary Phone: " + secondaryPhone;
            tbl.add(tmp);
            keysList.add(key);
            crsr.moveToNext();
        }

        crsr.close();
        db.close();
        return tbl;
    }

    /**
     * Retrieves and displays meal data in the ListView.
     *
     * @param lvrecords The ListView to display the data
     * @param tbl       The ArrayList to store the data
     * @param crsr      The Cursor to read the data from
     * @param hlp       The HelperDB instance
     * @param db        The SQLiteDatabase instance
     * @return The ArrayList containing the meal data
     */
    public ArrayList<String> meal_show(ListView lvrecords, ArrayList<String> tbl, Cursor crsr, HelperDB hlp, SQLiteDatabase db) {
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();

        tbl = new ArrayList<>();
        keysList = new ArrayList<>();

        crsr = db.query(Meal.TABLE_MEAL, null, null, null, null, null, null);

        int col1 = crsr.getColumnIndex(Meal.KEY_ID);
        int col2 = crsr.getColumnIndex(Meal.STARTER);
        int col3 = crsr.getColumnIndex(Meal.MAIN_MEAL);
        int col4 = crsr.getColumnIndex(Meal.SIDE_MEAL);
        int col5 = crsr.getColumnIndex(Meal.DESSERT);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String starter = crsr.getString(col2);
            String mainMeal = crsr.getString(col3);
            String sideMeal = crsr.getString(col4);
            String dessert = crsr.getString(col5);

            String tmp = "key: " + key + "\n\n Starter: " + starter + "\n\n ,Main Meal: " + mainMeal + "\n\n Side Meal: " + sideMeal + "\n\n Dessert: " + dessert;
            tbl.add(tmp);
            keysList.add(key);
            crsr.moveToNext();
        }

        crsr.close();
        db.close();
        return tbl;
    }

    /**
     * Retrieves and displays order data in the ListView without sorting.
     *
     * @param lvrecords The ListView to display the data
     * @param tbl       The ArrayList to store the data
     * @param crsr      The Cursor to read the data from
     * @param hlp       The HelperDB instance
     * @param db        The SQLiteDatabase instance
     * @return The ArrayList containing the order data
     */
    public ArrayList<String> order_show(ListView lvrecords, ArrayList<String> tbl, Cursor crsr, HelperDB hlp, SQLiteDatabase db) {
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();

        tbl = new ArrayList<>();
        keysList = new ArrayList<>();
        crsr = db.query(Order.TABLE_ORDER, null, null, null, null, null, null);

        int col1 = crsr.getColumnIndex(Order.KEY_ID);
        int col2 = crsr.getColumnIndex(Order.DATE);
        int col3 = crsr.getColumnIndex(Order.TIME);
        int col4 = crsr.getColumnIndex(Order.EMPLOYEE);
        int col5 = crsr.getColumnIndex(Order.MEAL);
        int col6 = crsr.getColumnIndex(Order.PROVIDER);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String date = crsr.getString(col2);
            String time = crsr.getString(col3);
            String employee = crsr.getString(col4);
            String meal = crsr.getString(col5);
            String providerCompany = crsr.getString(col6);

            String tmp = "key: " + key + "\n\n Date: " + date + "\n\n Time: " + time + "\n\n Employee: " + employee + "\n\n Meal: " + meal + "\n\n Provider Company: " + providerCompany;
            tbl.add(tmp);
            keysList.add(key);
            crsr.moveToNext();
        }

        crsr.close();
        db.close();
        return tbl;
    }

    /**
     * Retrieves and displays order data in the ListView, sorted by employee name.
     *
     * @param lvrecords The ListView to display the data
     * @param tbl       The ArrayList to store the data
     * @param crsr      The Cursor to read the data from
     * @param hlp       The HelperDB instance
     * @param db        The SQLiteDatabase instance
     * @return The ArrayList containing the sorted order data
     */
    public ArrayList<String> order_show_sort(ListView lvrecords, ArrayList<String> tbl, Cursor crsr, HelperDB hlp, SQLiteDatabase db) {
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();

        tbl = new ArrayList<>();
        keysList = new ArrayList<>();
        crsr = db.query(Order.TABLE_ORDER, null, null, null, null, null, Order.EMPLOYEE + " ASC");

        int col1 = crsr.getColumnIndex(Order.KEY_ID);
        int col2 = crsr.getColumnIndex(Order.DATE);
        int col3 = crsr.getColumnIndex(Order.TIME);
        int col4 = crsr.getColumnIndex(Order.EMPLOYEE);
        int col5 = crsr.getColumnIndex(Order.MEAL);
        int col6 = crsr.getColumnIndex(Order.PROVIDER);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String date = crsr.getString(col2);
            String time = crsr.getString(col3);
            String employee = crsr.getString(col4);
            String meal = crsr.getString(col5);
            String providerCompany = crsr.getString(col6);

            String tmp = "key: " + key + "\n\n Date: " + date + "\n\n Time: " + time + "\n\n Employee: " + employee + "\n\n Meal: " + meal + "\n\n Provider Company: " + providerCompany;
            tbl.add(tmp);
            keysList.add(key);
            crsr.moveToNext();
        }

        crsr.close();
        db.close();
        return tbl;
    }

    /**
     * Closes the current activity and returns to the previous screen.
     *
     * @param view The view that triggered the action
     */
    public void back(View view) {
        finish();
    }

    /**
     * Handles item click events in the ListView, storing the selected item's index.
     *
     * @param adapterView The AdapterView where the click happened
     * @param view        The view that was clicked
     * @param i           The position of the item in the adapter
     * @param l           The row id of the item that was clicked
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        count2 = i;
    }

    /**
     * Deletes the selected item from the database and updates the ListView.
     * Handles cascading deletes for related records in other tables.
     *
     * @param view The view that triggered the action
     */
    public void clickedDeleteItem(View view) {
        if (tbl == null || tbl.isEmpty() || count2 < 0 || count2 >= tbl.size()) {
            Toast.makeText(this, "No item selected or list is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        int keyToDelete = keysList.get(count2);

        if (count == 0) { // Worker
            db.delete(Worker.TABLE_WORKER, KEY_ID + "=?", new String[]{Integer.toString(keyToDelete)});
            db.delete(Meal.TABLE_MEAL, KEY_ID + "=?", new String[]{Integer.toString(keyToDelete)});
            db.delete(Order.TABLE_ORDER, KEY_ID + "=?", new String[]{Integer.toString(keyToDelete)});
        } else if (count == 1) { // ParkFood (Company)
            db.delete(Company.TABLE_COMPANY, KEY_ID + "=?", new String[]{Integer.toString(keyToDelete)});
        } else if (count == 2) { // Meal
            db.delete(Meal.TABLE_MEAL, KEY_ID + "=?", new String[]{Integer.toString(keyToDelete)});
            db.delete(Order.TABLE_ORDER, KEY_ID + "=?", new String[]{Integer.toString(keyToDelete)});
        } else if (count == 3) { // Order
            db.delete(Order.TABLE_ORDER, KEY_ID + "=?", new String[]{Integer.toString(keyToDelete)});
            db.delete(Meal.TABLE_MEAL, KEY_ID + "=?", new String[]{Integer.toString(keyToDelete)});
        }

        db.close();

        tbl.remove(count2);
        keysList.remove(count2);
        ArrayAdapter<String> spinneradp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
        lvrecords.setAdapter(spinneradp);

        Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show();
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