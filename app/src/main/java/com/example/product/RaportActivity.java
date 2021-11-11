package com.example.product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.adapters.DealListAdapter;
import com.example.adapters.MainMenuAdapter;
import com.example.objects.DealList;
import com.example.objects.MainMenu;

import java.util.ArrayList;

public class RaportActivity extends AppCompatActivity {
    ArrayList<String> dealLists_id;

    ListView raportList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raport);
        makeList();
    }

    private ArrayList<DealList> ini()
    {
        dealLists_id = DealList.showAll(this);



        ArrayList<DealList> dealLists = new ArrayList<>();
        for (int i =0;i<dealLists_id.size();i++)
        {
            dealLists.add(new DealList(this,dealLists_id.get(i)));


        }
        return dealLists;
    }
    private void makeList()
    {

        ArrayList<DealList> dealLists =  ini();

        //Toast toast =Toast.makeText(this,dealLists.get(0).id,Toast.LENGTH_SHORT);
        //toast.show();

        // получаем элемент ListView
        raportList = findViewById(R.id.raportlList);

        // создаем адаптер
        DealListAdapter dealListAdapter = new DealListAdapter(this, R.layout.list_deal_list, dealLists);

        // устанавливаем для списка адаптер
        raportList.setAdapter(dealListAdapter);


    }

}