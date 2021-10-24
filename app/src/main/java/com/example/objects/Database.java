package com.example.objects;

import android.database.sqlite.SQLiteDatabase;

public class Database {
    public void create()
    {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("app.db", null);
        db.execSQL("CREATE TABLE IF NOT EXISTS products (name TEXT, code VARCHAR(255), price1 REAL, price2 REAL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS category (id INTEGER PRIMARY KEY   AUTOINCREMENT, name TEXT)");
    }
}
