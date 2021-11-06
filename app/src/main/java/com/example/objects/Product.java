package com.example.objects;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Product {
    Activity activity;
    public String name;
    public String code;
    public String category;
    public String img;
    public String id;
    public double price1;
    public double price2;
    public double quantity;

    public Product(String id, Activity activity)
    {
        Database database = new Database(activity);
        this.id=id;
        this.activity = activity;

        this.name =  database.select_alone("name","prod","WHERE id ="+this.id);
        this.category =  database.select_alone("category","prod","WHERE id ="+this.id);
        this.img =  database.select_alone("img","prod","WHERE id ="+this.id);
        this.code =  database.select_alone("code","prod","WHERE id ="+this.id);
        this.quantity = Double.parseDouble(database.select_alone("quantity","prod","WHERE id ="+this.id));
    }

    public Product(Activity activity)
    {
        this.activity = activity;
    }

    public void add(String name, String code, String img, String category, double price1, double price2)
    {

        SQLiteDatabase db = this.activity.getBaseContext().openOrCreateDatabase("app1.db" , MODE_PRIVATE, null);
        db.execSQL("INSERT  INTO prod (name, code, img, category, quantity, price1, price2) VALUES " +
                "('"+name+"' , '"+code+"' , '"+img+"', '"+category+"' , '0' , '"+price1+"' , '"+price2+"');");
    }

    public ArrayList<String> showAll(String litter)
    {
        Database database = new Database(activity);
        if (litter=="")
            return database.select("id","prod","ORDER BY name");
        else
            return database.select("id","prod","WHERE name LIKE '"+litter+"%' ORDER BY name");
    }
    public String serchId(String code)
    {
        Database database = new Database(activity);
        return database.select_alone("id","prod","WHERE code ="+code);
    }

}
