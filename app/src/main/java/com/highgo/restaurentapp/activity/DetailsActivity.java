package com.highgo.restaurentapp.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.highgo.restaurentapp.R;
import com.highgo.restaurentapp.utils.Utils;

import java.util.Objects;

public class DetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Utils.changeStatusBarColor(getResources().getColor(R.color.white), this);

        final LinearLayout ll_cart_view = findViewById(R.id.ll_cart_view);
        CardView cardAdd = findViewById(R.id.cardAdd);
        TextView tvView = findViewById(R.id.tvView);
        cardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_cart_view.setVisibility(View.VISIBLE);
            }
        });
        tvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // showOrderDialog();
                startActivity(new Intent(DetailsActivity.this,EditOrderActivity.class));
            }
        });
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
                //replaceFragment(DashBoardFragment.class,"DashBoard",null);
                dialog.dismiss();
                Intent intent=new Intent(DetailsActivity.this,HomeActivity.class);
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
}
