package com.example.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.objects.DealList;
import com.example.objects.DealProduct;
import com.example.product.R;

import java.util.List;

public class DealListAdapter  extends ArrayAdapter<DealList> {
    private LayoutInflater inflater;
    private int layout;
    private List<DealList> dealProducts;

    public DealListAdapter(Context context, int resource, List<DealList> products) {
        super(context, resource, products);
        this.dealProducts = products;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);


        TextView dataView = view.findViewById(R.id.data);
        TextView typeView = view.findViewById(R.id.type);
        TextView sumtView = view.findViewById(R.id.sum);



        DealList dealProduct = dealProducts.get(position);




        dataView.setText(dealProduct.date);
        typeView.setText(dealProduct.type);
        sumtView.setText(Double.toString(dealProduct.sum));


        return view;
    }
}
