package com.example.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.adapters.MainMenuAdapter;
import com.example.objects.Database;
import com.example.objects.MainMenu;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Database database = new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        database.create();
        makeList();

    }
    private void makeList()
    {

        ArrayList<MainMenu> mainMenu = ini();
        // получаем элемент ListView
        ListView countriesList = findViewById(R.id.countriesList);

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

        return mainMenu;
    }

}