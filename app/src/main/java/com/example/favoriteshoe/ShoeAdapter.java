package com.example.favoriteshoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoeAdapter extends RecyclerView.Adapter {

    private ArrayList<Shoe> shoeData;
    private View.OnClickListener onClickListener;

    public class ShoeViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewShoe;

        public ShoeViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewShoe = itemView.findViewById(R.id.textViewShoeName);
            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }

        public TextView getShoeTextView() {
            return textViewShoe;
        }

    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public ShoeAdapter(ArrayList<Shoe> arrayList) { shoeData = arrayList; }

    @NonNull

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_view, parent, false);
        return new ShoeViewHolder(v);
    }


    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ShoeViewHolder svh = (ShoeViewHolder) holder;
        svh.getShoeTextView().setText(shoeData.toString());//get(position).getColorway());
    }


    public int getItemCount() {
        return shoeData.size();
    }
}