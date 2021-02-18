package org.d3ifcool.finpro.koor.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.finpro.core.interfaces.lists.KegiatanListView;
import org.d3ifcool.finpro.core.models.Kegiatan;
import org.d3ifcool.finpro.core.presenters.KegiatanPresenter;
import org.d3ifcool.finpro.koor.activities.editor.create.KoorJadwalKegiatanTambahActivity;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.koor.adapters.KoorJadwalKegiatanViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorJadwalKegiatanFragment extends Fragment implements KegiatanListView {

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private View empty_view;

    private ArrayList<Kegiatan> arrayListKegiatan = new ArrayList<>();
    private KegiatanPresenter kegiatanPresenter;
    private KoorJadwalKegiatanViewAdapter koorJadwalKegiatanViewAdapter;


    public KoorJadwalKegiatanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_koor_jadwal_kegiatan, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        kegiatanPresenter = new KegiatanPresenter(this);
        kegiatanPresenter.initContext(getContext());

        koorJadwalKegiatanViewAdapter = new KoorJadwalKegiatanViewAdapter(getContext());
        recyclerView = rootView.findViewById(R.id.all_recyclerview_kegiatan);
        empty_view = rootView.findViewById(R.id.view_emptyview);

        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.frg_koor_kegiatan_home_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KoorJadwalKegiatanTambahActivity.class);
                getContext().startActivity(intent);
            }
        });

        kegiatanPresenter.getKegiatan();

        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();
        kegiatanPresenter.getKegiatan();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onGetListKegiatan(List<Kegiatan> kegiatan) {
        arrayListKegiatan.clear();
        arrayListKegiatan.addAll(kegiatan);

        koorJadwalKegiatanViewAdapter.addItem(arrayListKegiatan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(koorJadwalKegiatanViewAdapter);

        if (arrayListKegiatan.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void isEmptyListKegiatan() {
        empty_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
