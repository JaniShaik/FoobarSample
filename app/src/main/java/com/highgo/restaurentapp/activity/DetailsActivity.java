package com.highgo.restaurentapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.highgo.restaurentapp.R;
import com.highgo.restaurentapp.utils.Utils;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Utils.changeStatusBarColor(getResources().getColor(R.color.white), this);

        final LinearLayout ll_cart_view = findViewById(R.id.ll_cart_view);
        CardView cardAdd = findViewById(R.id.cardAdd);
        cardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_cart_view.setVisibility(View.VISIBLE);
            }
        });
    }
}
