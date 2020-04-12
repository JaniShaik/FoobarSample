package com.foobar.gireesam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.foobar.gireesam.R;

public class SmallFoodMenuAdapter extends RecyclerView.Adapter<SmallFoodMenuAdapter.ViewHolder> {

    private Context mContext;
    private String[] names;
    private int[] icons;

    public SmallFoodMenuAdapter(Context mContext, String[] names, int[] icons) {
        this.mContext = mContext;
        this.names = names;
        this.icons = icons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.small_menu_item, parent, false);
        return new ViewHolder(menuLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.menuTV.setText(names[position]);
        holder.iconIMG.setImageResource(icons[position]);
        if (position == 0) {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.yellow));
        } else {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.search_boarder_color));
        }
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView menuTV;
        ImageView iconIMG;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuTV = itemView.findViewById(R.id.menuTV);
            iconIMG = itemView.findViewById(R.id.iconIMG);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
