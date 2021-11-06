package com.example.objects;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Category {
    public String name;
    public String id;
    public Activity activity;

    public Category(Activity activity, String id)
    {
        Database database = new Database(activity);
        this.id=id;
        this.name =  database.select_alone("name","category","WHERE id ="+this.id);
    }

    public Category(Activity activity)
    {
        this.activity = activity;
    }

    public void add(String name)
    {

        SQLiteDatabase db = this.activity.getBaseContext().openOrCreateDatabase("app1.db" , MODE_PRIVATE, null);
        db.execSQL("INSERT  INTO category (name) VALUES ('"+name+"');");
    }
    public ArrayList<String> showAll()
    {
        Database database = new Database(activity);
        return database.select("id","category","");
    }

    public String searchId(String name)
    {
        Database database = new Database(activity);
        return database.select_alone("id","category","WHERE name ="+name);
    }
}
