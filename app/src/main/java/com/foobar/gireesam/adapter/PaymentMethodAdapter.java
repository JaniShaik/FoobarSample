package com.foobar.gireesam.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.foobar.gireesam.R;
import com.foobar.gireesam.model.PaymentMethod;

import java.util.ArrayList;

/**
 * Created by JANI SHAIK on 12/04/2020
 */

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<PaymentMethod> paymentMethodArrayList;
    private int selected_position = 0;

    public PaymentMethodAdapter(Context mContext, ArrayList<PaymentMethod> paymentMethodArrayList) {
        this.mContext = mContext;
        this.paymentMethodArrayList = paymentMethodArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_method, parent, false);
        return new ViewHolder(menuLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PaymentMethod paymentMethod = paymentMethodArrayList.get(position);
        holder.paymentIMG.setImageDrawable(ContextCompat.getDrawable(mContext, paymentMethod.getIcon()));
        holder.tvNumber.setText(paymentMethod.getNumber());
        //
        if (paymentMethod.getSelected()) {
            holder.llMainLayout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_selected_payment_method));
            holder.checkCircle.setVisibility(View.VISIBLE);
        } else {
            holder.llMainLayout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_payment_method));
            holder.checkCircle.setVisibility(View.GONE);
        }

        holder.container.setTag(position);
    }

    @Override
    public int getItemCount() {
        return paymentMethodArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        View container;
        LinearLayout llMainLayout;
        TextView tvNumber;
        ImageView paymentIMG, checkCircle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView;
            container.setOnClickListener(this);
            container.setClickable(true);
            llMainLayout = itemView.findViewById(R.id.llMainLayout);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            paymentIMG = itemView.findViewById(R.id.paymentIMG);
            checkCircle = itemView.findViewById(R.id.checkCircle);
        }

        @Override
        public void onClick(View v) {

            int pos = (int) v.getTag();

            for (int i = 0; i < paymentMethodArrayList.size(); i++) {
                PaymentMethod product = paymentMethodArrayList.get(i);
                if (i != pos) {
                    product.setSelected(false);
                } else {
                    product.setSelected(true);
                }
            }
            notifyDataSetChanged();
        }
    }
}
