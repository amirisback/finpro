package org.d3ifcool.finpro.mahasiswa.fragments.parent;


import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.mahasiswa.adapters.viewpager.MahasiswaJudulPaPagerAdapter;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.lists.JudulListView;
import org.d3ifcool.finpro.core.models.Judul;
import org.d3ifcool.finpro.core.presenters.JudulPresenter;

import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DISETUJUI;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_MENUNGGU;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_PENDING;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_TERSEDIA;


/**
 * A simple {@link Fragment} subclass.
 */
public class MahasiswaJudulPaFragment extends Fragment implements JudulListView {

    private SessionManager sessionManager;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private View mDisableView;

    private String PARAM_JUDUL = "judul.judul_id";
    private ProgressDialog progressDialog;

    private JudulPresenter judulPresenter;

    private TextView textViewJudul, textViewDosen, textViewStatus;

    private MahasiswaJudulPaPagerAdapter adapter;

    public MahasiswaJudulPaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mahasiswa_judul_pa, container, false);
        // -----------------------------------------------------------------------------------------
        progressDialog = new ProgressDialog(requireContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        // -----------------------------------------------------------------------------------------
        judulPresenter = new JudulPresenter(this);
        judulPresenter.initContext(requireContext());
        // -----------------------------------------------------------------------------------------
        return rootView;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // -----------------------------------------------------------------------------------------
        sessionManager = new SessionManager(requireContext());
        // -----------------------------------------------------------------------------------------
        // Deklarasi Element XML
        mTabLayout = view.findViewById(R.id.frg_mhs_judul_pa_tablayout);
        mViewPager = view.findViewById(R.id.frg_mhs_judul_pa_viewpager);
        mDisableView = view.findViewById(R.id.disable_view);
        // -----------------------------------------------------------------------------------------
        textViewJudul = view.findViewById(R.id.dis_judul);
        textViewDosen = view.findViewById(R.id.dis_dosen_pembimbing);
        textViewStatus = view.findViewById(R.id.dis_status);
        // -----------------------------------------------------------------------------------------
        checkStatusJudulMahasiswa(sessionManager.getSessionMahasiswaIdJudul());


    }

    @Override
    public void onResume() {
        super.onResume();
        checkStatusJudulMahasiswa(sessionManager.getSessionMahasiswaIdJudul());
    }

    private void checkStatusJudulMahasiswa(int judulId){
        if (judulId != 0){
            mDisableView.setVisibility(View.VISIBLE);
            mTabLayout.setVisibility(View.GONE);
            mViewPager.setVisibility(View.GONE);
            judulPresenter.searchJudulBy(PARAM_JUDUL, String.valueOf(sessionManager.getSessionMahasiswaIdJudul()));
        } else {
            // Membuat ViewPager (SLIDER)
            adapter = new MahasiswaJudulPaPagerAdapter(requireContext(),getChildFragmentManager());
            mViewPager.setAdapter(adapter);
            mTabLayout.setupWithViewPager(mViewPager);
            // -------------------------------------------------------------------------------------
            mDisableView.setVisibility(View.GONE);
            mTabLayout.setVisibility(View.VISIBLE);
            mViewPager.setVisibility(View.VISIBLE);
        }
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
    public void onGetListJudul(List<Judul> judulpa) {
        textViewJudul.setText(judulpa.get(0).getJudul());
        textViewDosen.setText(judulpa.get(0).getDsn_nama());
        String getStatus = judulpa.get(0).getJudul_status();
        if (getStatus.equalsIgnoreCase(JUDUL_STATUS_TERSEDIA) || getStatus.equalsIgnoreCase(JUDUL_STATUS_PENDING)) {
            textViewStatus.setText(JUDUL_STATUS_MENUNGGU);
        } else if (getStatus.equalsIgnoreCase(JUDUL_STATUS_DISETUJUI)){
            sessionManager.createSessionJudulStatusMahasiswa(getStatus);
            textViewStatus.setText(JUDUL_STATUS_DISETUJUI);
            textViewStatus.setTextColor(getResources().getColor(R.color.colorBackgroundGreen));
        } else {
            sessionManager.createSessionJudulStatusMahasiswa(getStatus);
            textViewStatus.setText(JUDUL_STATUS_DISETUJUI);
            textViewStatus.setTextColor(getResources().getColor(R.color.colorBackgroundGreen));
        }
    }

    @Override
    public void isEmptyListJudul() {

    }

    @Override
    public void onFailed(String message) {
//        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
