// companies.java
package com.example.jobfit.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.jobfit.R;

public class companies extends Fragment {

    public companies() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_companies, container, false);

        // Mengambil referensi ke layout yang akan diklik
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout item1 = view.findViewById(R.id.item1);
        LinearLayout item2 = view.findViewById(R.id.item2);
        LinearLayout item3 = view.findViewById(R.id.item3);
        LinearLayout item4 = view.findViewById(R.id.item4);

        // Menambahkan click listener untuk masing-masing item
        item1.setOnClickListener(v -> openFragment(new FactsCompFragment()));
        item2.setOnClickListener(v -> openFragment(new fragment_facts_comptwo()));
        item3.setOnClickListener(v -> openFragment(new fragment_facts_compthree()));
        item4.setOnClickListener(v -> openFragment(new fragment_facts_compfour()));

        return view;
    }

    private void openFragment(Fragment fragment) {
        // Melakukan perpindahan ke Fragment yang ditentukan
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
