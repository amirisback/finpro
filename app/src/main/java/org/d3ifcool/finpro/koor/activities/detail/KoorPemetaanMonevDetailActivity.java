package org.d3ifcool.finpro.koor.activities.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.core.helpers.SpinnerHelper;
import org.d3ifcool.finpro.core.interfaces.lists.DosenListView;
import org.d3ifcool.finpro.core.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.finpro.core.interfaces.works.ProyekAkhirWorkView;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.core.presenters.ProyekAkhirPresenter;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;
import java.util.List;


public class KoorPemetaanMonevDetailActivity extends AppCompatActivity implements ProyekAkhirListView, DosenListView, ProyekAkhirWorkView {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";
    private static final String PARAM_PROYEK_AKHIR_JUDUL_ID = "proyek_akhir.judul_id";
    private static final String PARAM_JUDUL_STATUS = "judul_status";

    private DosenPresenter dosenPresenter;
    private ProyekAkhirPresenter proyekAkhirPresenter;
    private ProgressDialog progressDialog;
    private SpinnerHelper spinnerHelper;
    private Spinner sp_dosen,sp_ubah_reviewer;
    private View view_atur;
    private Button btn_ubah_reviewer;

    private String spinnerItemPosition, spinnerItemNipDosen, extraDsnNip, spinnerItemPositionBaru, spinnerItemNipDosenBaru
            , tempreviewer;


    private ArrayList<Dosen> arrayListDosen = new ArrayList<>();
    private ArrayList<Dosen> tempArrayListDosen = new ArrayList<>();
    private ArrayList<ProyekAkhir> arrayListProyekAkhir = new ArrayList<>();

    private TextView tv_judul_pa,tv_kelompok_pa,tv_nama_anggota1_pa, tv_nama_anggota2_pa,
            tv_dosen_reviewer_pa;
    private AlertDialog.Builder mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_pemetaan_monev_detail);

        setTitle(getString(R.string.title_pemetaan_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        proyekAkhirPresenter = new ProyekAkhirPresenter(this, this);
        dosenPresenter = new DosenPresenter(this);

        proyekAkhirPresenter.initContext(this);
        dosenPresenter.initContext(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        tv_judul_pa = findViewById(R.id.ctn_koor_pemetaan_judul);
        tv_kelompok_pa = findViewById(R.id.ctn_koor_pemetaan_kelompok);
        tv_nama_anggota1_pa = findViewById(R.id.ctn_koor_pemetaan_anggota1);
        tv_nama_anggota2_pa = findViewById(R.id.ctn_koor_pemetaan_anggota2);
        tv_dosen_reviewer_pa = findViewById(R.id.ctn_koor_pemetaan_reviewer);
        btn_ubah_reviewer = findViewById(R.id.act_koor_btn_ubah_reviewer);
        sp_dosen = findViewById(R.id.sp_dosen);
        spinnerHelper = new SpinnerHelper(this);

        final View view_ubah = LayoutInflater.from(KoorPemetaanMonevDetailActivity.this).inflate(R.layout.dialog_edit_reviewer, null);
        sp_ubah_reviewer = view_ubah.findViewById(R.id.spinner_reviewer_ubah);
        view_atur = findViewById(R.id.view_atur);

        Button buttonAtur = findViewById(R.id.ctn_koor_pemetaan_button_atur);

        ProyekAkhir extraProyekAkhir = getIntent().getParcelableExtra(EXTRA_PROYEK_AKHIR);
        final String stringJudulId = String.valueOf(extraProyekAkhir.getJudul_id());
        extraDsnNip = extraProyekAkhir.getPembimbing_dsn_nip();

        dosenPresenter.getDosen();
        proyekAkhirPresenter.searchAllProyekAkhirBy(PARAM_PROYEK_AKHIR_JUDUL_ID, stringJudulId);

        sp_dosen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerItemPosition = parent.getItemAtPosition(position).toString();
                spinnerItemNipDosen = arrayListDosen.get(position).getDsn_nip();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        buttonAtur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (arrayListProyekAkhir.size() == 2){
                    proyekAkhirPresenter.updateReviewer(arrayListProyekAkhir.get(0).getProyek_akhir_id(), spinnerItemNipDosen);
                    proyekAkhirPresenter.updateReviewerFinish(arrayListProyekAkhir.get(1).getProyek_akhir_id(), spinnerItemNipDosen);
                } else {
                    proyekAkhirPresenter.updateReviewerFinish(arrayListProyekAkhir.get(0).getProyek_akhir_id(), spinnerItemNipDosen);
                }

            }
        });

        btn_ubah_reviewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                sp_ubah_reviewer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        spinnerItemNipDosenBaru = parent.getItemAtPosition(position).toString();
                        spinnerItemNipDosenBaru = arrayListDosen.get(position).getDsn_nip();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                mDialog = new AlertDialog.Builder(view_ubah.getContext());
                        mDialog.setView(view_ubah)
                                .setCancelable(true)
                                .setPositiveButton(R.string.ubah, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        if (arrayListProyekAkhir.size() == 2){
                                            proyekAkhirPresenter.updateReviewer(arrayListProyekAkhir.get(0).getProyek_akhir_id(), spinnerItemNipDosenBaru);
                                            proyekAkhirPresenter.updateReviewerFinish(arrayListProyekAkhir.get(1).getProyek_akhir_id(), spinnerItemNipDosenBaru);
                                        } else {
                                            proyekAkhirPresenter.updateReviewerFinish(arrayListProyekAkhir.get(0).getProyek_akhir_id(), spinnerItemNipDosenBaru);
                                        }
                                        dialog.dismiss();
                                        if (view_ubah.getParent() != null) {
                                            ((ViewGroup) view_ubah.getParent()).removeView(view_ubah);
                                        }
                                    }
                                })
                                .setNegativeButton(R.string.batal, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        if (view_ubah.getParent() != null) {
                                            ((ViewGroup) view_ubah.getParent()).removeView(view_ubah);
                                        }
                                    }
                                })
                        .show();
            }
        });


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

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
    public void onSucces() {
        finish();
    }

    @Override
    public void onGetListDosen(List<Dosen> dosen) {
        tempArrayListDosen.clear();
        tempArrayListDosen.addAll(dosen);

        for (int i = 0; i < tempArrayListDosen.size(); i++){
            if (!tempArrayListDosen.get(i).getDsn_nip().equalsIgnoreCase(extraDsnNip)) {
                arrayListDosen.add(tempArrayListDosen.get(i));
            }
        }
        spinnerHelper.initSpinnerDosen(arrayListDosen, sp_dosen);
        spinnerHelper.initSpinnerDosen(arrayListDosen, sp_ubah_reviewer);

    }

    @Override
    public void isEmptyListDosen() {

    }

    @Override
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {
        arrayListProyekAkhir.clear();
        arrayListProyekAkhir.addAll(proyekAkhirList);

        if (!arrayListProyekAkhir.isEmpty()) {

            tv_judul_pa.setText(arrayListProyekAkhir.get(0).getJudul_nama());
            tv_kelompok_pa.setText(arrayListProyekAkhir.get(0).getNama_tim());
            tv_nama_anggota1_pa.setText(arrayListProyekAkhir.get(0).getMhs_nama());

            if (arrayListProyekAkhir.get(0).getReviewer_dsn_nip() != null) {
                view_atur.setVisibility(View.GONE);
                btn_ubah_reviewer.setVisibility(View.VISIBLE);
                tv_dosen_reviewer_pa.setText(arrayListProyekAkhir.get(0).getReviewer_dsn_nama());
            } else {
                tv_dosen_reviewer_pa.setText(R.string.text_no_dosen_monev);
                btn_ubah_reviewer.setVisibility(View.GONE);
                view_atur.setVisibility(View.VISIBLE);
            }
            if (arrayListProyekAkhir.size() == 2) {
                tv_nama_anggota2_pa.setText(arrayListProyekAkhir.get(arrayListProyekAkhir.size()-1).getMhs_nama());
            } else {
                tv_nama_anggota2_pa.setText("");
            }
        }

    }

    @Override
    public void isEmptyListProyekAkhir() {

    }


    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

