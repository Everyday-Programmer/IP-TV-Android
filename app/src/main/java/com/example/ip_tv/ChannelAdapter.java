package com.example.ip_tv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import java.text.MessageFormat;
import java.util.ArrayList;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ViewHolder> {
    Context context;
    ArrayList<Channel> arrayList;
    StreamClickListener streamClickListener;

    public ChannelAdapter(Context context, ArrayList<Channel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.channel_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(arrayList.get(position).getLogo()).into(holder.imageView);
        holder.name.setText(arrayList.get(position).getName());
        holder.categories.setText(MessageFormat.format("Categories: {0}", arrayList.get(position).getCategories()));
        holder.languages.setText(MessageFormat.format("Languages: {0}", arrayList.get(position).getLanguages()));
        holder.id.setText(MessageFormat.format("Channel ID: {0}", arrayList.get(position).getId()));
        holder.altNames.setText(MessageFormat.format("Alt Names: {0}", arrayList.get(position).getAlt_names()));
        holder.network.setText(MessageFormat.format("Network :{0}", arrayList.get(position).getNetwork()));
        holder.owners.setText(MessageFormat.format("Owners: {0}", arrayList.get(position).getOwners()));
        holder.country.setText(MessageFormat.format("Country: {0}", arrayList.get(position).getCountry()));
        holder.city.setText(MessageFormat.format("City: {0}", arrayList.get(position).getCity()));
        holder.broadcastArea.setText(MessageFormat.format("Broadcast Area: {0}", arrayList.get(position).getBroadcast_area()));
        holder.nsfw.setText(MessageFormat.format("NSFW: {0}", arrayList.get(position).isIs_nsfw()));
        holder.launched.setText(MessageFormat.format("Launched: {0}", arrayList.get(position).getLaunched()));
        holder.closed.setText(MessageFormat.format("Closed: {0}", arrayList.get(position).getClosed()));
        holder.replacedBy.setText(MessageFormat.format("Replaced By: {0}", arrayList.get(position).getReplaced_by()));
        holder.website.setText(MessageFormat.format("Website: {0}", arrayList.get(position).getWebsite()));
        holder.streams.setOnClickListener(view -> streamClickListener.onClick(arrayList.get(position).getId()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.linearLayout.getVisibility() == View.VISIBLE) {
                    holder.linearLayout.setVisibility(View.GONE);
                } else {
                    holder.linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, categories, languages, id, altNames, network, owners, country, city, broadcastArea, nsfw, launched, closed, replacedBy, website;

        LinearLayout linearLayout;
        MaterialButton streams;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            setIsRecyclable(false);
            imageView = itemView.findViewById(R.id.list_item_logo);
            name = itemView.findViewById(R.id.list_item_name);
            categories = itemView.findViewById(R.id.list_item_categories);
            languages = itemView.findViewById(R.id.list_item_languages);
            id = itemView.findViewById(R.id.list_item_id);
            altNames = itemView.findViewById(R.id.list_item_alt_names);
            network = itemView.findViewById(R.id.list_item_network);
            owners = itemView.findViewById(R.id.list_item_owners);
            country = itemView.findViewById(R.id.list_item_country);
            city = itemView.findViewById(R.id.list_item_city);
            broadcastArea = itemView.findViewById(R.id.list_item_broadcast_area);
            nsfw = itemView.findViewById(R.id.list_item_is_nsfw);
            launched = itemView.findViewById(R.id.list_item_launched);
            closed = itemView.findViewById(R.id.list_item_closed);
            replacedBy = itemView.findViewById(R.id.list_item_replaced_by);
            website = itemView.findViewById(R.id.list_item_website);
            linearLayout = itemView.findViewById(R.id.detailsLayout);
            streams = itemView.findViewById(R.id.list_item_streams);
        }
    }

    public void setStreamClickListener(StreamClickListener streamClickListener) {
        this.streamClickListener = streamClickListener;
    }

    public interface StreamClickListener {
        void onClick(String channel);
    }
}