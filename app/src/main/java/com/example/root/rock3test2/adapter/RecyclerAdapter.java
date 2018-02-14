package com.example.root.rock3test2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.rock3test2.R;
import com.example.root.rock3test2.acitivities.MainActivity;
import com.example.root.rock3test2.model.Product;

import java.util.List;

/**
 * Created by root on 9/2/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    public Context context;
    private List<Product> productList;
    private List<Product> cartList;

    public RecyclerAdapter(List<Product> productList, List<Product> cartList, Context context) {
        this.productList = productList;
        this.context = context;
        this.cartList = cartList;
    }

    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_row, parent, false);

        return new RecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapter.MyViewHolder holder, final int position) {

        final Product dataList = productList.get(position);
        final double price = dataList.getPrice();

        holder.txtProductNM.setText(dataList.getProduct_name());
        holder.txtProductDescription.setText(dataList.getDescription());



        String nameOfImage = dataList.getImage();
        int resId = context.getResources().getIdentifier(nameOfImage, "drawable", context.getPackageName());
        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), resId);
        holder.imgProduct.setImageBitmap(bitmap2);

        holder.txtProductPrice.setText(context.getResources().getString(R.string.dolar) + " " + String.valueOf(price));
        for (int i = 0; i < cartList.size(); i++) {
                if(dataList.getSr_no()==cartList.get(i).getSr_no()){
                    holder.txtSelectedQty.setText(String.valueOf(cartList.get(i).getQty()));
                    holder.txtAmountPayable.setText(String.valueOf(cartList.get(i).getPrice()));
                    holder.badge_notification_1.setText(String.valueOf(cartList.get(i).getQty()));
                    break;
                }
        }

        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lastQty = Integer.valueOf(holder.txtSelectedQty.getText().toString());
                int totalQty = dataList.getQty();

                if (lastQty < totalQty) {
                    int currentQty = lastQty + 1;
                    holder.txtSelectedQty.setText(String.valueOf(currentQty));

                    holder.txtAmountPayable.setText(String.valueOf(price * currentQty));


                }else{
                    Toast.makeText(context,"No More Stocks Available",Toast.LENGTH_LONG).show();
                }

            }
        });
        holder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lastQty = Integer.valueOf(holder.txtSelectedQty.getText().toString());

                if (lastQty > 0) {
                    int currentQty = lastQty - 1;
                    holder.txtSelectedQty.setText(String.valueOf(currentQty));

                    holder.txtAmountPayable.setText(String.valueOf(price * currentQty));


                }

            }
        });


        holder.btnaddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.badge_notification_1.setText(holder.txtSelectedQty.getText().toString());


                double _payAmount = Double.valueOf(holder.txtAmountPayable.getText().toString());
                ((MainActivity) context).addToCart(dataList.getSr_no(), dataList.getProduct_name(), Integer.valueOf(holder.txtSelectedQty.getText().toString()), _payAmount, dataList.getQty());
            }
        });
    }

    public void removeItem(int position) {
        // Const.Products.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void updateRow(int position){

    }


    public  class MyViewHolder extends RecyclerView.ViewHolder {
        public  TextView txtProductNM, txtProductDescription, txtProductPrice, txtSelectedQty, txtAmountPayable, badge_notification_1;

        public ImageView ivAdd, ivRemove;
        public Button btnaddToCart;
        public ImageView imgProduct;

        public MyViewHolder(View view) {
            super(view);
            txtProductNM = (TextView) view.findViewById(R.id.txtProductNM);
            txtProductDescription = (TextView) view.findViewById(R.id.txtProductDescription);
            txtProductPrice = (TextView) view.findViewById(R.id.txtProductPrice);
            ivAdd = (ImageView) view.findViewById(R.id.IVadd);
            ivRemove = (ImageView) view.findViewById(R.id.IVremove);
            txtSelectedQty = (TextView) view.findViewById(R.id.txtSelectedQty);
            txtAmountPayable = (TextView) view.findViewById(R.id.txtAmountPayable);
            badge_notification_1 = (TextView) view.findViewById(R.id.badge_notification_1);
            imgProduct =(ImageView)view.findViewById(R.id.imgProduct);
            btnaddToCart = (Button) view.findViewById(R.id.btnaddToCart);

        }


    }
}
