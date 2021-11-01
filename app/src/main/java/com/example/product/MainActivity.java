package com.example.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapters.MainMenuAdapter;
import com.example.objects.Database;
import com.example.objects.MainMenu;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Database database = new Database(this);
    ListView countriesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        database.create();
        makeList();
        selectMenu();

    }
    private void makeList()
    {

        ArrayList<MainMenu> mainMenu = ini();
        // получаем элемент ListView
        countriesList = findViewById(R.id.countriesList);

        // создаем адаптер
         MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(this, R.layout.list_menu, mainMenu);

        // устанавливаем для списка адаптер
        countriesList.setAdapter(mainMenuAdapter);


    }
    private ArrayList<MainMenu> ini()
    {
        ArrayList<MainMenu> mainMenu = new ArrayList<>();
        mainMenu.add(new MainMenu("Продажа",R.drawable.sell));
        mainMenu.add(new MainMenu("Закуп",R.drawable.bue));
        mainMenu.add(new MainMenu("Товар",R.drawable.produkt));
        mainMenu.add(new MainMenu("Категории",R.drawable.category));
        mainMenu.add(new MainMenu("Склад",R.drawable.warehouse));
        return mainMenu;
    }
    private void selectMenu()
    {

        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent;
                switch (position)
                {
                    case 2:
                         intent = new Intent(MainActivity.this, ProductActivity.class);
                         startActivity(intent);
                         break;
                    case 3:
                         intent = new Intent(MainActivity.this, CategoryActivity.class);
                         startActivity(intent);
                         break;
                    case 4:
                        intent = new Intent(MainActivity.this, WarehousActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        };
        countriesList.setOnItemClickListener(itemListener);
    }

}