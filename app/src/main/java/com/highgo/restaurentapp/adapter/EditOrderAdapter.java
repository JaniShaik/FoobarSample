package com.highgo.restaurentapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.highgo.restaurentapp.R;

public class EditOrderAdapter extends RecyclerView.Adapter<EditOrderAdapter.ViewHolder> {

    private Context mContext;

    public EditOrderAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public EditOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new EditOrderAdapter.ViewHolder(menuLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull EditOrderAdapter.ViewHolder holder, int position) {
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
        return 5;
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

