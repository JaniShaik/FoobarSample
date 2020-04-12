package com.foobar.gireesam.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.foobar.gireesam.R;
import com.foobar.gireesam.databinding.ActivityNfcBinding;
import com.foobar.gireesam.viewmodel.NfcViewModel;

public class NfcFragment extends BaseFragment<ActivityNfcBinding, NfcViewModel> {

    @Override
    protected int fragmentId() {
        return R.layout.activity_nfc;
    }

    @Override
    protected Class<NfcViewModel> viewModelClass() {
        return NfcViewModel.class;
    }

    @Override
    protected Factory factory() {
        return new Factory() {
            @Override
            public NfcViewModel getClassInstance() {
                return new NfcViewModel();
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getBinding().rlQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(DashBoardFragment.class, "DashBoard", null);
            }
        });
    }
}
