package org.d3ifcool.superuser.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.network.api.ApiInterfaceDosen;
import org.d3ifcool.service.network.bridge.ApiClient;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.editors.KoorJudulPaSubdosenTambahActivity;
import org.d3ifcool.superuser.adapters.KoorJudulPaSubdosenViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorJudulPaSubdosenFragment extends Fragment {
    private Spinner sp_dosen;
    private RecyclerView recyclerView;
    private KoorJudulPaSubdosenViewAdapter adapter;
    private FloatingActionButton actionButton;
    private ProgressDialog dialog;
    private ArrayList<Judul> judul;

    public KoorJudulPaSubdosenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_koor_judul_pa_subdosen, container, false);
        sp_dosen = view.findViewById(R.id.spinner_dosen);
        recyclerView = view.findViewById(R.id.frg_koor_judul_dsn_recyclerview);
        actionButton = view.findViewById(R.id.frg_koor_judul_dsn_fab);
        dialog = new ProgressDialog(getContext());
        adapter = new KoorJudulPaSubdosenViewAdapter(getContext());

        dialog.setMessage(getString(R.string.progress_dialog));
        dialog.show();

        judul = new ArrayList<>();
//        judul.add(new Judul("huhu","haha"));
//        judul.add(new Judul("huhu","haha"));
//        judul.add(new Judul("huhu","haha"));
//        judul.add(new Judul("huhu","haha"));
//        judul.add(new Judul("huhu","haha"));
        adapter.additem(judul);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KoorJudulPaSubdosenTambahActivity.class);
                startActivity(intent);
            }
        });
        initSpinner();
        return view;
    }

    private void initSpinner() {
        ApiInterfaceDosen interfaceDosen = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<List<Dosen>> call = interfaceDosen.getDosen();
        call.enqueue(new Callback<List<Dosen>>() {
            @Override
            public void onResponse(Call<List<Dosen>> call, Response<List<Dosen>> response) {
                if (response.isSuccessful()) {
                    List<Dosen> dosens = response.body();
                    List<String> spinner = new ArrayList<String>();
                    for (int i = 0; i < dosens.size(); i++) {
//                        nip_dosen = dosens.get(i).getDsn_nip();
                        spinner.add(dosens.get(i).getDsn_nama());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item
                            , spinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_dosen.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Dosen>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
