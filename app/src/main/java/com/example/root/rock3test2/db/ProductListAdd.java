package com.example.root.rock3test2.db;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.example.root.rock3test2.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by root on 13/2/18.
 */

public class ProductListAdd {


    public void addProduct(SqliteHandler sqliteHandler, Context context) {


        sqliteHandler.addProducts("TV", "TCL 123 cm  L49D2900 Full HD LED TV ", 1350, 26990.00, "tv");
        sqliteHandler.addProducts("Camera", "Canon EOS 1300D 18MP Digital SLR", 152, 25098.00, "camera");
        sqliteHandler.addProducts("Smart Watch", "Rrimin Waterproof Color Display ", 58, 18876.00, "smartwatch");
        sqliteHandler.addProducts("Washing Machine", "IFB 7 kg Fully-Automatic Front Loading ", 20, 27990.00, "washingmachine");
        sqliteHandler.addProducts("Headphone", "JBL T250SI On-Ear ", 50, 675.00, "headphone");
        sqliteHandler.addProducts("Pen Drive", "Strontium Pollex 16GB ", 56, 349.00, "pendrive");
        sqliteHandler.addProducts("Hard Drive", "WD Elements 500GB  External ", 350, 2999.00, "harddisk");
        sqliteHandler.addProducts("Memory Card", "SanDisk 32GB Class 10 microSDXC ", 100, 728.00, "memorycard");
        sqliteHandler.addProducts("Tower Speaker", "Kewlkart Hi Fi Dj 500 W with one 4.25 woofer ", 250, 3499.00, "speaker");
        sqliteHandler.addProducts("AC", "LG 1.5 Ton 3 Star Dual Inverter ", 100, 36990.00, "ac");
        sqliteHandler.addProducts("Oven", "Prestige POTG 9 PC 800-Watt ", 800, 1999.00, "oven");
        sqliteHandler.addProducts("Mixer Grinder", "Butterfly Matchless 750-Watt ", 400, 4199.00, "grinder");
        sqliteHandler.addProducts("Induction", "Pigeon Cruise 1800-Watt ", 350, 1549.00, "induction");
        sqliteHandler.addProducts("Gas Stove", "Sunflame Crystal Stainless Steel 4 Burner ", 254, 4699.00, "stove");
        sqliteHandler.addProducts("Blender", "Nova Pro NHM-2109 150-Watt ", 652, 1099.00, "blender");
        sqliteHandler.addProducts("Water Purifier", "Kent Wonder 7-Litre RO", 150, 13500.00, "waterpurifier");
        sqliteHandler.addProducts("Laptop", "HP 15q-BU004TU 2017 15.6-inch", 800, 26999.00, "laptop");
        sqliteHandler.addProducts("Printer", "Canon Pixma MG2577s All-in-One InkJet", 100, 5400.00, "printer");
        sqliteHandler.addProducts("Mobile", "Lenovo K8 Note (Venom Black, 4GB) ", 500, 12999.00, "mobile");
        sqliteHandler.addProducts("Tablet", "Lenovo Yoga Tab 3 8", 652, 10999.00, "tablet");
    }
}
