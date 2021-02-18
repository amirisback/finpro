package org.d3ifcool.finpro.dosen.fragments.parent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.d3ifcool.finpro.dosen.activities.editor.create.DosenInformasiTambahActivity;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.lists.InformasiListView;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.dosen.adapters.recyclerview.DosenInformasiViewAdapter;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosenInformasiFragment extends Fragment implements InformasiListView {

    private ArrayList<Informasi> arrayList = new ArrayList<>();
    private DosenInformasiViewAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private InformasiPresenter informasiPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View empty_view;

    public DosenInformasiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dosen_informasi, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.frg_dsn_info_home_recyclerview);
        adapter = new DosenInformasiViewAdapter(getContext());
        informasiPresenter = new InformasiPresenter(this);
        progressDialog = new ProgressDialog(getContext());
        empty_view = view.findViewById(R.id.view_emptyview);
        swipeRefreshLayout = view.findViewById(R.id.frg_dsn_info_home_swiperefresh);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        informasiPresenter.initContext(getContext());
        informasiPresenter.getInformasi();

        FloatingActionButton floatingActionButton = view.findViewById(R.id.frg_dsn_info_home_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DosenInformasiTambahActivity.class);
                startActivity(i);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                informasiPresenter.getInformasi();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        informasiPresenter.getInformasi();
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
    public void onGetListInformasi(List<Informasi> informasi) {
        arrayList.clear();
        arrayList.addAll(informasi);
        adapter.addItem(arrayList);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);

        if (arrayList.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void isEmptyListInformasi() {
        empty_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
