package org.d3ifcool.finpro.dosen.activities.editor.update;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.helpers.SpinnerHelper;
import org.d3ifcool.finpro.core.interfaces.works.ProyekAkhirWorkView;
import org.d3ifcool.finpro.core.interfaces.works.SidangWorkView;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.models.Sidang;
import org.d3ifcool.finpro.core.presenters.ProyekAkhirPresenter;
import org.d3ifcool.finpro.core.presenters.SidangPresenter;
import org.d3ifcool.finpro.R;

public class DosenSidangUbahActivity extends AppCompatActivity implements SidangWorkView, ProyekAkhirWorkView {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";
    public static final String EXTRA_SIDANG = "extra_sidang";

    private ProgressDialog progressDialog;
    private SidangPresenter sidangPresenter;
    private ProyekAkhirPresenter proyekAkhirPresenter;

    private String tanggal, review, stringNilaiProposal, stringNilaiPembimbing, stringNilaiPenguji1, stringNilaiPenguji2, stringNilaiTotal, status_sidang;
    private int intNilaiProposal = 0, intNilaiPembimbing = 0, intNilaiPenguji1 = 0, intNilaiPenguji2 = 0;
    private double doubleNilaiTotal;
    private int extraProyeAkhirId;

    private EditText et_nilai_proposal, et_nilai_pembimbing1, et_nilai_penguji1, et_nilai_penguji2;
    private TextView tv_nilai_total;

    private String extraSidangId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_sidang_ubah);

        setTitle(getString(R.string.title_sidang_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        sidangPresenter = new SidangPresenter(this);
        proyekAkhirPresenter = new ProyekAkhirPresenter(this);

        sidangPresenter.initContext(this);
        proyekAkhirPresenter.initContext(this);

        MethodHelper helper = new MethodHelper();
        SpinnerHelper helper1 = new SpinnerHelper(this);

        final ProyekAkhir proyekAkhir = getIntent().getParcelableExtra(EXTRA_PROYEK_AKHIR);
        extraProyeAkhirId = proyekAkhir.getProyek_akhir_id();

        et_nilai_proposal = findViewById(R.id.dsn_sidang_nilai_proposal_1);
        et_nilai_pembimbing1 = findViewById(R.id.dsn_sidang_nilai_pembimbing1_1);
        et_nilai_penguji1 = findViewById(R.id.dsn_sidang_nilai_penguji1_1);
        et_nilai_penguji2 = findViewById(R.id.dsn_sidang_nilai_penguji2_1);
        tv_nilai_total = findViewById(R.id.dsn_sidang_nilai_total_1);

        final TextView tv_tanggal = findViewById(R.id.act_dsn_mhs_sidang_textview_tanggal);
        final EditText et_review = findViewById(R.id.act_dsn_edittext_sidang_deskripsi);
        final Spinner sp_status_sidang = findViewById(R.id.act_dsn_sidang_spinner);
        Button btn_simpan = findViewById(R.id.act_dsn_info_button_simpan);

        Sidang extraSidang = getIntent().getParcelableExtra(EXTRA_SIDANG);
        extraSidangId = extraSidang.getSidang_id();
        int extraNilaiProposal = extraSidang.getNilai_proposal();
        int extraNilaiPembimbing1 = extraSidang.getNilai_pembimbing();
        int extraNilaiPenguji1 = extraSidang.getNilai_penguji_1();
        int extraNilaiPenguji2 = extraSidang.getNilai_penguji_2();
        double extraNilaiTotal = extraSidang.getNilai_total();
        String extraSidangReview = extraSidang.getSidang_review();

        et_review.setText(extraSidangReview);
        et_nilai_proposal.setText(String.valueOf(extraNilaiProposal));
        et_nilai_pembimbing1.setText(String.valueOf(extraNilaiPembimbing1));
        et_nilai_penguji1.setText(String.valueOf(extraNilaiPenguji1));
        et_nilai_penguji2.setText(String.valueOf(extraNilaiPenguji2));
        tv_nilai_total.setText(String.valueOf(extraNilaiTotal));
        et_review.setText(extraSidangReview);

        tv_tanggal.setText(helper.getDateToday());
        helper1.initSpinnerStatus(sp_status_sidang);

        et_nilai_proposal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setNilaiTotal();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_nilai_pembimbing1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setNilaiTotal();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_nilai_penguji1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setNilaiTotal();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_nilai_penguji2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setNilaiTotal();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanggal = tv_tanggal.getText().toString();
                review = et_review.getText().toString();
                status_sidang = sp_status_sidang.getSelectedItem().toString();

                if (stringNilaiProposal != null && stringNilaiPembimbing != null && stringNilaiPenguji1 != null && stringNilaiPenguji2 != null) {
                    setNilaiTotal();
                }

                if (intNilaiProposal > 100.0){
                    et_nilai_proposal.setError(getString(R.string.validate_tidak_lebih_100));
                } else if (intNilaiPembimbing > 100.0){
                    et_nilai_pembimbing1.setError(getString(R.string.validate_tidak_lebih_100));
                }else if (intNilaiPenguji1 > 100.0) {
                    et_nilai_penguji1.setError(getString(R.string.validate_tidak_lebih_100));
                }else if (intNilaiPenguji2 > 100.0) {
                    et_nilai_penguji2.setError(getString(R.string.validate_tidak_lebih_100));
                }else if (review == null){
                    et_review.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (stringNilaiProposal == null) {
                    et_nilai_proposal.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (stringNilaiPembimbing == null){
                    et_nilai_pembimbing1.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (stringNilaiPenguji1 == null){
                    et_nilai_penguji1.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (stringNilaiPenguji2 == null){
                    et_nilai_penguji2.setError(getString(R.string.text_tidak_boleh_kosong));
                }else {
                    sidangPresenter.updateSidang(extraSidangId, review, tanggal, intNilaiProposal, intNilaiPembimbing, intNilaiPenguji1, intNilaiPenguji2, doubleNilaiTotal, status_sidang);
                    proyekAkhirPresenter.updateNilai(extraProyeAkhirId, doubleNilaiTotal);
                }
            }
        });
    }


    private void setNilaiTotal(){
        stringNilaiProposal = et_nilai_proposal.getText().toString();
        stringNilaiPembimbing = et_nilai_pembimbing1.getText().toString();
        stringNilaiPenguji1 = et_nilai_penguji1.getText().toString();
        stringNilaiPenguji2 = et_nilai_penguji2.getText().toString();
        stringNilaiTotal = tv_nilai_total.getText().toString();

        if (!stringNilaiProposal.equalsIgnoreCase("")) {
            intNilaiProposal = Integer.parseInt(stringNilaiProposal);
        }

        if (!stringNilaiPembimbing.equalsIgnoreCase("")) {
            intNilaiPembimbing = Integer.parseInt(stringNilaiPembimbing);
        }

        if (!stringNilaiPenguji1.equalsIgnoreCase("")) {
            intNilaiPenguji1 = Integer.parseInt(stringNilaiPenguji1);
        }

        if (!stringNilaiPenguji2.equalsIgnoreCase("")) {
            intNilaiPenguji2 = Integer.parseInt(stringNilaiPenguji2);
        }

        double doubleNilaiProposal = intNilaiProposal * 0.1;
        double doubleNilaiPembimbing = intNilaiPembimbing * 0.5;
        double doubleNilaiPenguji = (intNilaiPenguji1 + intNilaiPenguji2) * 0.5 * 0.4;

        doubleNilaiTotal = doubleNilaiProposal + doubleNilaiPembimbing + doubleNilaiPenguji;
        tv_nilai_total.setText(String.valueOf(doubleNilaiTotal));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
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
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

