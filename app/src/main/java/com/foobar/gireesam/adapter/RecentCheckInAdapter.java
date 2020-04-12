package com.foobar.gireesam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.foobar.gireesam.R;

public class RecentCheckInAdapter extends RecyclerView.Adapter<RecentCheckInAdapter.ViewHolder> {

    private Context mContext;

    public RecentCheckInAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent_check_in, parent, false);
        return new ViewHolder(menuLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     /*   holder.menuTV.setText(names[position]);
        holder.iconIMG.setImageResource(icons[position]);
        if (position == 0) {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.yellow));
        } else {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
        }*/
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView menuTV;
        ImageView iconIMG;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
