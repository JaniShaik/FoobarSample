package com.foobar.gireesam.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.foobar.gireesam.R;
import com.foobar.gireesam.adapter.OrderAdapter;
import com.foobar.gireesam.adapter.TipAdapter;
import com.foobar.gireesam.utils.Utils;

import java.util.ArrayList;
import java.util.Objects;

public class BillDetailsActivity extends AppCompatActivity {

    private final static int TIME_OUT = 3000;
    private AlertDialog dialog;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);
        Utils.changeStatusBarColor(getResources().getColor(R.color.white), this);

        RecyclerView rvList = findViewById(R.id.rvList);
        OrderAdapter orderAdapter = new OrderAdapter(this);
        rvList.setAdapter(orderAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        RecyclerView rvTipList = findViewById(R.id.rvTipList);

        ArrayList<String> list=new ArrayList<String>();
        list.add("+ 10 %");
        list.add("+ 15 %");
        list.add("+ 20 %");
        list.add("+ 25 %");
        list.add("+ 30 %");

        TipAdapter tipAdapter = new TipAdapter(this,list);
        rvTipList.setAdapter(tipAdapter);
        rvTipList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        CardView cardPay = findViewById(R.id.cardPay);
        cardPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrderDialog();
                startTimer();
            }
        });

    }

    private void startTimer() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                /*if (dialog != null) {
                    dialog.dismiss();
                }*/
                Intent intent = new Intent(BillDetailsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        };
        handler.postDelayed(runnable, TIME_OUT);
    }

    private void showOrderDialog() {
        dialog = new AlertDialog.Builder(BillDetailsActivity.this).create();
        LayoutInflater layoutInflater = getLayoutInflater();
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.payment_success_dialog, null);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setView(view);
        dialog.setCancelable(false);
        Button btContinue = view.findViewById(R.id.btContinue);
        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (handler != null) {
                    handler.removeCallbacks(runnable);
                }
                Intent intent = new Intent(BillDetailsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }
}
