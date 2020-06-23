package com.example.buygo.adapter;

import android.widget.Filter;

import com.example.buygo.adapter.BaseItemRecylerViewAdapter;
import com.example.buygo.models.BaseItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamurcuabi on 23,June,2020
 **/
public class BaseItemFilter extends Filter {
    ArrayList<BaseItemModel> filterList;
    BaseItemRecylerViewAdapter adapter;

    public BaseItemFilter(BaseItemRecylerViewAdapter adapter,
                          ArrayList<BaseItemModel> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }


    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toLowerCase();
            List<BaseItemModel> filtered = new ArrayList<>();

            for (int i = 0; i < adapter.getFilterList().size(); i++) {
                if ((adapter.getFilterList().get(i)).getProduktInformation().toLowerCase().contains(constraint)) {
                    filtered.add(adapter.getFilterList().get(i));
                }

            }

            results.count = filtered.size();
            results.values = filtered;
        } else {
            results.count = adapter.getBaseItemModelArrayList().size();
            results.values = adapter.getBaseItemModelArrayList();

        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.setBaseItemModelArrayList((ArrayList<BaseItemModel>) results.values);
    }
}

