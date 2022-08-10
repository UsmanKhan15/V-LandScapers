package com.example.vlandscaper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vlandscaper.R;
import com.example.vlandscaper.utilClasses.Notification;

import java.util.ArrayList;

public class NotiRecViewAdapter extends RecyclerView.Adapter<NotiRecViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Notification> listNotification = new ArrayList<>();
    public NotiRecViewAdapter(Context context, ArrayList<Notification> listNotification) {
        this.context = context;
        this.listNotification = listNotification;
    }

    public NotiRecViewAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item_activity, parent, false);
        return new NotiRecViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Notification notifications = listNotification.get(holder.getAdapterPosition());
        holder.notifiction.setText("You order "+notifications.getNotification() + "for more detail Contact at ");
        holder.time.setText(notifications.getTime());
    }

    @Override
    public int getItemCount() {
        return listNotification.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView notifiction, time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            notifiction = itemView.findViewById(R.id.txtNotification);
            time = itemView.findViewById(R.id.txtTimeNot);
        }
    }
}
