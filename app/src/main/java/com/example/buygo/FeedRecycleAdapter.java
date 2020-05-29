package com.example.buygo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.collect.BiMap;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedRecycleAdapter extends RecyclerView.Adapter<FeedRecycleAdapter.PostHolder> {

    private ArrayList<String> userMailList;
    private ArrayList<String> produktNameList;
    private ArrayList<String> produktPriceList;
    private ArrayList<String> produktStiationList;
    private ArrayList<String> produktInformationList;
    private  ArrayList<String> produktImageList;

    public FeedRecycleAdapter(ArrayList<String> userMailList, ArrayList<String> produktNameList, ArrayList<String> produktPriceList, ArrayList<String> produktStiationList, ArrayList<String> produktInformationList, ArrayList<String> produktImageList) {
        this.userMailList = userMailList;
        this.produktNameList = produktNameList;
        this.produktPriceList = produktPriceList;
        this.produktStiationList = produktStiationList;
        this.produktInformationList = produktInformationList;
        this.produktImageList = produktImageList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycle_row,parent,false);

        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        holder.usermail.setText(userMailList.get(position));
        holder.produktInformation.setText(produktInformationList.get(position));
        holder.produktStiation.setText(produktStiationList.get(position));
        holder.produktPrice.setText(produktPriceList.get(position));
        holder.produktInformation.setText(produktInformationList.get(position));
        Picasso.get().load(produktImageList.get(position)).into(holder.produktImage);


    }

    @Override
    public int getItemCount() {
        return userMailList.size();
    }

    class PostHolder extends RecyclerView.ViewHolder {

        ImageView produktImage;
        TextView usermail, produktnName, produktPrice,produktStiation, produktInformation;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            produktImage = itemView.findViewById(R.id.recycle_row_image);
            usermail = itemView.findViewById(R.id.recycle_row_usermail);
            produktnName = itemView.findViewById(R.id.recycle_row_produktname);
            produktPrice = itemView.findViewById(R.id.recycle_row_produktprice);
            produktStiation = itemView.findViewById(R.id.recycle_row_produktstiation);
            produktInformation = itemView.findViewById(R.id.recycle_row_produktinformation);

        }
    }
}
