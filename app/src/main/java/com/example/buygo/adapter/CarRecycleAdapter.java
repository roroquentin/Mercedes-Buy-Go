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
import com.example.buygo.helper.CarFilter;
import com.example.buygo.models.CarModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CarRecycleAdapter extends RecyclerView.Adapter<CarRecycleAdapter.CarPost> implements Filterable {

    private ArrayList<CarModel> carModelArrayList;

    public CarRecycleAdapter(ArrayList<CarModel> carModelArrayList) {
        this.carModelArrayList = carModelArrayList;

    }

    public ArrayList<CarModel> getCarModelArrayList() {
        return carModelArrayList;
    }

    public void setCarModelArrayList(ArrayList<CarModel> carModelArrayList) {
        this.carModelArrayList = carModelArrayList;
    }

    @NonNull
    @Override
    public CarPost onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycle_row, parent, false);

        return new CarPost(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarPost holder, int position) {

        holder.usermail.setText(carModelArrayList.get(position).getUserMail());
        holder.produktInformation.setText(carModelArrayList.get(position).getProduktInformation());
        holder.produktnName.setText(carModelArrayList.get(position).getProduktName());
        holder.produktStiation.setText(carModelArrayList.get(position).getProduktStiation());
        holder.produktPrice.setText(carModelArrayList.get(position).getProduktPrice());
        holder.produktInformation.setText(carModelArrayList.get(position).getProduktInformation());
        Picasso.get().load(carModelArrayList.get(position).getProduktImage()).into(holder.produktImage);

    }

    @Override
    public int getItemCount() {
        return carModelArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return new CarFilter(this, getCarModelArrayList());
    }

    class CarPost extends RecyclerView.ViewHolder {

        ImageView produktImage;
        TextView usermail, produktnName, produktPrice, produktStiation, produktInformation;

        public CarPost(@NonNull View itemView) {

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
