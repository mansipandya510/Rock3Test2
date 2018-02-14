package com.example.root.rock3test2.acitivities;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.root.rock3test2.R;
import com.example.root.rock3test2.adapter.RecyclerAdapter;
import com.example.root.rock3test2.adapter.RecyclerCartAdapter;
import com.example.root.rock3test2.db.ProductListAdd;
import com.example.root.rock3test2.db.SqliteHandler;
import com.example.root.rock3test2.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView prodRecyclerView, cartRecyclerView;
    // List<Product> productList;
    SqliteHandler sqliteHandler;
    TextView txtTotalQty, txtTotalAmount;
    List<Product> cartLst = new ArrayList<>();
    List<Product> productLst = new ArrayList<>();
    LinearLayout LyQtyMain;
    TextView alertTxtTotalAmount, alerttxtErrorNoData;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Create Sqllite Handler
        sqliteHandler = new SqliteHandler(MainActivity.this);

        //Check data is already Added or not
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("dataAvailable", false)) {
            ProductListAdd productListAdd = new ProductListAdd();
            productListAdd.addProduct(sqliteHandler, MainActivity.this
            );
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("dataAvailable", true);
            editor.commit();

        }

        //delete cart Delete
        sqliteHandler.deleteData();

        //define view
        prodRecyclerView = (RecyclerView) findViewById(R.id.prodLst);
        txtTotalAmount = (TextView) findViewById(R.id.txtTotalAmount);
        txtTotalQty = (TextView) findViewById(R.id.txtTotalQty);
        LyQtyMain = (LinearLayout) findViewById(R.id.LyQtyMain);


        //Initalize Product list
        productLst = sqliteHandler.fetchData();
        recyclerAdapter = new RecyclerAdapter(productLst, cartLst, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);

        prodRecyclerView.setLayoutManager(mLayoutManager);
        prodRecyclerView.setItemAnimator(new DefaultItemAnimator());
        prodRecyclerView.setAdapter(recyclerAdapter);
        prodRecyclerView.setHasFixedSize(true);

        LyQtyMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }


    //function "calculateTotal"  is calculate cart total Quantity and Amount and Display into Textview
    public void calculateTotal() {

        double currentAmount = 0;
        int currentQty = 0;
        cartLst = sqliteHandler.fetchCartData();
        for (int i = 0; i < cartLst.size(); i++) {
            currentAmount += cartLst.get(i).getPrice();
            currentQty += cartLst.get(i).getQty();
        }

        txtTotalAmount.setText(String.valueOf(currentAmount));
        txtTotalQty.setText(String.valueOf(currentQty));

    }


    //Function "addToCart" add the selected product into cart and calculate remaining qty and update databasae
    public void addToCart(int sr_no, String productName, int currentQty, double amount, int toalQty) {
        if(currentQty!=0) {
            boolean isContain = false;
            for (int i = 0; i < cartLst.size(); i++) {
                if (cartLst.get(i).getSr_no() == sr_no) {
                    isContain = true;
                    break;
                }
            }


            if (isContain) {
                sqliteHandler.updateCart(sr_no, currentQty, amount);
            } else {
                sqliteHandler.addCart(sr_no, productName, currentQty, amount);
            }

            int remainingQty = toalQty - currentQty;

            sqliteHandler.updateProduct(sr_no, remainingQty);
        }else{
            sqliteHandler.removeItemFormCart(sr_no);
        }
        calculateTotal();
    }


    //Function "showDialog" create dialog for cart list
    public void showDialog() {

        final Dialog alertDialog = new Dialog(MainActivity.this);

        alertDialog.setCancelable(true);
        alertDialog.setContentView(R.layout.cart_list);
        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        cartRecyclerView = (RecyclerView) alertDialog.findViewById(R.id.cartLst);
        alertTxtTotalAmount = (TextView) alertDialog.findViewById(R.id.txtTotalAmount);
        alerttxtErrorNoData = (TextView) alertDialog.findViewById(R.id.txtErrorNoData);
        ImageView btn_closebtn = (ImageView) alertDialog.findViewById(R.id.btn_closebtn);
        if (cartLst.size() > 0) {
            RecyclerCartAdapter cartAdapter = new RecyclerCartAdapter(cartLst, MainActivity.this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
            cartRecyclerView.setLayoutManager(mLayoutManager);
            cartRecyclerView.setItemAnimator(new DefaultItemAnimator());
            cartRecyclerView.setAdapter(cartAdapter);
        } else {
            alerttxtErrorNoData.setVisibility(View.VISIBLE);
            cartRecyclerView.setVisibility(View.GONE);
        }
        alertTxtTotalAmount.setText(getResources().getString(R.string.dolar) + " " + txtTotalAmount.getText().toString());
        btn_closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }

    //Function "updateProductQty" remove delete item from cart,it will calculate qty and update database
    public void updateProductQty(int sr_no, int qty, int pos) {
        cartLst.remove(pos);
        int totalQty = 0;
        int Row_pos = 0;
        //For Find total Qty
        for (int i = 0; i < productLst.size(); i++) {
            if (productLst.get(i).getSr_no() == sr_no) {
                totalQty = productLst.get(i).getQty();
                Row_pos = i;
                break;
            }
        }
        int addedQty = totalQty + qty;
        double currentAmount = 0;
        int currentQty = 0;

        //For Update Cart total amount and Qty
        for (int i = 0; i < cartLst.size(); i++) {
            currentAmount += cartLst.get(i).getPrice();
            currentQty += cartLst.get(i).getQty();
        }


        txtTotalAmount.setText(String.valueOf(currentAmount));
        txtTotalQty.setText(String.valueOf(currentQty));
        sqliteHandler.removeItemFormCart(sr_no);
        sqliteHandler.updateProduct(sr_no, addedQty);
        alertTxtTotalAmount.setText(getResources().getString(R.string.dolar) + " " + String.valueOf(currentAmount));
        productLst = sqliteHandler.fetchData();
        recyclerAdapter = new RecyclerAdapter(productLst, cartLst, MainActivity.this);


        prodRecyclerView.setAdapter(recyclerAdapter);
        if (cartLst.size() > 0) {
            alerttxtErrorNoData.setVisibility(View.GONE);
            cartRecyclerView.setVisibility(View.VISIBLE);
        } else {
            alerttxtErrorNoData.setVisibility(View.VISIBLE);
            cartRecyclerView.setVisibility(View.GONE);
        }
    }
}
