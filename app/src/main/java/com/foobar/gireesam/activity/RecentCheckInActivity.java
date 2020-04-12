package com.foobar.gireesam.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foobar.gireesam.R;
import com.foobar.gireesam.adapter.RecentCheckInAdapter;
import com.foobar.gireesam.fragment.NfcFragment;

public class RecentCheckInActivity extends BaseActivity {
    private Context mContext;

    @Override
    protected int containerId() {
        return 0;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_recent_check_in;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        createAdapter();
    }

    private void createAdapter() {
        RecentCheckInAdapter recentCheckInAdapter = new RecentCheckInAdapter(mContext);
        RecyclerView rvList=findViewById(R.id.rvList);
        LinearLayout llOpen=findViewById(R.id.llOpen);
        rvList.setAdapter(recentCheckInAdapter);
        rvList.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.HORIZONTAL, false));

        llOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NfcActivity.class);
                startActivity(intent);
               // replaceFragment(NfcFragment.class, "NFC", null);
            }
        });
    }
}
