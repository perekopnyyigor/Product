package com.example.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapters.DealProductAdapter;
import com.example.adapters.ProductAdapter;
import com.example.objects.DealList;
import com.example.objects.DealProduct;
import com.example.objects.Product;
import com.example.scan.IntentIntegrator;
import com.example.scan.IntentResult;

import java.util.ArrayList;

public class DealActivity extends AppCompatActivity {
    Product product = new Product(this);
    ListView productsList;
    EditText editText ;
    String type;
    private DealList dealList = new DealList(this);
    private ArrayList<Product> products;
    private ArrayList<DealProduct> dealProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        type = arguments.get("type").toString();

        setContentView(R.layout.activity_deal);
        dealProducts = new ArrayList<>();
        editText = findViewById(R.id.name);
        products = ini("");
        makeList(products);
        changeEditText();
        selectMenu();
    }

    public void add(View view)
    {
        double summ = DealProduct.sum(dealProducts);
        int dealListId = DealList.add(this,summ,type, "contragent");
        DealProduct.add_all(this,dealProducts,dealListId);

        Toast toast = Toast.makeText(this,Integer.toString(dealListId),Toast.LENGTH_SHORT);
        toast.show();
    }
    private void changeEditText()
    {
        editText.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                String strCatName = editText.getText().toString();
                products = ini(strCatName);
                makeList(products);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    private void makeList(ArrayList<Product> list)
    {
        // получаем элемент ListView
        productsList = findViewById(R.id.productsList);

        // создаем адаптер
        ProductAdapter productAdapter = new ProductAdapter(this, R.layout.list_warehous, list);

        // устанавливаем для списка адаптер
        productsList.setAdapter(productAdapter);
    }
    private void makeDealList(ArrayList<DealProduct> list)
    {
        // получаем элемент ListView
        productsList = findViewById(R.id.dealList);

        // создаем адаптер
        DealProductAdapter productAdapter = new DealProductAdapter(this, R.layout.list_deal_product, list);

        // устанавливаем для списка адаптер
        productsList.setAdapter(productAdapter);
    }
    private ArrayList<Product> ini(String litter)
    {
        ArrayList<String> category_arr = product.showAll(litter);
        ArrayList<Product> products =new ArrayList<>();
        for(int i=0;i<category_arr.size();i++)
            products.add(new Product(category_arr.get(i),this));
        return products;

    }
    public void scan(View view)
    {
        IntentIntegrator scanIntegrator = new IntentIntegrator(DealActivity.this);
        scanIntegrator.initiateScan();
    }
    private void selectMenu()
    {

        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                String product_id = products.get(position).id;
                Intent intent = new Intent(DealActivity.this,ProductDealActivity.class);
                intent.putExtra("id", product_id);
                startActivityForResult(intent,100);


            }
        };
        productsList.setOnItemClickListener(itemListener);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//retrieve scan result
        super.onActivityResult(requestCode, resultCode, intent);

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            String id = product.serchId(scanContent);
            Intent intentProduct = new Intent(DealActivity.this,ProductDealActivity.class);
            intentProduct.putExtra("id", id);
            startActivity(intentProduct);
        }
        if (requestCode==100)
        {
            String id = intent.getStringExtra("id");
            double quantity = Double.parseDouble(intent.getStringExtra("quantity"));
            double discount = Double.parseDouble(intent.getStringExtra("discount"));
            dealProducts.add(new DealProduct(id,quantity,discount,this));
            makeDealList(dealProducts);
            //Toast toast = Toast.makeText(this,name,Toast.LENGTH_SHORT);
            //toast.show();
        }


    }
}