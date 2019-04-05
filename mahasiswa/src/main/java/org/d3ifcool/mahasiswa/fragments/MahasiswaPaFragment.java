package org.d3ifcool.mahasiswa.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.mahasiswa.activities.MahasiswaPaBimbinganDetailActivity;
import org.d3ifcool.mahasiswa.activities.MahasiswaPaMonevDetailActivity;
import org.d3ifcool.mahasiswa.activities.MahasiswaPaSidangDetailActivity;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.service.interfaces.lists.BimbinganListView;
import org.d3ifcool.service.interfaces.lists.DosenListView;
import org.d3ifcool.service.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.service.interfaces.objects.DosenPembimbingView;
import org.d3ifcool.service.interfaces.objects.DosenReviewerView;
import org.d3ifcool.service.interfaces.objects.DosenView;
import org.d3ifcool.service.models.Bimbingan;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.service.presenters.BimbinganPresenter;
import org.d3ifcool.service.presenters.DosenPresenter;
import org.d3ifcool.service.presenters.ProyekAkhirPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.service.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DIGUNAKAN;


/**
 * A simple {@link Fragment} subclass.
 */
public class MahasiswaPaFragment extends Fragment implements ProyekAkhirListView, BimbinganListView, DosenPembimbingView, DosenReviewerView {

    private static final String PROYEK_AKHIR_PARAM_1 = "proyek_akhir.judul_id";
    private static final String PROYEK_AKHIR_PARAM_2 = "judul_status";
    private static final String BIMBINGAN_PARAM = "bimbingan.proyek_akhir_id";

    private DosenPresenter dosenPresenter;
    private ProyekAkhirPresenter proyekAkhirPresenter;
    private BimbinganPresenter bimbinganPresenter;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<Bimbingan> arrayListBimbingan = new ArrayList<>();
    private ArrayList<ProyekAkhir> arrayListProyekAkhir = new ArrayList<>();

    private TextView tv_judul_pa,tv_kelompok_pa,tv_nama_anggota1_pa, tv_nama_anggota2_pa,
            tv_nim_anggota1_pa, tv_nim_anggota2_pa, tv_dosen_pembimbing_pa, tv_jumlah_bimbingan_pa,
            tv_dosen_reviewer_pa, tv_jumlah_monev_pa, tv_status_sidang_pa;

    public MahasiswaPaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_mahasiswa_pa, container, false);

        proyekAkhirPresenter = new ProyekAkhirPresenter(this);
        bimbinganPresenter = new BimbinganPresenter(this);
        dosenPresenter = new DosenPresenter(this, this);
        sessionManager = new SessionManager(getContext());
        progressDialog = new ProgressDialog(getContext());

        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh);

        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        tv_judul_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_judulpa);
        tv_kelompok_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_kelompokpa);
        tv_nama_anggota1_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_nama_1);
        tv_nama_anggota2_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_nama_2);
        tv_nim_anggota1_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_nim_1);
        tv_nim_anggota2_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_nim_2);
        tv_dosen_pembimbing_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_dsn_pembimbing);
        tv_jumlah_bimbingan_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_jml_bimbingan);
        tv_dosen_reviewer_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_dsn_reviewer);
        tv_jumlah_monev_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_jml_monev);
        tv_status_sidang_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_sidang);

        CardView cardViewBimbingan = rootView.findViewById(R.id.frg_mhs_pa_cardview_bimbingan);
        CardView cardViewMonev = rootView.findViewById(R.id.frg_mhs_pa_cardview_monev);
        CardView cardViewSidang = rootView.findViewById(R.id.frg_mhs_pa_cardview_sidang);

        final String stringJudulId = String.valueOf(sessionManager.getSessionMahasiswaIdJudul());

        proyekAkhirPresenter.searchProyekAkhirTwoParam(PROYEK_AKHIR_PARAM_1, stringJudulId, PROYEK_AKHIR_PARAM_2, JUDUL_STATUS_DIGUNAKAN);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                proyekAkhirPresenter.searchProyekAkhirTwoParam(PROYEK_AKHIR_PARAM_1, stringJudulId, PROYEK_AKHIR_PARAM_2, JUDUL_STATUS_DIGUNAKAN);
            }
        });

        cardViewBimbingan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MahasiswaPaBimbinganDetailActivity.class);
                startActivity(i);
            }
        });

        cardViewMonev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MahasiswaPaMonevDetailActivity.class);
                startActivity(i);
            }
        });

        cardViewSidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), MahasiswaPaSidangDetailActivity.class);
                startActivity(i);
            }
        });



        return rootView;
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
    public void onGetObjectDosenReviewer(Dosen dosen) {

        if (dosen != null) {
            tv_dosen_reviewer_pa.setText(dosen.getDsn_nama());
        } else {
            tv_dosen_reviewer_pa.setText(R.string.text_no_dosen_monev);
        }


    }

    @Override
    public void onGetObjectDosenPembimbing(Dosen dosen) {

        if (dosen != null) {
            tv_dosen_pembimbing_pa.setText(dosen.getDsn_nama());
        } else {
            tv_dosen_pembimbing_pa.setText(getString(R.string.text_no_dosen_pembimbing));
        }

    }

    @Override
    public void onGetListBimbingan(List<Bimbingan> bimbinganList) {
        arrayListBimbingan.clear();
        arrayListBimbingan.addAll(bimbinganList);

        if (!arrayListBimbingan.isEmpty()){
            int jumlahBimbingan = arrayListBimbingan.size();
            String stringJumlahBimibingan = String.valueOf(jumlahBimbingan);
            tv_jumlah_bimbingan_pa.setText(stringJumlahBimibingan);

        } else {
            int jumlahBimbingan = 0;
            String stringJumlahBimibingan = String.valueOf(jumlahBimbingan);
            tv_jumlah_bimbingan_pa.setText(stringJumlahBimibingan);
        }

    }


    @Override
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {
        arrayListProyekAkhir.clear();
        arrayListProyekAkhir.addAll(proyekAkhirList);
        swipeRefreshLayout.setRefreshing(false);

        if (!arrayListProyekAkhir.isEmpty()) {

            String stringNipPembimbing = String.valueOf(arrayListProyekAkhir.get(0).getPembimbing_dsn_nip());
            String stringNipReviewer = String.valueOf(arrayListProyekAkhir.get(0).getReviewer_dsn_nip());
            String stringProyekAkhirId = String.valueOf(arrayListProyekAkhir.get(0).getProyek_akhir_id());

            tv_judul_pa.setText(arrayListProyekAkhir.get(0).getJudul_nama());
            tv_kelompok_pa.setText(arrayListProyekAkhir.get(0).getNama_tim());
            tv_nama_anggota1_pa.setText(arrayListProyekAkhir.get(0).getMhs_nama());
            tv_nim_anggota1_pa.setText(arrayListProyekAkhir.get(0).getMhs_nim());

            bimbinganPresenter.searchBimbingan(BIMBINGAN_PARAM, stringProyekAkhirId);
            dosenPresenter.getDosenPembimbing(arrayListProyekAkhir.get(0).getPembimbing_dsn_nip());

            if (arrayListProyekAkhir.get(0).getReviewer_dsn_nip() != null) {
                dosenPresenter.getDosenReviewer(arrayListProyekAkhir.get(0).getReviewer_dsn_nip());
            } else {
                tv_dosen_reviewer_pa.setText(R.string.text_no_dosen_monev);
            }

            if (arrayListProyekAkhir.size() == 2) {
                tv_nama_anggota2_pa.setText(arrayListProyekAkhir.get(arrayListProyekAkhir.size()-1).getMhs_nama());
                tv_nim_anggota2_pa.setText(arrayListProyekAkhir.get(arrayListProyekAkhir.size()-1).getMhs_nim());
            } else {
                tv_nama_anggota2_pa.setText("");
                tv_nim_anggota2_pa.setText("");
            }
        }

    }


    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
