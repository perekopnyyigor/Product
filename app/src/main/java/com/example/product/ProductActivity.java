package com.example.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adapters.MainMenuAdapter;
import com.example.functional.Img;
import com.example.objects.Category;
import com.example.objects.MainMenu;
import com.example.objects.Product;
import com.example.scan.IntentIntegrator;
import com.example.scan.IntentResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    Category category = new Category(this);
    ListView list;
    private int categorySelect;
    private Product product = new Product(this);

    private static final int CAMERA_REQUEST = 1;
    private Img img = new Img(this);
    private ImageView picView ;
    private ArrayList<String> categories_id;
    private String name;
    private String code;
    private double price1;
    private double price2;
    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        picView = findViewById(R.id.Photo);
        categories_id = category.showAll();
        fillSpiner();
        makeList();
        selectMenu();

    }

    public void addProduct(View view)
    {

        EditText editTextName = findViewById(R.id.name);
        name = editTextName.getText().toString();

        EditText editTextPrice1 = findViewById(R.id.price1);
        price1 = Double.parseDouble(editTextPrice1.getText().toString()) ;

        EditText editTextPrice2 = findViewById(R.id.price2);
        price2 = Double.parseDouble(editTextPrice2.getText().toString());

        EditText editTextCode = findViewById(R.id.code);
        code =  editTextCode.getText().toString();



        //String categoryId=categories.get(categorySelect);
        Category category = new Category(this,categories_id.get(categorySelect));




        product.add(name, code, image, category.id ,price1, price2);

    }
    private void fillSpiner()
    {

        ArrayList<String> categories = new ArrayList<>();

        for (int i =0;i<categories_id.size();i++)
        {
            Category category = new Category(this,categories_id.get(i));



            categories.add(category.name);
        }

        Spinner spinner = findViewById(R.id.spinner);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                categorySelect = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }
    private void makeList()
    {

        ArrayList<MainMenu> mainMenu = ini();
        // получаем элемент ListView
        list = findViewById(R.id.list);

        // создаем адаптер
        MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(this, R.layout.list_menu, mainMenu);

        // устанавливаем для списка адаптер
        list.setAdapter(mainMenuAdapter);


    }
    private ArrayList<MainMenu> ini()
    {
        ArrayList<MainMenu> mainMenu = new ArrayList<>();
        mainMenu.add(new MainMenu("Штрихкод",R.drawable.code));
        mainMenu.add(new MainMenu("Фото",R.drawable.code));
        return mainMenu;
    }
    private void selectMenu()
    {

        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                switch (position)
                {
                    case 0:
                        IntentIntegrator scanIntegrator = new IntentIntegrator(ProductActivity.this);
                        scanIntegrator.initiateScan();
                        break;
                    case 1:
                        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(captureIntent, CAMERA_REQUEST);
                        break;

                }
            }
        };
        list.setOnItemClickListener(itemListener);
    }

    //--------------------------------------------------------------------------------------Результат сканирования
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//retrieve scan result
        super.onActivityResult(requestCode, resultCode, intent);

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            EditText editText = findViewById(R.id.code);
            editText.setText(scanContent);
        }

        else if (requestCode == CAMERA_REQUEST ) {

            image = img.result(intent,picView);

        }
    }

}