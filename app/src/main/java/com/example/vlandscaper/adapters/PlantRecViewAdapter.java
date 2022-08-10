package com.example.vlandscaper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vlandscaper.R;
import com.example.vlandscaper.activities.Common;
import com.example.vlandscaper.activities.VirtualLandscapingActivity;
import com.example.vlandscaper.utilClasses.LandScape;

import java.util.ArrayList;

public class PlantRecViewAdapter extends RecyclerView.Adapter<PlantRecViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<LandScape> listLandscape;

    public PlantRecViewAdapter() {
    }

    public PlantRecViewAdapter(Context context, ArrayList<LandScape> listLandscape) {
        this.context = context;
        this.listLandscape = listLandscape;
    }

    @NonNull
    @Override
    public PlantRecViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_item_activity, parent, false);
        return new PlantRecViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantRecViewAdapter.MyViewHolder holder, int position) {
        LandScape landScape = listLandscape.get(holder.getAdapterPosition());
        holder.plantName.setText(landScape.getName());
        Glide.with(holder.imageView.getContext()).load(landScape.getImgUrl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common common = new Common();
                common.model = landScape.getModelUrl();
                Toast.makeText(context, "Model Loading!!! Internet is slow", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listLandscape.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView plantName;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            plantName = itemView.findViewById(R.id.plantName);
            imageView = itemView.findViewById(R.id.plantView);
        }
    }
}
