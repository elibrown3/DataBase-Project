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

        public TextView textViewShoeBrand;
        public TextView textViewReleaseDate;
        public TextView textViewColorWay;


        public ShoeViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewShoeBrand = itemView.findViewById(R.id.textViewShoeName);
            textViewColorWay = itemView.findViewById(R.id.textViewColorWay);
            textViewReleaseDate = itemView.findViewById(R.id.textViewReleaseDate);
            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }

        public TextView getShoeTextView() {
            return textViewShoeBrand;
        }

        public TextView getShoeColorWay() {
            return textViewColorWay;
        }

        public TextView getShoeReleaseDate() {
            return textViewReleaseDate;
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
        svh.getShoeTextView().setText(shoeData.get(position).getShoeBrand());
        svh.getShoeColorWay().setText(shoeData.get(position).getColorway());
        svh.getShoeReleaseDate().setText(shoeData.get(position).getReleaseDate());
    }


    public int getItemCount() {
        return shoeData.size();
    }
}