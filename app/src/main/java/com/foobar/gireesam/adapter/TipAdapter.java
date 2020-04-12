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

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {

    private Context mContext;

    public TipAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TipAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tip, parent, false);
        return new TipAdapter.ViewHolder(menuLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull TipAdapter.ViewHolder holder, int position) {

        if (position == 0) {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.yellow));
        } else {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.search_boarder_color));
        }
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
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}

