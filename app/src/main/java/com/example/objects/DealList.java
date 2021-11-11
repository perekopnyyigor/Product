package com.example.objects;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class DealList {
    public Activity activity;
    public String id;
    public String date;
    public String type;
    public double sum;


    public DealList(Activity activity)
    {
        this.activity = activity;

    }
    public DealList(Activity activity, String id)
    {
        //Toast toast =Toast.makeText(activity,"id",Toast.LENGTH_SHORT);
        //toast.show();

        Database database = new Database(activity);
        this.id=id;
        this.activity = activity;

        this.date =  database.select_alone("Timestamp","dealList","WHERE id ="+this.id);
        this.type =  database.select_alone("type","dealList","WHERE id ="+this.id);
       this.sum = Double.parseDouble(database.select_alone("sum","dealList","WHERE id ="+this.id));
    }

    public static int add(Activity activity, double sum, String type, String contragent_id)
    {
        SQLiteDatabase db = activity.getBaseContext().openOrCreateDatabase("app1.db" , MODE_PRIVATE, null);
        ContentValues cv = new ContentValues();
        cv.put("sum",sum);
        cv.put("type",type);
        cv.put("contragent_id",contragent_id);
        int punkt =  (int) db.insert("dealList",null,cv);
        return punkt;

    }
    public static ArrayList<String> showAll(Activity activity)
    {
        Database database = new Database(activity);
        return database.select("id","dealList","");
    }
}
