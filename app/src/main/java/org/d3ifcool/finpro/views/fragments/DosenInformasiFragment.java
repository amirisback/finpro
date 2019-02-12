package org.d3ifcool.finpro.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.finpro.views.activities.adds.DosenInformasiTambahActivity;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.models.dataclass.Informasi;
import org.d3ifcool.finpro.views.adapters.recyclerviews.DosenInformasiViewAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosenInformasiFragment extends Fragment {


    public DosenInformasiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_informasi, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.frg_dsn_info_home_recyclerview);
        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.frg_dsn_info_home_fab);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        DosenInformasiViewAdapter adapter = new DosenInformasiViewAdapter(getContext());

        ArrayList<Informasi> arrayList = new ArrayList<>();
        arrayList.add(new Informasi("a", "Judul", getString(R.string.dummyLong), getString(R.string.dummy_tanggal), getString(R.string.dummy_dosen_pembimbing)));
        arrayList.add(new Informasi("a", "Judul", getString(R.string.dummyLong), getString(R.string.dummy_tanggal), getString(R.string.dummy_dosen_pembimbing)));
        arrayList.add(new Informasi("a", "Judul", getString(R.string.dummyLong), getString(R.string.dummy_tanggal), getString(R.string.dummy_dosen_pembimbing)));
        arrayList.add(new Informasi("a", "Judul", getString(R.string.dummyLong), getString(R.string.dummy_tanggal), getString(R.string.dummy_dosen_pembimbing)));
        arrayList.add(new Informasi("a", "Judul", getString(R.string.dummyLong), getString(R.string.dummy_tanggal), getString(R.string.dummy_dosen_pembimbing)));
        arrayList.add(new Informasi("a", "Judul", getString(R.string.dummyLong), getString(R.string.dummy_tanggal), getString(R.string.dummy_dosen_pembimbing)));

        adapter.addItem(arrayList);
        adapter.setLayoutType(R.layout.content_item_dosen_informasi);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DosenInformasiTambahActivity.class);
                startActivity(i);
            }
        });


        return rootView;
    }



}
