package com.example.vlandscaper.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vlandscaper.R;
import com.example.vlandscaper.activities.CartActivity;
import com.example.vlandscaper.utilClasses.Buy;
import com.example.vlandscaper.utilClasses.CartData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CartRecViewAdapter extends RecyclerView.Adapter<CartRecViewAdapter.MyViewHolder> {

    private Context context;
    ArrayList<CartData> listCart;

    public CartRecViewAdapter(Context context, ArrayList<CartData> listCart) {
        this.context = context;
        this.listCart = listCart;
    }

    @NonNull
    @Override
    public CartRecViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_activity, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartRecViewAdapter.MyViewHolder holder, int position) {
        CartData cartData = listCart.get(position);
        holder.itemTitle.setText(cartData.getTitlePlantA() + "");
        holder.itemPrice.setText(cartData.getPrice() + "");
        holder.itemTotalPrice.setText(cartData.getTotalPrice() + "");
        holder.itemTotal.setText(cartData.getQuantity() + "");
    }
    @Override
    public int getItemCount() {
        return listCart.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView itemTitle, itemPrice, itemTotal, itemTotalPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.txtTitleItem);
            itemPrice = itemView.findViewById(R.id.txtItemPrice);
            itemTotalPrice = itemView.findViewById(R.id.txtTotalItemPrice);
            itemTotal = itemView.findViewById(R.id.txtQuant);
        }
    }
}
