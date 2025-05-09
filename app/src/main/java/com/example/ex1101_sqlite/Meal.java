package com.example.ex1101_sqlite;

/**
 * This class defines the constants for the "Meal" table in the database.
 * It includes the table name and the names of the columns within the table.
 */
public class Meal{
    /**
     * The name of the database table for meals.
     */
    public static final String TABLE_MEAL= "Meal";
    /**
     * The column name for the starter meal.
     */
    public static final String STARTER= "Starter_meal";
    /**
     * The column name for the unique identifier of each meal record.
     */
    public static final String KEY_ID= "_id";
    /**
     * The column name for the main meal.
     */
    public static final String MAIN_MEAL= "Main_meal";
    /**
     * The column name for the side meal.
     */
    public static final String SIDE_MEAL= "Side_meal";
    /**
     * The column name for the dessert.
     */
    public static final String DESSERT= "Dessert";
}