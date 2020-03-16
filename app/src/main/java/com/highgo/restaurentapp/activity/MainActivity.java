package com.highgo.restaurentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.highgo.restaurentapp.R;

public class MainActivity extends AppCompatActivity {

    // Context
    private Context mContext = this;

    // Relative Layout
    private RelativeLayout menuLayout, ordersLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        //
        menuLayout = findViewById(R.id.menuLayout);
        ordersLayout = findViewById(R.id.ordersLayout);
        //
        menuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FoodMenuActivity.class);
                startActivity(intent);
            }
        });
        //
        ordersLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                startActivity(intent);
            }
        });

    }
}
