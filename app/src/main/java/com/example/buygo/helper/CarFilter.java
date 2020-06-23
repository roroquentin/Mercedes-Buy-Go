package com.example.buygo.helper;

import android.widget.Filter;

import com.example.buygo.adapter.CarRecycleAdapter;
import com.example.buygo.models.CarModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamurcuabi on 23,June,2020
 **/
public class CarFilter extends Filter {
    ArrayList<CarModel> filterList;
    CarRecycleAdapter adapter;

    public CarFilter(CarRecycleAdapter adapter,
                     ArrayList<CarModel> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }


    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toLowerCase();
            List<CarModel> filtered = new ArrayList<>();

            for (int i = 0; i < adapter.getCarModelArrayList().size(); i++) {
                if ((adapter.getCarModelArrayList().get(i)).getProduktInformation().toLowerCase().contains(constraint)) {
                    filtered.add(adapter.getCarModelArrayList().get(i));
                }

            }

            results.count = filtered.size();
            results.values = filtered;
        } else {
            results.count = adapter.getCarModelArrayList().size();
            results.values = adapter.getCarModelArrayList();

        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.setCarModelArrayList((ArrayList<CarModel>) results.values);
    }
}

