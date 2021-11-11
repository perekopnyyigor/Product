package com.example.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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

    public void add(View view)
    {
        EditText editTextQuantity = findViewById(R.id.quantity);
        String quantity = editTextQuantity.getText().toString();

        EditText editTextDiscount = findViewById(R.id.discont);
        String discount = editTextDiscount.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("id", product.id);
        intent.putExtra("quantity", quantity);
        intent.putExtra("discount", discount);
        setResult(RESULT_OK, intent);
        finish();
    }

}