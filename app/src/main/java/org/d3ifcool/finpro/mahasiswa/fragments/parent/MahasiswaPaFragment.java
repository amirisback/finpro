package org.d3ifcool.finpro.mahasiswa.fragments.parent;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.core.adapters.AnggotaViewAdapter;
import org.d3ifcool.finpro.core.helpers.ViewAdapterHelper;
import org.d3ifcool.finpro.core.interfaces.lists.BimbinganSearchListView;
import org.d3ifcool.finpro.core.models.Judul;
import org.d3ifcool.finpro.mahasiswa.activities.MahasiswaPaBimbinganActivity;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.mahasiswa.activities.detail.MahasiswaPaMonevDetailActivity;
import org.d3ifcool.finpro.mahasiswa.activities.detail.MahasiswaPaSidangDetailActivity;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.finpro.core.interfaces.objects.DosenPembimbingView;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.BimbinganPresenter;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.core.presenters.ProyekAkhirPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_ARSIP;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DIGUNAKAN;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUMLAH_BIMBINGAN_SIDANG;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_BIMBINGAN_DISETUJUI;


/**
 * A simple {@link Fragment} subclass.
 */
public class MahasiswaPaFragment extends Fragment implements ProyekAkhirListView,
        BimbinganSearchListView, DosenPembimbingView{

    private static final String PARAM_PROYEK_AKHIR_JUDUL = "proyek_akhir.judul_id";
    private static final String PARAM_BIMBINGAN_ID = "bimbingan.proyek_akhir_id";
    private static final String PARAM_BIMBINGAN_STATUS = "bimbingan.bimbingan_status";
    private static final String PARAM_JUDUL_STATUS = "judul_status";
    private static final String PARAM_JUDUL_ID = "judul_id";

    private DosenPresenter dosenPresenter;
    private ProyekAkhirPresenter proyekAkhirPresenter;
    private BimbinganPresenter bimbinganPresenter;

    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;

    private View disable_view;
    private Dosen parcelDosenPembimbing;
    private ProyekAkhir parcelProyekAkhir;
    private int jumlahBimbingan;

    private ViewAdapterHelper viewAdapterHelper;
    private AnggotaViewAdapter anggotaViewAdapter;

    private ArrayList<Bimbingan> arrayListBimbingan = new ArrayList<>();
    private ArrayList<ProyekAkhir> arrayListProyekAkhir = new ArrayList<>();
    private ArrayList<Judul> arrayListJudul = new ArrayList<>();

    private TextView tv_judul_pa,tv_kelompok_pa, tv_dosen_pembimbing_pa, tv_jumlah_bimbingan_pa,
            tv_dosen_reviewer_pa, tv_status_sidang_pa;

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
        dosenPresenter = new DosenPresenter(this);

        proyekAkhirPresenter.initContext(getContext());
        bimbinganPresenter.initContext(getContext());
        dosenPresenter.initContext(getContext());

        sessionManager = new SessionManager(getContext());
        progressDialog = new ProgressDialog(getContext());

        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh);
        disable_view = rootView.findViewById(R.id.disable_view);

        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        tv_judul_pa = rootView.findViewById(R.id.ctn_all_pa_textview_judul);
        tv_kelompok_pa = rootView.findViewById(R.id.ctn_all_pa_textview_kelompok);
        tv_dosen_pembimbing_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_dsn_pembimbing);
        tv_jumlah_bimbingan_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_jml_bimbingan);
        tv_dosen_reviewer_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_dsn_reviewer);
        tv_status_sidang_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_sidang);

        CardView cardViewBimbingan = rootView.findViewById(R.id.frg_mhs_pa_cardview_bimbingan);
        CardView cardViewMonev = rootView.findViewById(R.id.frg_mhs_pa_cardview_monev);
        CardView cardViewSidang = rootView.findViewById(R.id.frg_mhs_pa_cardview_sidang);

        RecyclerView recyclerView = rootView.findViewById(R.id.all_recyclerview_anggota);

        anggotaViewAdapter = new AnggotaViewAdapter(getContext());
        viewAdapterHelper = new ViewAdapterHelper(getContext());
        viewAdapterHelper.setRecyclerView(recyclerView);


        checkStatusJudulMahasiswa(sessionManager.getSessionMahasiswaIdJudul());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkStatusJudulMahasiswa(sessionManager.getSessionMahasiswaIdJudul());
            }
        });

        cardViewBimbingan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MahasiswaPaBimbinganActivity.class);
                ArrayList<ProyekAkhir> extraArrayProyekAkhir = arrayListProyekAkhir;
                i.putExtra(MahasiswaPaBimbinganActivity.EXTRA_DOSEN_PEMBIMBING, parcelDosenPembimbing);
                i.putParcelableArrayListExtra(MahasiswaPaBimbinganActivity.EXTRA_PROYEK_AKHIR, extraArrayProyekAkhir);
                i.putExtra(MahasiswaPaBimbinganActivity.EXTRA_BIMBINGAN_JUMLAH, jumlahBimbingan);
                startActivity(i);
            }
        });


        cardViewMonev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (parcelProyekAkhir.getReviewer_dsn_nip() != null) {
                    Intent i = new Intent(getContext(), MahasiswaPaMonevDetailActivity.class);
                    i.putExtra(MahasiswaPaMonevDetailActivity.EXTRA_PROYEK_AKHIR, parcelProyekAkhir);
                    startActivity(i);
                } else {
                    Toast.makeText(getContext(), getContext().getString(R.string.text_no_dosen_monev), Toast.LENGTH_SHORT).show();
                }

            }
        });


        cardViewSidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), MahasiswaPaSidangDetailActivity.class);
                i.putExtra(MahasiswaPaSidangDetailActivity.EXTRA_PROYEK_AKHIR, parcelProyekAkhir);
                startActivity(i);
            }
        });

        return rootView;
    }

    private void checkStatusJudulMahasiswa(int judulId){
        if (judulId != 0){
            if (sessionManager.getSessionMahasiswaJudulStatus() != null) {
                if (sessionManager.getSessionMahasiswaJudulStatus().equalsIgnoreCase(JUDUL_STATUS_DIGUNAKAN)) {
                    proyekAkhirPresenter.searchAllProyekAkhirByTwo(PARAM_PROYEK_AKHIR_JUDUL, String.valueOf(judulId), PARAM_JUDUL_STATUS, JUDUL_STATUS_DIGUNAKAN);
                } else if (sessionManager.getSessionMahasiswaJudulStatus().equalsIgnoreCase(JUDUL_STATUS_ARSIP)) {
                    proyekAkhirPresenter.searchAllProyekAkhirByTwo(PARAM_PROYEK_AKHIR_JUDUL, String.valueOf(judulId), PARAM_JUDUL_STATUS, JUDUL_STATUS_ARSIP);
                } else {
                    disable_view.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setVisibility(View.GONE);
                }
            } else {
                disable_view.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setVisibility(View.GONE);
            }
        } else {
            disable_view.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkStatusJudulMahasiswa(sessionManager.getSessionMahasiswaIdJudul());
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
    public void onGetListBimbinganSearch(List<Bimbingan> bimbinganList) {
        arrayListBimbingan.clear();
        arrayListBimbingan.addAll(bimbinganList);

        if (!arrayListBimbingan.isEmpty()){
            jumlahBimbingan = arrayListBimbingan.size();
            String stringJumlahBimibingan = String.valueOf(jumlahBimbingan);
            tv_jumlah_bimbingan_pa.setText(stringJumlahBimibingan);

        } else {
            jumlahBimbingan = 0;
            String stringJumlahBimibingan = String.valueOf(jumlahBimbingan);
            tv_jumlah_bimbingan_pa.setText(stringJumlahBimibingan);
        }

        if (arrayListProyekAkhir.get(0).getNilai_total() == 0){
            if (arrayListBimbingan.size() >= JUMLAH_BIMBINGAN_SIDANG){
                tv_status_sidang_pa.setText(getString(R.string.text_siap_sidang));
                tv_status_sidang_pa.setTextColor(getResources().getColor(R.color.colorBackgroundYellow));

            }else {
                tv_status_sidang_pa.setText(getString(R.string.text_belum_siap_sidang));
                tv_status_sidang_pa.setTextColor(getResources().getColor(R.color.colorTextRed));

            }
        } else {
            tv_status_sidang_pa.setText(getString(R.string.text_sudah_sidang));
            tv_status_sidang_pa.setTextColor(getResources().getColor(R.color.colorTextGreen));
        }
    }

    @Override
    public void onGetObjectDosenPembimbing(Dosen dosen) {

        parcelDosenPembimbing = dosen;

        if (dosen != null) {
            tv_dosen_pembimbing_pa.setText(dosen.getDsn_nama());
        } else {
            tv_dosen_pembimbing_pa.setText(getString(R.string.text_no_dosen_pembimbing));
        }

    }

    @Override
    public void isEmptyObjectDosenPembimbing() {

    }

    @Override
    public void isEmptyListBimbingan() {

    }


    @Override
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {
        arrayListProyekAkhir.clear();
        arrayListProyekAkhir.addAll(proyekAkhirList);
        swipeRefreshLayout.setRefreshing(false);

        if (arrayListProyekAkhir.size() != 0) {
            disable_view.setVisibility(View.GONE);
            swipeRefreshLayout.setVisibility(View.VISIBLE);

            parcelProyekAkhir = arrayListProyekAkhir.get(0);
            String stringProyekAkhirId = String.valueOf(arrayListProyekAkhir.get(0).getProyek_akhir_id());

            tv_judul_pa.setText(arrayListProyekAkhir.get(0).getJudul_nama());
            tv_kelompok_pa.setText(arrayListProyekAkhir.get(0).getNama_tim());

            bimbinganPresenter.searchBimbinganAllByTwo(PARAM_BIMBINGAN_ID, stringProyekAkhirId, PARAM_BIMBINGAN_STATUS, STATUS_BIMBINGAN_DISETUJUI);
            dosenPresenter.getDosenPembimbing(arrayListProyekAkhir.get(0).getPembimbing_dsn_nip());

            if (arrayListProyekAkhir.get(0).getReviewer_dsn_nip() != null) {
                tv_dosen_reviewer_pa.setText(arrayListProyekAkhir.get(0).getReviewer_dsn_nama());
            } else {
                tv_dosen_reviewer_pa.setText(R.string.text_no_dosen_monev);
            }

            anggotaViewAdapter.addItem(arrayListProyekAkhir);
            viewAdapterHelper.setAdapterAnggota(anggotaViewAdapter);

        } else {
            swipeRefreshLayout.setVisibility(View.GONE);
            disable_view.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void isEmptyListProyekAkhir() {

    }


    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
