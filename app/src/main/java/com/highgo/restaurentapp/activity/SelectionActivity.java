package com.highgo.restaurentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.highgo.restaurentapp.R;
import com.highgo.restaurentapp.utils.Utils;

import io.realm.internal.Util;

public class SelectionActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        Utils.changeStatusBarColor(getResources().getColor(R.color.white),this);

        Button btBar = findViewById(R.id.btBar);
        btBar.setOnClickListener(this);

        Button btRest = findViewById(R.id.btRest);
        btRest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(SelectionActivity.this,LoginActivity.class));
        /*switch (v.getId()) {
            case R.id.btBar:
                startActivity(new Intent(SelectionActivity.this,LoginActivity.class));
                break;
            case R.id.btRest:
                startActivity(new Intent(SelectionActivity.this,LoginActivity.class));
                break;
        }*/
    }
}
