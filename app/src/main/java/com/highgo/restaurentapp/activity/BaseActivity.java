package com.highgo.restaurentapp.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
    }

    protected abstract int containerId();

    protected abstract int getLayoutID();

    public void replaceFragment(Class fragmentClass, String title, Bundle bundle) {

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentClass.getSimpleName());
        Log.e("current_frag", "" + title);
        boolean isPopBackStack = getSupportFragmentManager().popBackStackImmediate(fragmentClass.getSimpleName(), 0);
        if (!isPopBackStack) {
            if (fragment == null) {
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (fragment != null) {
                if (bundle != null) {
                    fragment.setArguments(bundle);
                }
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(fragmentClass.getSimpleName());
                fragmentTransaction.replace(containerId(), fragment, fragmentClass.getSimpleName());
                getSupportFragmentManager().executePendingTransactions();
                fragmentTransaction.commit();
            }
        }
    }
}
