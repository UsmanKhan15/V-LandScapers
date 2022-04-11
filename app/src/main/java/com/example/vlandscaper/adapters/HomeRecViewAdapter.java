package com.example.vlandscaper.adapters;

import android.content.Context;
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
import com.example.vlandscaper.utilClasses.Home;

import java.util.ArrayList;


public class HomeRecViewAdapter extends RecyclerView.Adapter<HomeRecViewAdapter.MyViewHolder>{

    Context context;
    ArrayList<Home> list;

    public HomeRecViewAdapter() {
    }

    public HomeRecViewAdapter(Context context, ArrayList<Home> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 0)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity, parent, false);
            return new MyViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_item_activity, parent, false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 1;
        return position % 1;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecViewAdapter.MyViewHolder holder, int position) {
        if(position == 0)
        {
            Home user = list.get(position);
            holder.txtTitle.setText(user.getTitle());
            holder.txtDescription.setText(user.getDescription());
        }
        else
        {
            Home user = list.get(position);
            holder.title.setText(user.getTitle());
            holder.description.setText(user.getDescription());
            Glide.with(holder.imgURL.getContext()).load(user.getImgURL()).into(holder.imgURL);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(user.getTitle().equals("Our Products"))
                    {
                        Toast.makeText(context, "Our Products", Toast.LENGTH_SHORT).show();
                    }
                    else if(user.getTitle().equals("Our Services"))
                    {
                        Toast.makeText(context, "Our Services", Toast.LENGTH_SHORT).show();
                    }
                    else if (user.getTitle().equals("Make Green"))
                    {
                        Toast.makeText(context, "Make Green", Toast.LENGTH_SHORT).show();
                    }
                    else if(user.getTitle().equals("About Us"))
                    {
                        Toast.makeText(context, "About Us", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, description;
        ImageView imgURL;
        TextView txtTitle, txtDescription;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txtTitle);
            description = itemView.findViewById(R.id.txtDesHome);
            imgURL = itemView.findViewById(R.id.imgPlantAccessories);

            txtTitle = itemView.findViewById(R.id.txttitlehome);
            txtDescription = itemView.findViewById(R.id.textViewDescription);
            cardView = itemView.findViewById(R.id.parentHome);
        }
    }
}