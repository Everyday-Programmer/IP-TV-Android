package com.example.ip_tv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.MessageFormat;
import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    Context context;
    ArrayList<Country> arrayList;
    OnItemClickListener onItemClickListener;

    public CountryAdapter(Context context, ArrayList<Country> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.flag.setText(arrayList.get(position).getFlag());
        holder.name.setText(arrayList.get(position).getName());
        holder.code.setText(MessageFormat.format("Code: {0}", arrayList.get(position).getCode()));
        holder.languages.setText(MessageFormat.format("Languages: {0}", arrayList.get(position).getLanguages()));
        holder.itemView.setOnClickListener(view -> onItemClickListener.onClick(arrayList.get(position).getCode()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView flag, name, code, languages;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flag = itemView.findViewById(R.id.list_item_flag);
            name = itemView.findViewById(R.id.list_item_country_name);
            code = itemView.findViewById(R.id.list_item_code);
            languages = itemView.findViewById(R.id.list_item_languages);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(String country);
    }
}