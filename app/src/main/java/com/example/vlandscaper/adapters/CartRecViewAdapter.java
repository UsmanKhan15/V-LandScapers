package com.example.vlandscaper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vlandscaper.R;
import com.example.vlandscaper.utilClasses.Buy;

import java.util.ArrayList;

public class CartRecViewAdapter extends RecyclerView.Adapter<CartRecViewAdapter.MyViewHolder> {

    private Context context;
    ArrayList<Buy> list;

    public CartRecViewAdapter(Context context, ArrayList<Buy> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CartRecViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_activity, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartRecViewAdapter.MyViewHolder holder, int position) {
        Buy buy = list.get(position);
        holder.itemTitle.setText(buy.getTitlePlantAccessories());
        //holder.itemTotal.setText(buy.getPrice()+"");
        holder.itemPrice.setText(buy.getTotalPrice()+"");
        //holder.itemTotal.setText(buy.getQuantity()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView itemTitle, itemPrice, itemTotal, itemTotalPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.txtTitleItem);
            itemPrice = itemView.findViewById(R.id.txtItemPrice);
            itemTotalPrice = itemView.findViewById(R.id.txtTotalItemPrice);
            //itemTotal = itemPrice.findViewById(R.id.txtQuantity);
        }
    }
}
