package org.d3ifcool.superuser.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.editors.KoorDosenTambahActivity;
import org.d3ifcool.superuser.adapters.KoorDosenViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorDosenFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Dosen> dosen;
    private FloatingActionButton floatingActionButton;
    private KoorDosenViewAdapter adapter;
    public KoorDosenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_koor_dosen, container, false);
        recyclerView = view.findViewById(R.id.frg_koor_dosen_home_recyclerview);
        floatingActionButton = view.findViewById(R.id.frg_koor_dosen_home_fab);
        adapter = new KoorDosenViewAdapter(getContext());

        dosen = new ArrayList<>();
        dosen.add(new Dosen("6706162062","ikhsan", "R.drawable.ic_dummy_photo","ikhsanramadhan28@gmail.com"
                ,"081313198291" ));
        dosen.add(new Dosen("6706162062","ikhsan", "R.drawable.ic_dummy_photo","ikhsanramadhan28@gmail.com"
                ,"081313198291" ));
        dosen.add(new Dosen("6706162062","ikhsan", "R.drawable.ic_dummy_photo","ikhsanramadhan28@gmail.com"
                ,"081313198291" ));
        dosen.add(new Dosen("6706162062","ikhsan", "R.drawable.ic_dummy_photo","ikhsanramadhan28@gmail.com"
                ,"081313198291" ));
        dosen.add(new Dosen("6706162062","ikhsan", "R.drawable.ic_dummy_photo","ikhsanramadhan28@gmail.com"
                ,"081313198291" ));
        dosen.add(new Dosen("6706162062","ikhsan", "R.drawable.ic_dummy_photo","ikhsanramadhan28@gmail.com"
                ,"081313198291" ));
        adapter.setDosens(dosen);
        adapter.setLayoutType(R.layout.content_item_koor_dosen);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KoorDosenTambahActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

}
