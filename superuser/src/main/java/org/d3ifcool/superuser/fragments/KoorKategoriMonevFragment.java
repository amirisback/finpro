package org.d3ifcool.superuser.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.service.models.Monev;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.adapters.KoorMonevKategoriViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorKategoriMonevFragment extends Fragment {

    private RecyclerView recyclerView;
    private KoorMonevKategoriViewAdapter adapter;
    private ArrayList<Monev> monevs = new ArrayList<>();
    private View empty_view;
    public KoorKategoriMonevFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_koor_kategori_monev, container, false);
        FloatingActionButton actionButton = view.findViewById(R.id.frg_koor_kategori_monev_fab);
        empty_view = view.findViewById(R.id.view_emptyview);
        recyclerView = view.findViewById(R.id.frg_koor_kategori_monev_rv);

        monevs.add(new Monev(1, "Pra Monev 1"));
        adapter = new KoorMonevKategoriViewAdapter(getContext());
        adapter.setLayoutType(R.layout.content_item_kategori_monev);
        adapter.setMonev(monevs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addKategori();
            }
        });

        if (monevs.size() == 0){
            empty_view.setVisibility(View.VISIBLE);
        }else {
            empty_view.setVisibility(View.GONE);
        }
        return view;
    }
    public void addKategori(){
        final View viewtambahan = getLayoutInflater().inflate(R.layout.content_item_add_kategori_monev,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(viewtambahan.getContext());
            builder.setView(viewtambahan)
                    .setPositiveButton(R.string.tambah, new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                            EditText et_kategori_monev = viewtambahan.findViewById(R.id.dialog_kategori_monev);
                            String kategori = et_kategori_monev.getText().toString();

                        }
                     })
                    .setNegativeButton(R.string.batal, new DialogInterface.OnClickListener() {
                        @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                        }
                    });
                 builder.create().show();
    }

}
