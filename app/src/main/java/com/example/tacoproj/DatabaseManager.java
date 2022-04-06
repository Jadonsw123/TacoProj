package com.example.tacoproj;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME =
            "candyDB";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_TACO = "taco";
    private static final String TABLE_TOPPING = "topping";
    private static final String TABLE_SIDE = "side";
    private static final String TABLE_DRINK = "drink";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String ID = "id";
    private static final String AVAILABLE = "available";
    private static final String BREAKFAST = "breakfast";
    private static final String TYPE = "type";
    // other constants for column names
    public DatabaseManager(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    // create candy table
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( "drop table if exists " +
                TABLE_TACO );
        // build sql create statement
        String sqlCreate = "create table " +  TABLE_TACO +
                "( " + ID;
        sqlCreate += " integer primary key autoincrement, " +
                NAME;
        sqlCreate += " text, " + PRICE + " real, " + AVAILABLE +" text, " + BREAKFAST + " text)";
        db.execSQL( sqlCreate );

        db.execSQL( "drop table if exists " +
                TABLE_TOPPING );
        // build sql create statement
        String sqlCreate1 = "create table " +  TABLE_TOPPING +
                "( " + ID;
        sqlCreate1 += " integer primary key autoincrement, " +
                NAME;
        sqlCreate1 += " text, " + PRICE + " real, " + AVAILABLE +" text, " + BREAKFAST + " text, "  + TYPE +" text " + ")";
        db.execSQL( sqlCreate1 );
    }



    // drop candy table, recreate it
    public void onUpgrade( SQLiteDatabase db,
                           int oldVersion, int newVersion ) {
// Drop old table if it exists
        db.execSQL( "drop table if exists " +
                TABLE_TACO );
// Re-create table(s)
        onCreate( db );

    }

    public void insertTaco( Taco taco ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " +
                TABLE_TACO;
        sqlInsert += " values( null, '" + taco.getName( );
        sqlInsert += "', '{" + taco.getPrice( ) + "}' ,'" + taco.getAvailability() + "','" + taco.getBreakfast()+ "')";
        db.execSQL( sqlInsert );
        db.close( );

    }

    public void insertTopping( Topping topping ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " +
                TABLE_TOPPING;
        sqlInsert += " values( null, '" + topping.getName( );
        sqlInsert += "', '{" + topping.getPrice( ) + "}' ,'" + topping.getAvailability() + "','" + topping.getBreakfast()+ "','" + topping.getType() + "')";
        db.execSQL( sqlInsert );
        db.close( );

    }
    public void updateTacoById(int id, String newName, double newPrice, String newAvailability, String newBreakfast){
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlUpdate = "update " +
                TABLE_TACO;
        sqlUpdate += " set " + NAME + " = '{" + newName + "}'";
        sqlUpdate += ", " + PRICE + " = '{" + newPrice + "}'";
        sqlUpdate += ", " + AVAILABLE + " = '{" + newAvailability + "}'";
        sqlUpdate += ", " + BREAKFAST + " = '{" + newBreakfast + "}'";
        sqlUpdate += " where id = " + id;
        db.execSQL( sqlUpdate );
        db.close( );
    }
    public void deleteTacoById(int id){
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDelete = "delete from " +
                TABLE_TACO;
        sqlDelete += " where id = " + id;
        db.execSQL( sqlDelete );
        db.close( );
    }

    public void deleteTacoByName(String name){
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDelete = "delete from " +
                TABLE_TACO;
        sqlDelete += " where "+NAME+" = " + "'"+name+"'";
        db.execSQL( sqlDelete );
        db.close( );
    }
    public void deleteToppingByName(String name){
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDelete = "delete from " +
                TABLE_TOPPING;
        sqlDelete += " where "+NAME+" = '" + name +"'";
        db.execSQL( sqlDelete );
        db.close( );
    }


    public Taco selectTacoById( int id ) {
        // select the row in the taco table
        // whose id value is id
        // return a reference to the Candy object
        // stored in that row
        SQLiteDatabase db = this.getWritableDatabase( );
// construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_TACO;
        sqlQuery += " where " + ID + " = " + id;

// call rawQuery to execute the select query
        Cursor cursor = db.rawQuery( sqlQuery, null );
        Log.w("cursor:",cursor.toString());
// process the result of the query
        Taco taco = null;
        if( cursor.moveToFirst( ) )
            taco = new Taco(
                    Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ),
                    cursor.getDouble( 2 ),
                    cursor.getString(3),
                    cursor.getString(4) );
        cursor.close();
        return taco;





    }

    public Taco selectTacoByName( String name ) {
        // select the row in the taco table
        // whose id value is id
        // return a reference to the Candy object
        // stored in that row
        SQLiteDatabase db = this.getWritableDatabase( );
// construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_TACO;
        sqlQuery += " where " + NAME + " = " + "'" + name + "'";

// call rawQuery to execute the select query
        Cursor cursor = db.rawQuery( sqlQuery, null );
        Log.w("cursor:",cursor.toString());
// process the result of the query
        Taco taco = null;
        if( cursor.moveToFirst( ) )
            taco = new Taco(
                    Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ),
                    cursor.getDouble( 2 ),
                    cursor.getString(3),
                    cursor.getString(4) );
        cursor.close();
        return taco;




    }

    public Topping selectTopppingByName( String name ) {
        // select the row in the taco table
        // whose id value is id
        // return a reference to the Candy object
        // stored in that row
        SQLiteDatabase db = this.getWritableDatabase( );
// construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_TOPPING;
        sqlQuery += " where " + NAME + " = " + "'" + name + "'";

// call rawQuery to execute the select query
        Cursor cursor = db.rawQuery( sqlQuery, null );
        Log.w("cursor:",cursor.toString());
// process the result of the query
        Topping topping = null;
        if( cursor.moveToFirst( ) )
            topping = new Topping(
                    Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ),
                    cursor.getDouble( 2 ),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5));
        cursor.close();
        return topping;




    }

    public Topping selectToppingByName( String name ) {
        // select the row in the taco table
        // whose id value is id
        // return a reference to the Candy object
        // stored in that row
        SQLiteDatabase db = this.getWritableDatabase( );
// construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_TOPPING;
        sqlQuery += " where " + NAME + " = " + "'" + name + "'";

// call rawQuery to execute the select query
        Cursor cursor = db.rawQuery( sqlQuery, null );
        Log.w("cursor:",cursor.toString());
// process the result of the query
        Topping topping = null;
        if( cursor.moveToFirst( ) )
            topping = new Topping(
                    Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ),
                    cursor.getDouble( 2 ),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5) );
        cursor.close();
        return topping;




    }

    public ArrayList<Taco> selectAllTacos( ) {
        SQLiteDatabase db = this.getWritableDatabase( );
// construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_TACO;

// call rawQuery to execute the select query
        Cursor cursor = db.rawQuery( sqlQuery, null );
// process the result of the query
        // select all the rows in the taco table
        // return an ArrayList of Taco objects
        // build a Taco object, then return it
        ArrayList<Taco> tacos = new ArrayList<Taco>( );
        while( cursor.moveToNext( ) ) {
            Taco currentTaco = new Taco( Integer.parseInt(
                    cursor.getString( 0 ) ),
                    cursor.getString( 1 ), cursor.getDouble( 2 ),cursor.getString(3),cursor.getString(4) );
            tacos.add( currentTaco );
        }
        db.close( );
        return tacos;
    }

    public ArrayList<Topping> selectAllTacoToppings( ) {
        SQLiteDatabase db = this.getWritableDatabase( );
// construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_TOPPING;
        sqlQuery += " where " + TYPE + " = 'taco'";

// call rawQuery to execute the select query
        Cursor cursor = db.rawQuery( sqlQuery, null );
// process the result of the query
        // select all the rows in the taco table
        // return an ArrayList of Taco objects
        // build a Taco object, then return it
        ArrayList<Topping> toppings = new ArrayList<Topping>( );
        while( cursor.moveToNext( ) ) {
            Topping currentTopping = new Topping( Integer.parseInt(
                    cursor.getString( 0 ) ),
                    cursor.getString( 1 ), cursor.getDouble( 2 ),cursor.getString(3),cursor.getString(4),cursor.getString(5) );
            toppings.add( currentTopping );
        }
        db.close( );
        return toppings;
    }


    public ArrayList<Topping> selectAllSideToppings( ) {
        SQLiteDatabase db = this.getWritableDatabase( );
// construct sqlQuery, a select query
        String sqlQuery = "select * from " + TABLE_TOPPING;
        sqlQuery += " where " + TYPE + " = 'side'";

// call rawQuery to execute the select query
        Cursor cursor = db.rawQuery( sqlQuery, null );
// process the result of the query
        // select all the rows in the taco table
        // return an ArrayList of Taco objects
        // build a Taco object, then return it
        ArrayList<Topping> toppings = new ArrayList<Topping>( );
        while( cursor.moveToNext( ) ) {
            Topping currentTopping = new Topping( Integer.parseInt(
                    cursor.getString( 0 ) ),
                    cursor.getString( 1 ), cursor.getDouble( 2 ),cursor.getString(3),cursor.getString(4),cursor.getString(5) );
            toppings.add( currentTopping );
        }
        db.close( );
        return toppings;
    }





}