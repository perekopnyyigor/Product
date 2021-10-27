package com.example.product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.objects.Category;
import com.example.objects.Database;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    Category category = new Category(this);
    public EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        editText = findViewById(R.id.name);
        this.makeList();
    }
    private void makeList()
    {

        ArrayList<String> category_arr = category.showAll();
        // получаем элемент ListView
        ListView countriesList = findViewById(R.id.countriesList);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, category_arr);

        // устанавливаем для списка адаптер
        countriesList.setAdapter(adapter);
    }
    public void addCategory(View view)
    {
        String name =  editText.getText().toString();
        category.add(name);
        makeList();
    }
}