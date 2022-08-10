package com.example.vlandscaper.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

import com.bumptech.glide.Glide;
import com.example.vlandscaper.R;
import com.example.vlandscaper.activities.CartActivity;
import com.example.vlandscaper.activities.DetailItem;
import com.example.vlandscaper.utilClasses.Buy;
import com.example.vlandscaper.utilClasses.CartData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuyRecViewAdapter extends RecyclerView.Adapter<BuyRecViewAdapter.MyViewHolder> {

    /*
    Identifiers declaration
     */
    static int total = 0;
    private String name;
    private DatabaseReference userNameReference;
    Context context;
    ArrayList<Buy> list;

    /*
    Constructors
     */
    public BuyRecViewAdapter() {
    }


    public BuyRecViewAdapter(Context context, ArrayList<Buy> list) {
        this.context = context;
        this.list = list;
    }

    /*
    OnCreateViewHolder class for layout setting
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_buy, parent, false);
        return new BuyRecViewAdapter.MyViewHolder(view);
    }

    /*
    onBindViewHolder for binding data to views
     */
    @Override
    public void onBindViewHolder(@NonNull BuyRecViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Buy user = list.get(position);
        holder.titleplantAccessories.setText(user.getTitlePlantAccessories());
        holder.quantityT.setText(user.getQuantity()+"");
        holder.price.setText(user.getPrice()+"");
        Glide.with(holder.plantAccessoriesImg.getContext()).load(user.getImgURL()).into(holder.plantAccessoriesImg);

        /*
        Showing detail of Items when user click on item
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Details of Item", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailItem.class);
                intent.putExtra("detail", list.get(position));
                context.startActivity(intent);
            }
        });
    }

    /*
    Returning the size of arraylist
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    /*
    ViewHolder class for view declaration
     */

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleHome;
        TextView titleplantAccessories, price, quantity, quantityT;
        Button cart;
        EditText q;
        ImageView plantAccessoriesImg;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            /*
            Getting views by their ids
             */
            titleHome = itemView.findViewById(R.id.txttitlehome);
            titleplantAccessories = itemView.findViewById(R.id.txtTitlePlantAccesories);
            price = itemView.findViewById(R.id.txtPrice);
            cart = itemView.findViewById(R.id.btnAddToCart);
            plantAccessoriesImg = itemView.findViewById(R.id.imgPlantAccessories);
            quantity = itemView.findViewById(R.id.txtQuantityTitle);
            quantityT = itemView.findViewById(R.id.totalQuantity);
            q = itemView.findViewById(R.id.edtQuantity);

            String userId = firebaseAuth.getCurrentUser().getUid();

            /*
            Getting username of application to store data by his name
             */
            userNameReference = FirebaseDatabase.getInstance().getReference("users").child(userId).child("userName");
            userNameReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists())
                    {
                        name = snapshot.getValue().toString();
                    }
                    else{
                        Toast.makeText(context, "Something went wrong!!! Please check your connection", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            cart.setOnClickListener(this::onClick);
        }

        /*
        Function to add data to cartView
         */

        @Override
        public void onClick(View view) {
            Buy user = list.get(getAdapterPosition());
            String quan = q.getText().toString();
            int totalQ = user.getQuantity();
            if(quan.isEmpty())
            {
                Toast.makeText(context, "Please set quantity", Toast.LENGTH_SHORT).show();
                q.setFocusable(true);
            }
            else if(Integer.parseInt(quan) <= 0 || Integer.parseInt(quan) >= totalQ)
            {
                Toast.makeText(context, "Numbers of items you want is not available", Toast.LENGTH_SHORT).show();
            }
            else
            {
                    String itemTitle;
                    int itemPrice, itemQuantity;
                    itemTitle = user.getTitlePlantAccessories();
                    itemPrice = user.getPrice();
                    itemQuantity = Integer.parseInt(quan);
                    CartData cartData = new CartData();
                    CartData cartData1 = new CartData( itemTitle, itemPrice, cartData.totalPrice(itemPrice, itemQuantity), itemQuantity);

                    /*
                    Adding data to the cartItem in firebase database
                     */

                    database.child("cartItem").child(name).push().setValue(cartData1)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        q.setText("");
                                        Toast.makeText(context, user.getTitlePlantAccessories() + " Added To Cart Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, "Please wait ", Toast.LENGTH_SHORT).show();
                                }
                            });

            }
        }
    }
}
