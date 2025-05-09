package com.example.ex1101_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelperDB extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "dbexam.db";
    private static final int DATABASE_VERSION = 69;
    String strCreate, strDelete;


    public HelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        strCreate = "CREATE TABLE " + Worker.TABLE_WORKER +
                " (" + Worker.KEY_ID + " INTEGER PRIMARY KEY," +
                " " + Worker.NAME + " TEXT," +
                " " + Worker.COMPANY + " TEXT," +
                " " + Worker.LAST_NAME + " TEXT," +
                " " + Worker.PHONE_NUMBER + " INTEGER," +
                " " + Worker.ID + " INTEGER," +
                " " + Worker.CARD_NUMBER + " INTEGER" +
                ");";
        db.execSQL(strCreate);

        strCreate = "CREATE TABLE " + Company.TABLE_COMPANY +
                " (" + Company.KEY_ID + " INTEGER PRIMARY KEY," +
                " " + Company.NAME_COMPANY + " TEXT," +
                " " + Company.COMPANY_ID + " INTEGER," +
                " " + Company.MAIN_PHONE + " INTEGER," +
                " " + Company.SECONDARY_PHONE + " INTEGER" +
                ");";
        db.execSQL(strCreate);

        strCreate = "CREATE TABLE " + Meal.TABLE_MEAL +
                " (" + Meal.KEY_ID + " INTEGER PRIMARY KEY," +
                " " + Meal.STARTER + " TEXT," +
                " " + Meal.MAIN_MEAL + " TEXT," +
                " " + Meal.SIDE_MEAL + " TEXT," +
                " " + Meal.DESSERT + " TEXT" +
                ");";
        db.execSQL(strCreate);

        strCreate = "CREATE TABLE " + Order.TABLE_ORDER +
                " (" + Order.KEY_ID + " INTEGER PRIMARY KEY," +
                " " + Order.MEAL + " TEXT," +
                " " + Order.EMPLOYEE + " TEXT," +
                " " + Order.PROVIDER + " TEXT," +
                " " + Order.DATE + " TEXT," +
                " " + Order.TIME + " INTEGER" +
                ");";
        db.execSQL(strCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        strDelete = "DROP TABLE IF EXISTS " + Worker.TABLE_WORKER;
        db.execSQL(strDelete);

        strDelete = "DROP TABLE IF EXISTS " + Meal.TABLE_MEAL;
        db.execSQL(strDelete);

        strDelete = "DROP TABLE IF EXISTS " + Company.TABLE_COMPANY;
        db.execSQL(strDelete);

        strDelete = "DROP TABLE IF EXISTS " + Order.TABLE_ORDER;
        db.execSQL(strDelete);

        onCreate(db);
    }
}