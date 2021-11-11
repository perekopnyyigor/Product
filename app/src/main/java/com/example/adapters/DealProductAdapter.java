package com.example.adapters;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.objects.DealProduct;
import com.example.objects.MainMenu;
import com.example.objects.Product;
import com.example.product.R;

import java.util.List;

public class DealProductAdapter extends ArrayAdapter<DealProduct> {

    private LayoutInflater inflater;
    private int layout;
    private List<DealProduct> dealProducts;

    public DealProductAdapter(Context context, int resource, List<DealProduct> products) {
        super(context, resource, products);
        this.dealProducts = products;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView flagView = view.findViewById(R.id.flag);
        TextView nameView = view.findViewById(R.id.name);
        TextView quantityView = view.findViewById(R.id.quantity);
        TextView priceView = view.findViewById(R.id.price);
        TextView costView = view.findViewById(R.id.cost);


        DealProduct dealProduct = dealProducts.get(position);

        Uri uri =  Uri.parse( dealProduct.img );
        flagView.setImageURI(uri);

        nameView.setText(dealProduct.name);
        quantityView.setText(Double.toString(dealProduct.quantity));
        priceView.setText(Double.toString(dealProduct.price));
        costView.setText(Double.toString(dealProduct.cost));


        return view;
    }
}