package com.example.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.objects.Product;

public class ProductDealActivity extends AppCompatActivity {

    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_deal);
        Bundle arguments = getIntent().getExtras();
        String id = arguments.get("id").toString();
        product = new Product(id,this);
        fill_form();
    }
    private void fill_form()
    {
        TextView textViewName = findViewById(R.id.name);
        textViewName.setText(product.name);

        TextView textViewCode = findViewById(R.id.code);
        textViewCode.setText(product.code);

        EditText editTextPrice = findViewById(R.id.price);
        editTextPrice.setText(Double.toString(product.price2));

        ImageView imageView = findViewById(R.id.Photo);
        Uri uri = Uri.parse(product.img);
        imageView.setImageURI(uri);

    }

}