package com.foobar.gireesam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.foobar.gireesam.R;
import com.foobar.gireesam.activity.FoodMenuActivity;
import com.foobar.gireesam.activity.OrderActivity;
import com.foobar.gireesam.databinding.FragmentDashBoardBinding;
import com.foobar.gireesam.viewmodel.DashBoardViewModel;

public class DashBoardFragment extends BaseFragment<FragmentDashBoardBinding, DashBoardViewModel> {

    @Override
    protected int fragmentId() {
        return R.layout.fragment_dash_board;
    }

    @Override
    protected Class<DashBoardViewModel> viewModelClass() {
        return DashBoardViewModel.class;
    }

    @Override
    protected Factory factory() {
        return new Factory() {
            @Override
            public DashBoardViewModel getClassInstance() {
                return new DashBoardViewModel();
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getBinding().menuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FoodMenuActivity.class);
                startActivity(intent);
            }
        });
        getBinding().ordersLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("type", "Order");
                startActivity(intent);
            }
        });
        getBinding().closeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("type", "Close");
                startActivity(intent);
            }
        });
    }
}
