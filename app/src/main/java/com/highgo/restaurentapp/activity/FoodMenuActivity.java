package com.highgo.restaurentapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.highgo.restaurentapp.R;
import com.highgo.restaurentapp.adapter.MainMenuAdapter;
import com.highgo.restaurentapp.adapter.SmallFoodMenuAdapter;

public class FoodMenuActivity extends AppCompatActivity {

    // Context
    private Context mContext = this;
    // Small Menu RecyclerView
    private RecyclerView menuRV;
    private SmallFoodMenuAdapter smallFoodMenuAdapter;
    private LinearLayoutManager linearLayoutManager;
    // Main Menu RecyclerView
    private RecyclerView mainMenuRV;
    private MainMenuAdapter mainMenuAdapter;
    // Data
    private String[] menuNames = {"Pizza", "Salad", "Pizza", "Salad", "Pizza", "Salad"};
    private int[] menuIcons = {R.drawable.pizza, R.drawable.salad, R.drawable.pizza, R.drawable.salad, R.drawable.pizza, R.drawable.salad};
    private int[] mainMenuIcons = {R.drawable.chicken_salad_1, R.drawable.chicken_salad_2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_food_menu);
        // Small Menu
        menuRV = findViewById(R.id.menuRV);
        // Setting adapter
        smallFoodMenuAdapter = new SmallFoodMenuAdapter(mContext, menuNames, menuIcons);
        linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        menuRV.setLayoutManager(linearLayoutManager);
        menuRV.setAdapter(smallFoodMenuAdapter);
        smallFoodMenuAdapter.notifyDataSetChanged();
        // Main Menu
        mainMenuRV = findViewById(R.id.mainMenuRV);
        // Setting adapter
        mainMenuAdapter = new MainMenuAdapter(mContext, mainMenuIcons);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mainMenuRV.setLayoutManager(linearLayoutManager);
        mainMenuRV.setAdapter(mainMenuAdapter);
        mainMenuAdapter.notifyDataSetChanged();
    }
}
