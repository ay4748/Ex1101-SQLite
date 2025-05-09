package com.example.ex1101_sqlite;

/**
 * This class defines the constants for the "Orders" table in the database.
 * It includes the table name and the names of the columns within the table.
 */
public class Order {
    /**
     * The name of the database table for orders.
     */
    public static final String TABLE_ORDER= "Orders";
    /**
     * The column name for the order date.
     */
    public static final String DATE= "Date";
    /**
     * The column name for the unique identifier of each order record.
     */
    public static final String KEY_ID= "_id";
    /**
     * The column name for the order time.
     */
    public static final String TIME= "Time";
    /**
     * The column name for the employee associated with the order.
     */
    public static final String EMPLOYEE= "Employee";
    /**
     * The column name for the meal associated with the order.
     */
    public static final String MEAL= "Meal";
    /**
     * The column name for the provider of the order.
     */
    public static final String PROVIDER= "Provider";
}