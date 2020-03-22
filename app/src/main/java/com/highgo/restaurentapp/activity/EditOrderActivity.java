package com.highgo.restaurentapp.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.highgo.restaurentapp.R;
import com.highgo.restaurentapp.adapter.EditOrderAdapter;
import com.highgo.restaurentapp.fragment.DashBoardFragment;
import com.highgo.restaurentapp.utils.Utils;

import java.util.Objects;

public class EditOrderActivity extends BaseActivity {

    @Override
    protected int containerId() {
        return 0;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_edit_order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);
        Utils.changeStatusBarColor(getResources().getColor(R.color.white),this);
        RecyclerView rvList=findViewById(R.id.rvList);
        EditOrderAdapter orderAdapter=new EditOrderAdapter(this);
        rvList.setAdapter(orderAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        CardView cardAdd=findViewById(R.id.cardAdd);
        cardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrderDialog();
            }
        });
    }

    private void showOrderDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(EditOrderActivity.this).create();
        LayoutInflater layoutInflater = getLayoutInflater();
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.order_confirm_dialog, null);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setView(view);
        dialog.setCancelable(false);
        Button btContinue = view.findViewById(R.id.btContinue);
        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditOrderActivity.this,HomeActivity.class));
                dialog.dismiss();
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
