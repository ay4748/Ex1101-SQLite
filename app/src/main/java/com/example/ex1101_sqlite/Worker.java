package com.example.ex1101_sqlite;

/**
 * This class defines the constants for the "Worker" table in the database.
 * It includes the table name and the names of the columns within the table.
 */
public class Worker {
    /**
     * The name of the database table for workers.
     */
    public static final String TABLE_WORKER= "Worker";
    /**
     * The column name for the worker's card number.
     */
    public static final String CARD_NUMBER= "Card";
    /**
     * The column name for the unique identifier of each worker record.
     */
    public static final String KEY_ID= "_id";
    /**
     * The column name for the worker's first name.
     */
    public static final String NAME="Name";
    /**
     * The column name for the worker's last name.
     */
    public static final String LAST_NAME= "LastName";
    /**
     * The column name for the worker's phone number.
     */
    public static final String PHONE_NUMBER= "Phone";
    /**
     * The column name for the worker's ID.
     */
    public static final String ID= "Id";
    /**
     * The column name for the company the worker is associated with.
     */
    public static final String COMPANY = "Company_Works";
}