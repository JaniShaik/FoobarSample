package com.highgo.restaurentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.highgo.restaurentapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // Context
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        RelativeLayout rlBar = findViewById(R.id.rlBar);
        rlBar.setOnClickListener(this);

        RelativeLayout rlRest = findViewById(R.id.rlRest);
        rlRest.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(mContext,NfcActivity.class));
       /* switch (v.getId()) {
            case R.id.rlBar:
                startActivity(new Intent(mContext,MainActivity.class));
                break;
            case R.id.rlRest:
                startActivity(new Intent(mContext,MainActivity.class));
                break;
        }*/
    }
}
