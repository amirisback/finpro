package org.d3ifcool.finpro.dosen.activities.editor.create;

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

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.helpers.SpinnerHelper;
import org.d3ifcool.finpro.core.interfaces.works.ProyekAkhirWorkView;
import org.d3ifcool.finpro.core.interfaces.works.SidangWorkView;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.ProyekAkhirPresenter;
import org.d3ifcool.finpro.core.presenters.SidangPresenter;


public class DosenSidangTambahActivity extends AppCompatActivity implements SidangWorkView, ProyekAkhirWorkView {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";

    private ProgressDialog progressDialog;
    private SidangPresenter sidangPresenter;
    private ProyekAkhirPresenter proyekAkhirPresenter;

    private String tanggal, review, stringNilaiProposal, stringNilaiPembimbing, stringNilaiPenguji1, stringNilaiPenguji2, stringNilaiTotal, status_sidang;
    private int intNilaiProposal = 0, intNilaiPembimbing = 0, intNilaiPenguji1 = 0, intNilaiPenguji2 = 0;
    private double doubleNilaiTotal;
    private int extraProyeAkhirId;

    private EditText et_nilai_proposal, et_nilai_pembimbing1, et_nilai_penguji1, et_nilai_penguji2;
    private TextView tv_nilai_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_sidang_tambah);

        setTitle(getString(R.string.title_sidang_tambah));
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

                if (!stringNilaiProposal.isEmpty() && !stringNilaiPembimbing.isEmpty() && !stringNilaiPenguji1.isEmpty() && !stringNilaiPenguji2.isEmpty()) {
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
                }else if (review.isEmpty()){
                    et_review.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (stringNilaiProposal.isEmpty()){
                    et_nilai_proposal.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (stringNilaiPembimbing.isEmpty()){
                    et_nilai_pembimbing1.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (stringNilaiPenguji1.isEmpty()){
                    et_nilai_penguji1.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (stringNilaiPenguji2.isEmpty()){
                    et_nilai_penguji2.setError(getString(R.string.text_tidak_boleh_kosong));
                }else {
                    sidangPresenter.createSidang(review, tanggal, intNilaiProposal, intNilaiPembimbing, intNilaiPenguji1, intNilaiPenguji2, doubleNilaiTotal, status_sidang, extraProyeAkhirId);
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
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            default:
                break;
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
