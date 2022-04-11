package com.example.vlandscaper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vlandscaper.R;
import com.example.vlandscaper.utilClasses.Buy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BuyRecViewAdapter extends RecyclerView.Adapter<BuyRecViewAdapter.MyViewHolder> {

    String qunatity;
    Context context;
    ArrayList<Buy> list;

    public BuyRecViewAdapter() {
    }

    public BuyRecViewAdapter(ArrayList<Buy> list) {
        this.list = list;
    }

    public BuyRecViewAdapter(Context context, ArrayList<Buy> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_buy, parent, false);
        return new BuyRecViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyRecViewAdapter.MyViewHolder holder, int position) {
        Buy user = list.get(position);

        holder.titleplantAccessories.setText(user.getTitlePlantAccessories());
        holder.price.setText(user.getPrice()+"");
        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, user.getTitlePlantAccessories() + " Added To Cart Successfully", Toast.LENGTH_SHORT).show();
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                String itemTitle;
                int itemPrice, itemQuantity;
                itemTitle = user.getTitlePlantAccessories();
                itemPrice = user.getPrice();
                Buy buy1 = new Buy();
                //itemQuantity = qunatity;
                //itemQuantity = Integer.parseInt(String.valueOf(R.id.edtQuantityy));
                Buy buy = new Buy( itemTitle, itemPrice, 1, itemPrice);
                database.child("cartItem").child(buy1.getIdCart()).setValue(buy)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context, user.getTitlePlantAccessories() + " Added To Cart Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titleHome;
        TextView titleplantAccessories, price;
        Button cart;
        EditText edtQuantity;
        ImageView plantAccessoriesImg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleHome = itemView.findViewById(R.id.txttitlehome);
            titleplantAccessories = itemView.findViewById(R.id.txtTitlePlantAccesories);
            price = itemView.findViewById(R.id.txtPrice);
            cart = itemView.findViewById(R.id.btnAddToCart);
            plantAccessoriesImg = itemView.findViewById(R.id.imgPlantAccessories);
            //edtQuantity = itemView.findViewById(R.id.edtQuantityy);
            //qunatity = edtQuantity.getText().toString();
        }
    }
}
