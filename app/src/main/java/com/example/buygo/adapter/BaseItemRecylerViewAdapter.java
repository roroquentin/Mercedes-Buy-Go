package com.example.buygo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buygo.R;
import com.example.buygo.models.BaseItemModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BaseItemRecylerViewAdapter extends RecyclerView.Adapter<BaseItemRecylerViewAdapter.BaseItemPostViewHolder> implements Filterable {

    private ArrayList<BaseItemModel> baseItemModelArrayList;
    private ArrayList<BaseItemModel> filterList = new ArrayList<>();

    public BaseItemRecylerViewAdapter(ArrayList<BaseItemModel> baseItemModelArrayList) {
        this.baseItemModelArrayList = baseItemModelArrayList;
        this.filterList.addAll(baseItemModelArrayList);

    }

    public void addItem(BaseItemModel itemModel) {
        baseItemModelArrayList.add(itemModel);
        filterList.add(itemModel);
        notifyDataSetChanged();
    }

    public void clearAll() {
        baseItemModelArrayList = new ArrayList<>();
        filterList = new ArrayList<>();
        notifyDataSetChanged();
    }

    public ArrayList<BaseItemModel> getFilterList() {
        return filterList;
    }

    public void setFilterList(ArrayList<BaseItemModel> filterList) {
        this.filterList = filterList;
    }

    public ArrayList<BaseItemModel> getBaseItemModelArrayList() {
        return baseItemModelArrayList;
    }

    public void setBaseItemModelArrayList(ArrayList<BaseItemModel> baseItemModelArrayList) {
        this.baseItemModelArrayList = baseItemModelArrayList;
    }

    @NonNull
    @Override
    public BaseItemPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycle_row, parent, false);

        return new BaseItemPostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseItemPostViewHolder holder, int position) {

        holder.usermail.setText(baseItemModelArrayList.get(position).getUserMail());
        holder.produktInformation.setText(baseItemModelArrayList.get(position).getProduktInformation());
        holder.produktnName.setText(baseItemModelArrayList.get(position).getProduktName());
        holder.produktStiation.setText(baseItemModelArrayList.get(position).getProduktStiation());
        holder.produktPrice.setText(baseItemModelArrayList.get(position).getProduktPrice());
        holder.produktInformation.setText(baseItemModelArrayList.get(position).getProduktInformation());
        Picasso.get().load(baseItemModelArrayList.get(position).getProduktImage()).into(holder.produktImage);

    }

    @Override
    public int getItemCount() {
        return baseItemModelArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return new BaseItemFilter(this, getBaseItemModelArrayList());
    }

    class BaseItemPostViewHolder extends RecyclerView.ViewHolder {

        ImageView produktImage;
        TextView usermail, produktnName, produktPrice, produktStiation, produktInformation;

        public BaseItemPostViewHolder(@NonNull View itemView) {

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
