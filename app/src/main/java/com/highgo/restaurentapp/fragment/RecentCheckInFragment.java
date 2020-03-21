package com.highgo.restaurentapp.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.highgo.restaurentapp.R;
import com.highgo.restaurentapp.adapter.RecentCheckInAdapter;
import com.highgo.restaurentapp.databinding.FragmentRecentCheckInBinding;
import com.highgo.restaurentapp.viewmodel.RecentCheckInViewModel;

public class RecentCheckInFragment extends BaseFragment<FragmentRecentCheckInBinding, RecentCheckInViewModel> {

    @Override
    protected int fragmentId() {
        return R.layout.fragment_recent_check_in;
    }

    @Override
    protected Class<RecentCheckInViewModel> viewModelClass() {
        return RecentCheckInViewModel.class;
    }

    @Override
    protected Factory factory() {
        return new Factory() {
            @Override
            public RecentCheckInViewModel getClassInstance() {
                return new RecentCheckInViewModel();
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createAdapter();
    }


    private void createAdapter() {
        RecentCheckInAdapter recentCheckInAdapter = new RecentCheckInAdapter(getActivity());
        getBinding().rvList.setAdapter(recentCheckInAdapter);
        getBinding().rvList.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false));

        getBinding().llOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(NfcFragment.class, "NFC", null);
            }
        });
    }
}
