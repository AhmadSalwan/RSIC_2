package com.example.jobfit.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jobfit.model.Item;
import com.example.jobfit.adapter.MyAdapter;
import com.example.jobfit.R;

import java.util.ArrayList;
import java.util.List;

public class CompassFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Item> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compass, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize data list
        itemList = new ArrayList<>();
        itemList.add(new Item("Title 1", "Description 1"));
        itemList.add(new Item("Title 2", "Description 2"));
        itemList.add(new Item("Title 3", "Description 3"));
        // Add more items...

        // Set adapter
        adapter = new MyAdapter(itemList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
