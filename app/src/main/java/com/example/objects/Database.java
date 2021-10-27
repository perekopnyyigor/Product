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


        SQLiteDatabase db = this.activity.getBaseContext().openOrCreateDatabase("app.db" , MODE_PRIVATE, null );

        db.execSQL("CREATE TABLE IF NOT EXISTS products (id INTEGER PRIMARY KEY   AUTOINCREMENT, " +
                "name TEXT, " +
                "code VARCHAR(255), " +
                "price1 REAL, " +
                "price2 REAL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS category (id INTEGER PRIMARY KEY   AUTOINCREMENT, name TEXT)");
    }
    public ArrayList<String> select(String column, String table, String parametr)
    {
        ArrayList<String> select = new ArrayList<>();
        SQLiteDatabase db = this.activity.getBaseContext().openOrCreateDatabase("app.db" , MODE_PRIVATE, null );

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
}
