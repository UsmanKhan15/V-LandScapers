package com.example.vlandscaper.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vlandscaper.R;
import com.example.vlandscaper.activities.DetailDesignActivity;
import com.example.vlandscaper.utilClasses.Design;

import java.util.ArrayList;

public class DesignRecViewAdapter extends RecyclerView.Adapter<DesignRecViewAdapter.MyViewHolder> {

    private Context context;
    ArrayList<Design> listDesign;

    public DesignRecViewAdapter(Context context, ArrayList<Design> listDesign) {
        this.context = context;
        this.listDesign = listDesign;
    }

    @NonNull
    @Override
    public DesignRecViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_design_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DesignRecViewAdapter.MyViewHolder holder, int position) {
        Design design = listDesign.get(holder.getAdapterPosition());
        holder.designTitle.setText(design.getTitleDesign());
        Glide.with(holder.imageViewDesign.getContext()).load(design.getImgUrlDesign()).into(holder.imageViewDesign);

        /*
        Showing detail of Items when user click on item
         */
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "Design" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context, DetailDesignActivity.class);
//                intent.putExtra("detailsDesign", listDesign.get(holder.getAdapterPosition()));
//                context.startActivity(intent);
//            }
//        });
    }
    @Override
    public int getItemCount() {
        return listDesign.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView designTitle;
        private ImageView imageViewDesign;
        private CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            designTitle = itemView.findViewById(R.id.txtTitleDesign);
            imageViewDesign = itemView.findViewById(R.id.imgDesign);
            cardView = itemView.findViewById(R.id.parentHomeDesign);
        }
    }
}

