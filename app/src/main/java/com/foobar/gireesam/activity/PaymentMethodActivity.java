package com.foobar.gireesam.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.foobar.gireesam.R;
import com.foobar.gireesam.adapter.PaymentMethodAdapter;
import com.foobar.gireesam.model.PaymentMethod;
import com.foobar.gireesam.utils.Utils;

import java.util.ArrayList;
import java.util.Objects;

public class PaymentMethodActivity extends AppCompatActivity {

    // Context
    private Context mContext = this;
    // RecyclerView
    private RecyclerView rvPaymentMethod;
    private PaymentMethodAdapter paymentMethodAdapter;
    private ArrayList<PaymentMethod> listPaymentMethods = new ArrayList<>();
    private String[] names = {"**** **** **** 5967", "wilson.casper@bernice.in", "**** **** **** 3461"};
    // Button
    private Button btPayment;

    private final static int TIME_OUT = 3000;
    private AlertDialog dialog;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        Utils.changeStatusBarColor(getResources().getColor(R.color.white), this);
        // Initialization
        initialization();
    }

    // Initialization
    private void initialization() {
        rvPaymentMethod = findViewById(R.id.rvPaymentMethod);
        // Payment Methods
        listPaymentMethods.add(new PaymentMethod("**** **** **** 5967", R.drawable.visa, false));
        listPaymentMethods.add(new PaymentMethod("wilson.casper@bernice.in", R.drawable.paypal, false));
        listPaymentMethods.add(new PaymentMethod("**** **** **** 3461", R.drawable.visa, false));
        // Payment Adapter
        paymentMethodAdapter = new PaymentMethodAdapter(mContext, listPaymentMethods);
        rvPaymentMethod.setAdapter(paymentMethodAdapter);
        rvPaymentMethod.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        btPayment = findViewById(R.id.btPayment);
        btPayment.setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(PaymentMethodActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        };
        handler.postDelayed(runnable, TIME_OUT);
    }

    private void showOrderDialog() {
        dialog = new AlertDialog.Builder(PaymentMethodActivity.this).create();
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
                Intent intent = new Intent(PaymentMethodActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }

}
