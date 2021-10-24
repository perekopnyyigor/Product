package com.example.objects;

import android.database.sqlite.SQLiteDatabase;

public class Category {
    String name;
    Integer id;

    public Category(Integer id)
    {
        this.id=id;

    }
    public static void add(String name)
    {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("app.db", null);
        db.execSQL("INSERT OR IGNORE INTO category (name) VALUES ("+name+");");
    }
}
