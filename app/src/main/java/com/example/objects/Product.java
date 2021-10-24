package com.example.objects;

import android.database.sqlite.SQLiteDatabase;

public class Product {
    String name;
    String code;
    Double price1;
    Double price2;
    public Product(String code)
    {
        this.code=code;

    }
    public static void add(String name, String code, Double price1, Double price2)
    {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("app.db", null);
        db.execSQL("INSERT OR IGNORE INTO products VALUES ("+name+", "+code+","+price1+","+price2+");");
    }
}
