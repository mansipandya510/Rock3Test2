package com.example.root.rock3test2.db;

import android.app.ActionBar;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.root.rock3test2.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 12/2/18.
 */

public class SqliteHandler extends SQLiteOpenHelper {

    public static final String Database_Name = "shopping_card.db";
    public static final String Table_Name = "ProductMaster";
    public static final String Field_1 = "SR_No";
    public static final String Field_2 = "Product_Name";
    public static final String Field_3 = "Description";
    public static final String Field_4 = "Qty";
    public static final String Field_5 = "Price";
    public static final String Field_6 = "Product_Image";

    public static final String Table_Name_child="CART";
    public static final String T2_Field_1="SR_NO";
    public static final String T2_Field_2="Product_Item";
    public static final String T2_Field_3="Qty";
    public static final String T2_Field_4="Price";



    SQLiteDatabase mDatabase;
    public SqliteHandler(Context context) {
        super(context, Database_Name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlQuerty = "CREATE TABLE " + Table_Name + " (" + Field_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Field_2 + " TEXT NOT NULL, " + Field_3 + " TEXT NOT NULL, " + Field_4 + " INTEGER NOT NULL, " + Field_5 + " REAL NOT NULL, "+Field_6+" TEXT);";
        sqLiteDatabase.execSQL(sqlQuerty);

        String sqlQuerty1 = "CREATE TABLE " + Table_Name_child + " (" + T2_Field_1 + " INTEGER, " + T2_Field_2 + " TEXT NOT NULL, " + T2_Field_3 + " INTEGER NOT NULL, " + T2_Field_4 + " REAL NOT NULL, FOREIGN KEY ("+T2_Field_1+") REFERENCES "+Table_Name+"("+Field_1+"));";
        sqLiteDatabase.execSQL(sqlQuerty1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



    public void addProducts(String ProductName,String Description,int Qty,double Price,String image){
        SQLiteDatabase db = this.getWritableDatabase();

        String insertQuery="INSERT INTO "+Table_Name+ "("+Field_2+","+Field_3+","+Field_4+","+Field_5+","+Field_6+") VALUES ('"+ProductName+"', '"+Description+"', "+Qty+","+Price+", '"+image+"');";
        db.execSQL(insertQuery);
    }

    public void addCart(int sr_no,String ProductItem,int Qty,double TotalAmount){
        SQLiteDatabase db = this.getWritableDatabase();

        String insertQuery="INSERT OR REPLACE INTO  "+Table_Name_child+ "("+T2_Field_1+","+T2_Field_2+","+T2_Field_3+","+T2_Field_4+") VALUES ("+sr_no +",'"+ProductItem+"', "+Qty+", "+TotalAmount+");";
        db.execSQL(insertQuery);
    }


    public void updateCart(int sr_no,int Qty,double TotalAmount){
        SQLiteDatabase db = this.getWritableDatabase();

        String insertQuery="UPDATE "+Table_Name_child+ " SET "+T2_Field_3+"="+Qty+", "+T2_Field_4+"="+TotalAmount+" WHERE "+T2_Field_1+"="+sr_no+";";
        db.execSQL(insertQuery);
    }

    public void updateProduct(int sr_no,int Qty){
        SQLiteDatabase db = this.getWritableDatabase();

        String insertQuery="UPDATE "+Table_Name+ " SET "+T2_Field_3+"="+Qty+" WHERE "+Field_1+"="+sr_no+";";
        db.execSQL(insertQuery);
    }

    public void deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery="delete from "+Table_Name_child;
        db.execSQL(sqlQuery);

    }
    public void removeItemFormCart(int sr_no){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery="delete from "+Table_Name_child+" WHERE "+T2_Field_1+"="+sr_no;
        db.execSQL(sqlQuery);

    }

    public List<Product> fetchData(){
        String selectQuery = "SELECT  * FROM " + Table_Name;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);

        List<Product> productList=new ArrayList<>();
        while (cursor.moveToNext()){
            Product product=new Product();
            product.setSr_no(Integer.parseInt(cursor.getString(0)));
            product.setProduct_name(cursor.getString(1));
            product.setDescription(cursor.getString(2));
            product.setQty(Integer.valueOf(cursor.getString(3)));
            product.setPrice(Integer.valueOf(cursor.getString(4)));
            product.setImage(cursor.getString(5));
            productList.add(product);
         //   cursor.getString(cursor.getString(3));
        }
        return productList;

    }

    public List<Product> fetchCartData(){
        String selectQuery = "SELECT  * FROM " + Table_Name_child;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);

        List<Product> productList=new ArrayList<>();
            while(cursor.moveToNext()) {

                Product product = new Product();

                product.setSr_no(cursor.getInt(0));
                product.setProduct_name(cursor.getString(1));
                product.setQty(Integer.valueOf(cursor.getInt(2)));
                product.setPrice(Double.valueOf(cursor.getDouble(3)));

                productList.add(product);
                //   cursor.getString(cursor.getString(3));
            }
        return productList;

    }
}
