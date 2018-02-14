package com.example.root.rock3test2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.rock3test2.R;
import com.example.root.rock3test2.acitivities.MainActivity;
import com.example.root.rock3test2.model.Product;

import java.util.List;

/**
 * Created by root on 12/2/18.
 */

public class RecyclerCartAdapter extends RecyclerView.Adapter<RecyclerCartAdapter.MyViewHolder> {

    public Context context;
    private List<Product> cartList;


    public RecyclerCartAdapter(List<Product> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
    }


    @Override
    public RecyclerCartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_row, parent, false);

        return new RecyclerCartAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Product dataList = cartList.get(position);
        final double price = dataList.getPrice();
        // int year=Integer.valueOf(dataList.getFirstappearance());
        holder.txtProductName.setText(dataList.getProduct_name());
        holder.txtProductTotalQty.setText(String.valueOf(dataList.getQty()));
        holder.txtProductTotalAmount.setText(context.getResources().getString(R.string.dolar)+" "+String.valueOf(dataList.getPrice()));
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)context).updateProductQty(dataList.getSr_no(),dataList.getQty(),position);
                removeItem(position);

            }
        });


    }

    public void removeItem(int position) {

        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtProductName, txtProductTotalQty, txtProductTotalAmount;
        public ImageView btn_delete;

        // final LinearLayout.LayoutParams params;
        public MyViewHolder(View view) {
            super(view);
            txtProductName = (TextView) view.findViewById(R.id.txtProductName);
            txtProductTotalQty = (TextView) view.findViewById(R.id.txtProductTotalQty);
            txtProductTotalAmount = (TextView) view.findViewById(R.id.txtProductTotalAmount);
            btn_delete = (ImageView) view.findViewById(R.id.btn_delete);


        }


    }


}