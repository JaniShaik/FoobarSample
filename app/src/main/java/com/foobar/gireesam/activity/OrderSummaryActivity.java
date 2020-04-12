package com.foobar.gireesam.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.foobar.gireesam.R;
import com.foobar.gireesam.adapter.OrderAdapter;
import com.foobar.gireesam.utils.Utils;

public class OrderSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        Utils.changeStatusBarColor(getResources().getColor(R.color.white), this);
        RecyclerView rvList = findViewById(R.id.rvList);
        OrderAdapter orderAdapter = new OrderAdapter(this);
        rvList.setAdapter(orderAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }
}
