package com.example.objects;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Database {
    public Activity activity;
    public Database(Activity activity)
    {
        this.activity = activity;
    }
    public void create()
    {


        SQLiteDatabase db = this.activity.getBaseContext().openOrCreateDatabase("app1.db" , MODE_PRIVATE, null );

        db.execSQL("CREATE TABLE IF NOT EXISTS prod (id INTEGER PRIMARY KEY   AUTOINCREMENT, " +
                "name VARCHAR(255), " +
                "code VARCHAR(255), " +
                "img VARCHAR(255), " +
                "category VARCHAR(255), " +
                "quantity REAL, " +
                "price1 REAL, " +
                "price2 REAL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS category (id INTEGER PRIMARY KEY   AUTOINCREMENT, name TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS dealList (id INTEGER PRIMARY KEY   AUTOINCREMENT, " +
                "sum REAL , contragent_id INTEGER, type VARCHAR(255) ,Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP  )");

        db.execSQL("CREATE TABLE IF NOT EXISTS dealProduct (id INTEGER PRIMARY KEY   AUTOINCREMENT, " +
                "id_prod INTEGER," +
                "id_dealList INTEGER," +
                "quantity REAL," +
                "discount REAL," +
                "price REAL" +
                ")");

    }
    public ArrayList<String> select(String column, String table, String parametr)
    {
        ArrayList<String> select = new ArrayList<>();
        SQLiteDatabase db = this.activity.getBaseContext().openOrCreateDatabase("app1.db" , MODE_PRIVATE, null );

        Cursor query = db.rawQuery("SELECT "+column+" FROM "+table+" "+parametr+";", null);

        if(query.moveToFirst()){
            do{
                select.add( query.getString(0));
            }
            while(query.moveToNext());
        }

        query.close();
        db.close();
        return select;
    }

    public String select_alone(String column, String table, String parametr)
    {
        ArrayList<String> select=select(column, table, parametr);
        return select.get(0);
    }
}
