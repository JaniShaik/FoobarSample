package com.foobar.gireesam.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.foobar.gireesam.R;
import com.foobar.gireesam.adapter.MainMenuAdapter;
import com.foobar.gireesam.adapter.SmallFoodMenuAdapter;
import com.foobar.gireesam.utils.Utils;

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
    private int[] mainMenuIcons = {R.drawable.chicken_salad_1, R.drawable.chicken_salad_2,R.drawable.chicken_salad_1, R.drawable.chicken_salad_2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_food_menu);
        Utils.changeStatusBarColor(getResources().getColor(R.color.white),this);
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
