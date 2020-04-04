package com.highgo.restaurentapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.highgo.restaurentapp.R;
import com.highgo.restaurentapp.fragment.AlertsFragment;
import com.highgo.restaurentapp.fragment.DashBoardFragment;
import com.highgo.restaurentapp.fragment.ProfileFragment;
import com.highgo.restaurentapp.fragment.RecentCheckInFragment;
import com.highgo.restaurentapp.utils.Utils;

public class HomeActivity extends BaseActivity {

    private boolean doubleBackToExitPressedOnce = false;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            item.setChecked(true);
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    replaceFragment(DashBoardFragment.class, "DashBoard", null);
                    return true;
                case R.id.navigation_notification:
                    replaceFragment(AlertsFragment.class, "Alerts", null);
                    return true;
                case R.id.navigation_person:
                    replaceFragment(ProfileFragment.class, "Profile", null);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Utils.changeStatusBarColor(getResources().getColor(R.color.white),this);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        replaceFragment(DashBoardFragment.class, "Dashboard", null);
    }

    @Override
    protected int containerId() {
        return R.id.frame_container;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getCurrentFragmentByTag(R.id.frame_container);
        if (fragment instanceof DashBoardFragment) {
            doublePressToFinish();
        } else {
            getSupportFragmentManager().popBackStack();
            changeBackStack();
        }
    }

    private void doublePressToFinish() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.exit_msg), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    private Fragment getCurrentFragmentByTag(int i) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return fragmentManager.findFragmentById(i);
    }

    private void changeBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment fragment1 = getCurrentFragmentByTag(R.id.frame_container);
                 if (fragment1 instanceof DashBoardFragment) {
                    navigation.getMenu().getItem(0).setChecked(true);
                } else if (fragment1 instanceof AlertsFragment) {
                    navigation.getMenu().getItem(1).setChecked(true);
                } else if (fragment1 instanceof ProfileFragment) {
                    navigation.getMenu().getItem(2).setChecked(true);
                }
            }
        });
    }
}
