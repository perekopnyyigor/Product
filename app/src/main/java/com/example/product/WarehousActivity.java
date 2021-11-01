package com.example.product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapters.MainMenuAdapter;
import com.example.adapters.ProductAdapter;
import com.example.objects.MainMenu;
import com.example.objects.Product;

import java.util.ArrayList;

public class WarehousActivity extends AppCompatActivity {

    Product product = new Product(this);
    ListView productsList;
    EditText editText ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehous);

        editText = findViewById(R.id.name);

        makeList();
        changeEditText();


    }
    private void changeEditText()
    {
        editText.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                String strCatName = editText.getText().toString();
                Toast toast = Toast.makeText(WarehousActivity.this, strCatName,Toast.LENGTH_LONG);
                toast.show();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    private void makeList()
    {

        ArrayList<Product> mainMenu = ini();
        // получаем элемент ListView
        productsList = findViewById(R.id.productsList);

        // создаем адаптер
        ProductAdapter productAdapter = new ProductAdapter(this, R.layout.list_warehous, mainMenu);

        // устанавливаем для списка адаптер
        productsList.setAdapter(productAdapter);
    }
    private ArrayList<Product> ini()
    {
        ArrayList<String> category_arr = product.showAll();
        ArrayList<Product> products =new ArrayList<>();
        for(int i=0;i<category_arr.size();i++)
            products.add(new Product(category_arr.get(i),this));

        return products;

    }
}