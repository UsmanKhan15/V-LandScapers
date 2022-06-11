package com.example.vlandscaper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vlandscaper.R;
import com.example.vlandscaper.adapters.CartRecViewAdapter;
import com.example.vlandscaper.ui.buy.DashboardFragment;
import com.example.vlandscaper.utilClasses.CartData;
import com.example.vlandscaper.utilClasses.OrderPlace;
import com.example.vlandscaper.utilClasses.Quantity;
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

public class CartActivity extends AppCompatActivity {

    //Declaration
    private RecyclerView recyclerView;
    private CartRecViewAdapter adapter;
    public ArrayList<CartData> listCart;
    private DatabaseReference databaseReference, reference, reference1, reference2;

    //Declaration to set all fields to 0;
    private static int id = 0;
    private static int total = 0;
    private static int quantity = 0;
    private static String itemsName = "";
    private String name;
    private int quan;
    private TextView totalAmount, totalItems;
    private Button placeOrder, btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Changing Title of Action Bar
        getSupportActionBar().setTitle("Cart");

        //Initializing Elements
        recyclerView = findViewById(R.id.cartRecView);
        totalAmount = findViewById(R.id.txtTotalAmount);
        totalItems = findViewById(R.id.txtTotalItems);
        placeOrder = findViewById(R.id.btnPlaceOrder);
        btnDelete = findViewById(R.id.btnDelete);

        //Initializing Firebase
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        //Getting database Reference
        reference2 = FirebaseDatabase.getInstance().getReference("quantity");
        databaseReference = FirebaseDatabase.getInstance().getReference("cartItem").child(firebaseAuth.getCurrentUser().getUid());
        listCart = new ArrayList<>();

        //Setting Layout for Out Put
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    CartData user = dataSnapshot.getValue(CartData.class);
                    listCart.add(user);
                    total = total + listCart.get(id).getTotalPrice();
                    quantity = quantity + listCart.get(id).getQuantity();
                    itemsName = itemsName + listCart.get(id).getTitlePlantA() + listCart.get(id).getQuantity();
                    Quantity quantity = new Quantity(listCart.get(id).getTitlePlantA(), listCart.get(id).getQuantity());
                    reference2.child(listCart.get(id).getTitlePlantA()).setValue(quantity);
                    id++;
                }
                adapter.notifyDataSetChanged();
                if(total == 0){
                    totalAmount.setText("0");
                    totalItems.setText("0");
                }
                else
                {
                    totalItems.setText(quantity + "");
                    totalAmount.setText(total + "");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter = new CartRecViewAdapter(this,listCart);
        recyclerView.setAdapter(adapter);

        /*
        Deleting whole data from cart
         */
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference1 = FirebaseDatabase.getInstance().getReference();
                reference1.child("cartItem").child(firebaseAuth.getCurrentUser().getUid()).removeValue().
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                totalAmount.setText("0");
                                totalItems.setText("0");
                                total = 0;
                                Toast.makeText(CartActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
            }
        });

        //Placing order
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = firebaseAuth.getCurrentUser().getEmail().toString();
                if(TextUtils.isEmpty(totalAmount.getText().toString()))
                {
                    Toast.makeText(CartActivity.this, "Please Add Some Items To Cart", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(totalItems.getText().toString()) || totalItems.getText().toString().equals("0")){
                    Toast.makeText(CartActivity.this, "Please Add Items to Cart to Place Order", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try {
                        /*
                    Placing Order And Deleting Record From Cart View
                     */
                        reference = FirebaseDatabase.getInstance().getReference();
                        OrderPlace data1 = new OrderPlace(totalAmount.getText().toString(), totalItems.getText().toString(), itemsName, userEmail);
                        reference.child("orders").child(itemsName).setValue(data1);
                        Toast.makeText(CartActivity.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
                        reference1 = FirebaseDatabase.getInstance().getReference();
                        reference1.child("cartItem").child(firebaseAuth.getCurrentUser().getUid()).removeValue().
                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        totalAmount.setText("0");
                                        totalItems.setText("0");
                                        total = 0;
                                        Toast.makeText(CartActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(CartActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(CartActivity.this, "Something went wrong!!!Please Wait ", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } finally {
                        Toast.makeText(CartActivity.this, "Something went wrong!!! Please Wait a Moment", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}