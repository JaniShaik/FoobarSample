package com.foobar.gireesam.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foobar.gireesam.R;
import com.foobar.gireesam.adapter.NotificationAdapter;

public class AlertsFragment  extends Fragment {

    private View rootView;
    private Context mContext;
    // RecyclerView
    private RecyclerView notificationRV;
    private NotificationAdapter notificationAdapter;
    private int[] icons = {R.drawable.cocktail, R.drawable.high_priority, R.drawable.cocktail, R.drawable.high_priority};
    private int[] names = {R.string.notification_one, R.string.notification_two,
            R.string.notification_one, R.string.notification_two};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_alerts, container, false);
        mContext = getActivity();
        initViews(rootView);
        return rootView;
    }

    private void initViews(View view) {
        notificationRV = view.findViewById(R.id.notificationRV);
        notificationAdapter = new NotificationAdapter(mContext, icons, names);
        notificationRV.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        notificationRV.setAdapter(notificationAdapter);
        notificationAdapter.notifyDataSetChanged();
    }

}