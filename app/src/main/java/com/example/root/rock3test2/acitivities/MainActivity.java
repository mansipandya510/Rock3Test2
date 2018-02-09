package com.example.root.rock3test2.acitivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.root.rock3test2.R;

public class MainActivity extends AppCompatActivity {

    RecyclerView prodLst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prodLst=(RecyclerView)findViewById(R.id.prodLst);
    }
}
