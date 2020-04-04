package com.highgo.restaurentapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.highgo.restaurentapp.R;


public class NfcActivity extends BaseActivity {

    private Context mContext;

    @Override
    protected int containerId() {
        return 0;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_nfc;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        RelativeLayout rlQr = findViewById(R.id.rlQr);
        rlQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
