package com.foobar.gireesam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.foobar.gireesam.R;

/**
 * Created by JANI SHAIK on 15/04/2020
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context mContext;
    private int[] icons;
    private int[] names;

    public NotificationAdapter(Context mContext, int[] icons, int[] names) {
        this.mContext = mContext;
        this.icons = icons;
        this.names = names;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(menuLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.icon.setImageDrawable(ContextCompat.getDrawable(mContext, icons[position]));
        holder.text.setText(names[position]);
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        View container;
        ImageView icon;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView;
            container.setClickable(true);
            container.setOnClickListener(this);
            icon = itemView.findViewById(R.id.icon);
            text = itemView.findViewById(R.id.text);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
