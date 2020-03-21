package com.highgo.restaurentapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.highgo.restaurentapp.R;
import com.highgo.restaurentapp.fragment.RecentCheckInFragment;
import com.highgo.restaurentapp.utils.Utils;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    // Context
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Utils.changeStatusBarColor(getResources().getColor(R.color.white),this);
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
        startActivity(new Intent(mContext, HomeActivity.class));
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
