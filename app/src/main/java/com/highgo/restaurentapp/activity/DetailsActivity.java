package com.highgo.restaurentapp.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.highgo.restaurentapp.R;
import com.highgo.restaurentapp.utils.Utils;

import java.util.Objects;

public class DetailsActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvQty, tv_total;
    private LinearLayout ll_cart_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Utils.changeStatusBarColor(getResources().getColor(R.color.white), this);

        CardView cardAdd = findViewById(R.id.cardAdd);
        cardAdd.setOnClickListener(this);

        TextView tvView = findViewById(R.id.tvView);
        tvView.setOnClickListener(this);

        TextView tvMore = findViewById(R.id.tvMore);
        tvMore.setOnClickListener(this);

        ImageView ivMinus = findViewById(R.id.ivMinus);
        ivMinus.setOnClickListener(this);

        ImageView ivPlus = findViewById(R.id.ivPlus);
        ivPlus.setOnClickListener(this);

        ll_cart_view = findViewById(R.id.ll_cart_view);
        tvQty = findViewById(R.id.tvQty);
        tv_total = findViewById(R.id.tv_total);


    }

    @Override
    protected int containerId() {
        return 0;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_details;
    }

    private void showOrderDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(DetailsActivity.this).create();
        LayoutInflater layoutInflater = getLayoutInflater();
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.order_confirm_dialog, null);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setView(view);
        dialog.setCancelable(false);
        Button btContinue = view.findViewById(R.id.btContinue);
        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(DetailsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        TextView go_to_order = view.findViewById(R.id.go_to_order);
        go_to_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivMinus:
                qtyDecrement();
                break;
            case R.id.ivPlus:
                qtyIncrement();
                break;
            case R.id.cardAdd:
                ll_cart_view.setVisibility(View.VISIBLE);
                tv_total.setText(tvQty.getText().toString() + " ITEM");
                break;
            case R.id.tvView:
                startActivity(new Intent(DetailsActivity.this, EditOrderActivity.class));
                break;
            case R.id.tvMore:
                finish();
                break;
        }

    }

    private void qtyDecrement() {
        if (!tvQty.getText().toString().equalsIgnoreCase("1")) {
            int qty = Integer.parseInt(tvQty.getText().toString()) - 1;
            tvQty.setText("" + qty);
        }
    }

    private void qtyIncrement() {
        int qty = Integer.parseInt(tvQty.getText().toString()) + 1;
        tvQty.setText("" + qty);
    }
}
