package com.foobar.gireesam.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.foobar.gireesam.R;
import com.foobar.gireesam.adapter.OrderAdapter;
import com.foobar.gireesam.utils.Utils;

import java.util.Objects;

public class OrderActivity extends BaseActivity {

    @Override
    protected int containerId() {
        return 0;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_order;
    }

    private Button btCode;
    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Utils.changeStatusBarColor(getResources().getColor(R.color.white), this);
        RecyclerView rvList = findViewById(R.id.rvList);
        OrderAdapter orderAdapter = new OrderAdapter(this);
        rvList.setAdapter(orderAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        btCode = findViewById(R.id.btCode);

        if (getIntent().hasExtra("type")) {
            type = getIntent().getStringExtra("type");
        }

        if (type.equals("Order")) {
            btCode.setText(getString(R.string.show_code));
        } else if (type.equals("Close")) {
            btCode.setText(getString(R.string.close_tab));
        }
        btCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btCode.getText().toString().equals(getString(R.string.close_tab))) {
                    startActivity(new Intent(OrderActivity.this, BillDetailsActivity.class));
                } else {
                    showOrderDialog();
                }

            }
        });
    }

    private void showOrderDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(OrderActivity.this).create();
        LayoutInflater layoutInflater = getLayoutInflater();
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.show_code_dialog, null);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setView(view);
        dialog.setCancelable(false);
        Button btContinue = view.findViewById(R.id.btContinue);
        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btCode.setText(getString(R.string.close_tab));
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
