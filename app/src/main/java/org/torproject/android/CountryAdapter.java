package org.torproject.android; // پکیج خودت رو وارد کن

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private List<String> countryList;
    private Context context;
    private OnCountryClickListener onCountryClickListener;

    public interface OnCountryClickListener {
        void onCountryClick(int position);
    }

    public CountryAdapter(Context context, List<String> countryList, OnCountryClickListener listener) {
        this.context = context;
        this.countryList = countryList;
        this.onCountryClickListener = listener;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_list_item, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        String item = countryList.get(position);
        if (position == 0) {
            holder.countryFlag.setText("🌐");
            holder.countryName.setText(item);
        } else {
            String[] parts = item.split(" ", 2);
            if (parts.length == 2) {
                holder.countryFlag.setText(parts[0]);
                holder.countryName.setText(parts[1]);
            } else {
                holder.countryFlag.setText("");
                holder.countryName.setText(item);
            }
        }

        holder.itemView.setOnClickListener(v -> {
            if (onCountryClickListener != null) {
                onCountryClickListener.onCountryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView countryFlag;
        TextView countryName;

        CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryFlag = itemView.findViewById(R.id.countryFlag);
            countryName = itemView.findViewById(R.id.countryName);
        }
    }
}