package com.example.objects;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class DealProduct {
    public String productId;
    public double quantity;
    public double discount;
    public Activity activity;
    public double price;
    public String name;
    public String img;
    public double cost;


    public DealProduct(String productId, double quantity, double discount, Activity activity)
    {
        this.productId=productId;
        this.quantity = quantity;
        this.discount = discount;
        Product product = new Product(productId, activity);
        this.name = product.name;
        this.img=product.img;
        this.price = product.price2 - product.price2/100 * discount;
        this.cost = this.price*this.quantity;

    }
    public DealProduct(Activity activity)
    {
        this.activity = activity;
    }
    public void add(int id_prod, int id_dealList, double quantity, double discount, double price)
    {

        SQLiteDatabase db = this.activity.getBaseContext().openOrCreateDatabase("app1.db" , MODE_PRIVATE, null);
        db.execSQL("INSERT  INTO dealProduct (id_prod, id_dealList, quantity, discount, quantity, price) VALUES " +
                "('"+id_prod+"' , '"+id_dealList+"' , '"+quantity+"', '"+discount+"' , '"+price+"');");


    }
    public static double sum(ArrayList<DealProduct> products)
    {
        double summ = 0;
        for (int i = 0; i<products.size(); i++)
        summ += products.get(i).cost;
        return summ;
    }

    public static void add_all(Activity activity, ArrayList<DealProduct> products, int id_dealList)
    {
        SQLiteDatabase db = activity.getBaseContext().openOrCreateDatabase("app1.db" , MODE_PRIVATE, null);
        for (int i = 0; i<products.size(); i++)
        {
            db.execSQL("INSERT  INTO dealProduct (id_prod, id_dealList, quantity, discount, price) VALUES " +
                    "('"+products.get(i).productId+"' , '"+id_dealList+"' , '"+products.get(i).quantity+"', '"+products.get(i).discount+"' , '"+products.get(i).price+"');");
        }

    }



}
