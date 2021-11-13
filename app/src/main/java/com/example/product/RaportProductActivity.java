package com.example.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsetsAnimation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapters.DealListAdapter;
import com.example.adapters.DealProductAdapter;
import com.example.objects.DealList;
import com.example.objects.DealProduct;

import java.util.ArrayList;

public class RaportProductActivity extends AppCompatActivity {

    ArrayList<DealProduct> dealProducts = new ArrayList<>();
    String id_dealList;
    ListView raportProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raport_product);


        Bundle argument = getIntent().getExtras();
        id_dealList = argument.get("id").toString();
        ini();
        makeList();


    }
    private void ini()
    {
        ArrayList<String> id = DealProduct.showAll(this,id_dealList);

        Toast toast = Toast.makeText(this,Integer.toString(id.size()),Toast.LENGTH_SHORT);
        toast.show();

        for (int i=0;i<id.size();i++)
        {
            dealProducts.add(new DealProduct(id.get(i),this));

        }

    }
    private void makeList()
    {



        // получаем элемент ListView
        raportProduct = findViewById(R.id.raportProduct);

        // создаем адаптер
        DealProductAdapter dealListAdapter = new DealProductAdapter(this, R.layout.list_deal_product, dealProducts);

        // устанавливаем для списка адаптер
        raportProduct.setAdapter(dealListAdapter);



    }
}