package com.example.tacoproj;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TableLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME =
            "candyDB";
    private static final int DATABASE_VERSION = 10;
    private static final String TABLE_TACO = "taco";
    private static final String TABLE_TOPPING = "topping";
    private static final String TABLE_SIDE = "side";
    private static final String TABLE_DRINK = "drink";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String ID = "id";
    private static final String AVAILABLE = "available";
    private static final String BREAKFAST = "breakfast";

    // other constants for column names
    public DatabaseManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
//all have id that is required but gets overridden so put a random number doesn't matter
    //Taco will have price 1.23, whether or not its available ("false", "true"), and whether or not its for breakfast only, both, or just evening ("true","both","false")
    //Topping will have price 1.23, available ^, Breakfast ^,
    //Drink will have price 1.23, available ^, breakfast ^,
    //Side will have price 1.23, available ^, breakfast ^
    // create candy table
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL( "drop table if exists " + TABLE_TACO );
        // build sql create statement
        String sqlCreate = "create table " + TABLE_TACO +
                "( " + ID;
        sqlCreate += " integer primary key autoincrement, " +
                NAME;
        sqlCreate += " text, " + PRICE + " text, " + AVAILABLE + " text, " + BREAKFAST + " text)";
        db.execSQL(sqlCreate);

        //db.execSQL( "drop table if exists " + TABLE_TOPPING );
        // build sql create statement
        String sqlCreate1 = "create table " + TABLE_TOPPING +
                "( " + ID;
        sqlCreate1 += " integer primary key autoincrement, " +
                NAME;
        sqlCreate1 += " text, " + PRICE + " text, " + AVAILABLE + " text, " + BREAKFAST + " text "  + ")";
        db.execSQL(sqlCreate1);


        //db.execSQL( "drop table if exists " + TABLE_DRINK );
        // build sql create statement
        String sqlCreate2 = "create table " + TABLE_DRINK +
                "( " + ID;
        sqlCreate2 += " integer primary key autoincrement, " +
                NAME;
        sqlCreate2 += " text, " + PRICE + " text, " + AVAILABLE + " text, " + BREAKFAST + " text "  + ")";
        db.execSQL(sqlCreate2);


        //db.execSQL( "drop table if exists " + TABLE_SIDE );
        // build sql create statement
        String sqlCreate3 = "create table " + TABLE_SIDE +
                "( " + ID;
        sqlCreate3 += " integer primary key autoincrement, " +
                NAME;
        sqlCreate3 += " text, " + PRICE + " text, " + AVAILABLE + " text, " + BREAKFAST + " text" + ")";
        db.execSQL(sqlCreate3);
    }

    // drop candy table, recreate it
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
// Drop old table if it exists
        db.execSQL("drop table if exists " +
                TABLE_TACO);
        db.execSQL("drop table if exists " +
                TABLE_TOPPING);
        db.execSQL("drop table if exists " +
                TABLE_DRINK);
        db.execSQL("drop table if exists " +
                TABLE_SIDE);
// Re-create table(s)
        onCreate(db);

    }

    public void insertTaco(Taco taco) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " +
                TABLE_TACO;
        sqlInsert += " values( null, '" + taco.getName();
        sqlInsert += "', '" + taco.getPrice() + "' ,'" + taco.getAvailability() + "','" + taco.getBreakfast() + "')";
        db.execSQL(sqlInsert);
        db.close();

    }

    public void insertTopping(Topping topping) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " +
                TABLE_TOPPING;
        sqlInsert += " values( null, '" + topping.getName();
        sqlInsert += "', '" + topping.getPrice() + "' ,'" + topping.getAvailability() + "','" + topping.getBreakfast() + "')";
        db.execSQL(sqlInsert);
        db.close();

    }
    public void insertDrink(Drink drink) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " +
                TABLE_DRINK;
        sqlInsert += " values( null, '" + drink.getName();
        sqlInsert += "', '" + drink.getPrice() + "' ,'" + drink.getAvailability() + "','" + drink.getBreakfast() +  "')";
        db.execSQL(sqlInsert);
        db.close();

    }

    public void insertSide(Side side) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " +
                TABLE_DRINK;
        sqlInsert += " values( null, '" + side.getName();
        sqlInsert += "', '" + side.getPrice() + "' ,'" + side.getAvailability() + "','" + side.getBreakfast() + "')";
        db.execSQL(sqlInsert);
        db.close();

    }

    public void updateTacoById(int id, String newName, double newPrice, String newAvailability, String newBreakfast) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlUpdate = "update " +
                TABLE_TACO;
        sqlUpdate += " set " + NAME + " = '{" + newName + "}'";
        sqlUpdate += ", " + PRICE + " = '{" + newPrice + "}'";
        sqlUpdate += ", " + AVAILABLE + " = '{" + newAvailability + "}'";
        sqlUpdate += ", " + BREAKFAST + " = '{" + newBreakfast + "}'";
        sqlUpdate += " where id = " + id;
        db.execSQL(sqlUpdate);
        db.close();
    }

    public void deleteTacoById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " +
                TABLE_TACO;
        sqlDelete += " where id = " + id;
        db.execSQL(sqlDelete);
        db.close();
    }

    public void deleteTacoByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " +
                TABLE_TACO;
        sqlDelete += " where " + NAME + " = " + "'" + name + "'";
        db.execSQL(sqlDelete);
        db.close();
    }

    public void deleteToppingByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " +
                TABLE_TOPPING;
        sqlDelete += " where " + NAME + " = '" + name + "'";
        db.execSQL(sqlDelete);
        db.close();
    }

    public void deleteDrinkByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " +
                TABLE_DRINK;
        sqlDelete += " where " + NAME + " = '" + name + "'";
        db.execSQL(sqlDelete);
        db.close();
    }

    public void deleteSideByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " +
                TABLE_SIDE;
        sqlDelete += " where " + NAME + " = " + "'" + name + "'";
        db.execSQL(sqlDelete);
        db.close();
    }


    public Taco selectTacoById(int id) {
        // select the row in the taco table
        // whose id value is id
        // return a reference to the Candy object
        // stored in that row
        SQLiteDatabase db = this.getWritableDatabase();
// construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_TACO;
        sqlQuery += " where " + ID + " = " + id;

// call rawQuery to execute the select query
        Cursor cursor = db.rawQuery(sqlQuery, null);
        Log.w("cursor:", cursor.toString());
// process the result of the query
        Taco taco = null;
        if (cursor.moveToFirst())
            taco = new Taco(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Double.parseDouble(cursor.getString(2)),
                    cursor.getString(3),
                    cursor.getString(4));
        cursor.close();
        return taco;


    }

    public Taco selectTacoByName(String name) {
        // select the row in the taco table
        // whose id value is id
        // return a reference to the Candy object
        // stored in that row
        SQLiteDatabase db = this.getWritableDatabase();
// construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_TACO;
        sqlQuery += " where " + NAME + " = " + "'" + name + "'";

// call rawQuery to execute the select query
        Cursor cursor = db.rawQuery(sqlQuery, null);
        Log.w("cursor:", cursor.toString());
// process the result of the query
        Taco taco = null;
        if (cursor.moveToFirst())
            taco = new Taco(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Double.parseDouble(cursor.getString(2)),
                    cursor.getString(3),
                    cursor.getString(4));
        cursor.close();
        return taco;


    }

    public Side selectSideByName(String name) {
        // select the row in the taco table
        // whose id value is id
        // return a reference to the Candy object
        // stored in that row
        SQLiteDatabase db = this.getWritableDatabase();
// construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_SIDE;
        sqlQuery += " where " + NAME + " = " + "'" + name + "'";

// call rawQuery to execute the select query
        Cursor cursor = db.rawQuery(sqlQuery, null);
        Log.w("cursor:", cursor.toString());
// process the result of the query
        Side side = null;
        if (cursor.moveToFirst())
            side = new Side(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Double.parseDouble(cursor.getString(2)),
                    cursor.getString(3),
                    cursor.getString(4));
        cursor.close();
        return side;


    }



    public Topping selectToppingByName(String name) {
        // select the row in the taco table
        // whose id value is id
        // return a reference to the Candy object
        // stored in that row
        SQLiteDatabase db = this.getWritableDatabase();
// construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_TOPPING;
        sqlQuery += " where " + NAME + " = " + "'" + name + "'";

// call rawQuery to execute the select query
        Cursor cursor = db.rawQuery(sqlQuery, null);
        Log.w("cursor:", cursor.toString());
// process the result of the query
        Topping topping = null;
        if (cursor.moveToFirst())
            topping = new Topping(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Double.parseDouble(cursor.getString(2)),
                    cursor.getString(3),
                    cursor.getString(4));
        cursor.close();
        return topping;


    }

    public Drink selectDrinkByName(String name) {
        // select the row in the taco table
        // whose id value is id
        // return a reference to the Candy object
        // stored in that row
        SQLiteDatabase db = this.getWritableDatabase();
// construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_DRINK;
        sqlQuery += " where " + NAME + " = " + "'" + name + "'";

// call rawQuery to execute the select query
        Cursor cursor = db.rawQuery(sqlQuery, null);
        Log.w("cursor:", cursor.toString());
// process the result of the query
        Drink drink = null;
        if (cursor.moveToFirst())
            drink = new Drink(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Double.parseDouble(cursor.getString(2)),
                    cursor.getString(3),
                    cursor.getString(4));
        cursor.close();
        return drink;


    }


    public void updateTacoByName(String name, Taco newTaco) {

        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "UPDATE " +
                TABLE_TACO + " SET ";
        sqlDelete += NAME + " = '" + newTaco.getName() + "', ";
        sqlDelete += PRICE + " = " + newTaco.getPrice() + ", ";
        sqlDelete += BREAKFAST + " = '" + newTaco.getBreakfast() + "', ";
        sqlDelete += AVAILABLE + " = '" + newTaco.getAvailability() + "' ";
        sqlDelete += " WHERE " + NAME + " = " + "'" + name + "'";
        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateSideByName(String name, Side newSide) {

        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "UPDATE " +
                TABLE_SIDE + " SET ";
        sqlDelete += NAME + " = '" + newSide.getName() + "', ";
        sqlDelete += PRICE + " = " + newSide.getPrice() + ", ";
        sqlDelete += BREAKFAST + " = '" + newSide.getBreakfast() + "', ";
        sqlDelete += AVAILABLE + " = '" + newSide.getAvailability() + "' ";
        sqlDelete += " WHERE " + NAME + " = " + "'" + name + "'";
        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateToppingByName(String name, Topping newTopping) {

        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "UPDATE " +
                TABLE_TOPPING + " SET ";
        sqlDelete += NAME + " = '" + newTopping.getName() + "', ";
        sqlDelete += PRICE + " = " + newTopping.getPrice() + ", ";
        sqlDelete += BREAKFAST + " = '" + newTopping.getBreakfast() + "', ";
        sqlDelete += AVAILABLE + " = '" + newTopping.getAvailability() + "' ";
        sqlDelete += " WHERE " + NAME + " = " + "'" + name + "'";
        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateDrinkByName(String name, Drink newDrink) {

        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "UPDATE " +
                TABLE_DRINK + " SET ";
        sqlDelete += NAME + " = '" + newDrink.getName() + "', ";
        sqlDelete += PRICE + " = " + newDrink.getPrice() + ", ";
        sqlDelete += BREAKFAST + " = '" + newDrink.getBreakfast() + "', ";
        sqlDelete += AVAILABLE + " = '" + newDrink.getAvailability() + "' ";
        sqlDelete += " WHERE " + NAME + " = " + "'" + name + "'";
        db.execSQL(sqlDelete);
        db.close();
    }



    public ArrayList<Taco> selectAllTacos() {
        SQLiteDatabase db = this.getWritableDatabase();
// construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_TACO;

// call rawQuery to execute the select query
        Cursor cursor = db.rawQuery(sqlQuery, null);
// process the result of the query
        // select all the rows in the taco table
        // return an ArrayList of Taco objects
        // build a Taco object, then return it
        ArrayList<Taco> tacos = new ArrayList<Taco>();
        while (cursor.moveToNext()) {
            Taco currentTaco = new Taco(Integer.parseInt(
                    cursor.getString(0)),
                    cursor.getString(1),                     Double.parseDouble(cursor.getString(2)), cursor.getString(3), cursor.getString(4));
            tacos.add(currentTaco);
        }
        db.close();
        return tacos;
    }

    public ArrayList<Topping> selectAllToppings() {
        SQLiteDatabase db = this.getWritableDatabase();
// construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_TOPPING;

// call rawQuery to execute the select query
        Cursor cursor = db.rawQuery(sqlQuery, null);
// process the result of the query
        // select all the rows in the taco table
        // return an ArrayList of Taco objects
        // build a Taco object, then return it
        ArrayList<Topping> toppings = new ArrayList<Topping>();
        while (cursor.moveToNext()) {
            Topping currentTopping = new Topping(Integer.parseInt(
                    cursor.getString(0)),
                    cursor.getString(1),                     Double.parseDouble(cursor.getString(2)), cursor.getString(3), cursor.getString(4));
            toppings.add(currentTopping);
        }
        db.close();
        return toppings;
    }



    public ArrayList<Side> selectAllSides() {
        SQLiteDatabase db = this.getWritableDatabase();
// construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_SIDE;

// call rawQuery to execute the select query
        Cursor cursor = db.rawQuery(sqlQuery, null);
// process the result of the query
        // select all the rows in the taco table
        // return an ArrayList of Taco objects
        // build a Taco object, then return it
        ArrayList<Side> sides = new ArrayList<Side>();
        while (cursor.moveToNext()) {
            Side currentSide = new Side(Integer.parseInt(
                    cursor.getString(0)),
                    cursor.getString(1),                     Double.parseDouble(cursor.getString(2)), cursor.getString(3), cursor.getString(4));
            sides.add(currentSide);
        }
        db.close();
        return sides;
    }
    public ArrayList<Drink> selectAllDrinks() {
        SQLiteDatabase db = this.getWritableDatabase();
// construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_DRINK;

// call rawQuery to execute the select query
        Cursor cursor = db.rawQuery(sqlQuery, null);
// process the result of the query
        // select all the rows in the taco table
        // return an ArrayList of Taco objects
        // build a Taco object, then return it
        ArrayList<Drink> drinks = new ArrayList<Drink>();
        while (cursor.moveToNext()) {
            Drink currentDrink = new Drink(Integer.parseInt(
                    cursor.getString(0)),
                    cursor.getString(1),                     Double.parseDouble(cursor.getString(2)), cursor.getString(3), cursor.getString(4));
            drinks.add(currentDrink);
        }
        db.close();
        return drinks;
    }


}