package com.example.ip_tv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StreamAdapter extends RecyclerView.Adapter<StreamAdapter.ViewHolder> {
    Context context;
    ArrayList<Stream> arrayList;
    OnItemClickListener onItemClickListener;

    public StreamAdapter(Context context, ArrayList<Stream> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stream_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.channel.setText(arrayList.get(position).getChannel());
        holder.url.setText(arrayList.get(position).getUrl());
        holder.itemView.setOnClickListener(view -> onItemClickListener.onClick(arrayList.get(position).getUrl()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView channel, url;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            channel = itemView.findViewById(R.id.list_item_channel);
            url = itemView.findViewById(R.id.list_item_url);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(String url);
    }
}