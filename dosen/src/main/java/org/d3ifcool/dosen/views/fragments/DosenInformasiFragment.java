package org.d3ifcool.dosen.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.dosen.views.activities.adds.DosenInformasiTambahActivity;
import org.d3ifcool.dosen.R;
import org.d3ifcool.service.interfaces.InformasiView;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.dosen.views.adapters.recyclerviews.DosenInformasiViewAdapter;
import org.d3ifcool.service.presenter.InformasiPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosenInformasiFragment extends Fragment implements InformasiView {


    ArrayList<Informasi> arrayList = new ArrayList<>();
    DosenInformasiViewAdapter adapter;
    RecyclerView recyclerView;

    public DosenInformasiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_informasi, container, false);

        recyclerView = rootView.findViewById(R.id.frg_dsn_info_home_recyclerview);
        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.frg_dsn_info_home_fab);

        InformasiPresenter presenter = new InformasiPresenter(this, getContext());
        presenter.getInformasi();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DosenInformasiTambahActivity.class);
                startActivity(i);
            }
        });


        return rootView;
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onGetResult(List<Informasi> informasi) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        arrayList.clear();
        arrayList.addAll(informasi);
        adapter = new DosenInformasiViewAdapter(getContext());
        adapter.addItem(arrayList);
        adapter.notifyDataSetChanged();
        adapter.setLayoutType(R.layout.content_item_dosen_informasi);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void onErrorLoading(String message) {

    }
}
