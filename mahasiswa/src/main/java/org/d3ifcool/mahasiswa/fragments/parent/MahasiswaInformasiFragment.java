package org.d3ifcool.mahasiswa.fragments.parent;


import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.mahasiswa.adapters.recyclerview.MahasiswaInformasiViewAdapter;
import org.d3ifcool.service.interfaces.lists.InformasiListView;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.service.presenters.InformasiPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MahasiswaInformasiFragment extends Fragment implements InformasiListView {

    private ArrayList<Informasi> arrayList = new ArrayList<>();
    private MahasiswaInformasiViewAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private InformasiPresenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View empty_view;

    public MahasiswaInformasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_mahasiswa_informasi, container, false);

        recyclerView = rootView.findViewById(R.id.frg_mhs_info_recyclerview);
        swipeRefreshLayout = rootView.findViewById(R.id.frg_mhs_info_swiperefresh);
        empty_view = rootView.findViewById(R.id.view_emptyview);

        adapter = new MahasiswaInformasiViewAdapter(getContext());
        presenter = new InformasiPresenter(this);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        adapter.setLayoutType(R.layout.content_item_mahasiswa_informasi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter.getInformasi();

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
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
