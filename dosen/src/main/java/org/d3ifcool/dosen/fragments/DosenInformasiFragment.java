package org.d3ifcool.dosen.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.d3ifcool.dosen.activities.editors.DosenInformasiTambahActivity;
import org.d3ifcool.dosen.R;
import org.d3ifcool.service.interfaces.InformasiViewResult;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.dosen.adapters.recyclerviews.DosenInformasiViewAdapter;
import org.d3ifcool.service.presenters.InformasiPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosenInformasiFragment extends Fragment implements InformasiViewResult {

    private ArrayList<Informasi> arrayList = new ArrayList<>();
    private DosenInformasiViewAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private InformasiPresenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View empty_view;

    public DosenInformasiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_informasi, container, false);

        recyclerView = rootView.findViewById(R.id.frg_dsn_info_home_recyclerview);
        adapter = new DosenInformasiViewAdapter(getContext());
        presenter = new InformasiPresenter(this, getContext());
        progressDialog = new ProgressDialog(getContext());
        swipeRefreshLayout = rootView.findViewById(R.id.frg_dsn_info_home_swiperefresh);
        presenter.getInformasi();

        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.frg_dsn_info_home_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DosenInformasiTambahActivity.class);
                startActivity(i);
            }
        });

        empty_view = rootView.findViewById(R.id.view_emptyview);


        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getInformasi();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getInformasi();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onGetResult(List<Informasi> informasi) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        arrayList.clear();
        arrayList.addAll(informasi);
        adapter.addItem(arrayList);
        adapter.setLayoutType(R.layout.content_item_dosen_informasi);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        swipeRefreshLayout.setRefreshing(false);
        if (arrayList.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
