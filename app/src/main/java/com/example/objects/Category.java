package com.example.objects;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Category {
    public String name;
    public Integer id;
    public Activity activity;

    public Category(Integer id) {this.id=id;}

    public Category(Activity activity)
    {
        this.activity = activity;
    }

    public void add(String name)
    {

        SQLiteDatabase db = this.activity.getBaseContext().openOrCreateDatabase("app.db" , MODE_PRIVATE, null);
        db.execSQL("INSERT  INTO category (name) VALUES ('"+name+"');");
    }
    public ArrayList<String> showAll()
    {
        Database database = new Database(activity);
        return database.select("name","category","");
    }
}
