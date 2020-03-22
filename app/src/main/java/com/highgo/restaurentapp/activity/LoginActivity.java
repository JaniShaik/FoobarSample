package com.highgo.restaurentapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.highgo.restaurentapp.R;
import com.highgo.restaurentapp.utils.Utils;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    // Context
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Utils.changeStatusBarColor(getResources().getColor(R.color.white), this);
        RelativeLayout rlBar = findViewById(R.id.rlBar);
        rlBar.setOnClickListener(this);

        RelativeLayout rlRest = findViewById(R.id.rlRest);
        rlRest.setOnClickListener(this);

    }

    @Override
    protected int containerId() {
        return 0;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlBar:
                goToNext("Bar");
                break;
            case R.id.rlRest:
                goToNext("Restaurant");
                break;
        }
    }

    private void goToNext(String type) {
        Intent intent = new Intent(mContext, HomeActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
