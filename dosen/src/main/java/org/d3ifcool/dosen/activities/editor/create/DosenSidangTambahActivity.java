package org.d3ifcool.dosen.activities.editor.create;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.helpers.MethodHelper;
import org.d3ifcool.service.interfaces.works.SidangWorkView;
import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.service.presenters.SidangPresenter;

import java.util.ArrayList;

public class DosenSidangTambahActivity extends AppCompatActivity implements SidangWorkView {
    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";
    private ProgressDialog dialog;
    private SidangPresenter presenter;
    private ArrayList<ProyekAkhir> extraPa = new ArrayList<>();
    String tanggal, review, nilai_pro, nilai_pem, nilai_peng1, nilai_peng2, nilai_tot;
    int nilai_proposal, nilai_pembimbing1,nilai_penguji1,nilai_penguji2;
    double nilai_total;
    int extraProyeAkhirId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_sidang_tambah);

        setTitle(getString(R.string.title_sidang_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.text_progress_dialog));
        presenter = new SidangPresenter(this);
        MethodHelper helper = new MethodHelper();

        ProyekAkhir proyekAkhir = getIntent().getParcelableExtra(EXTRA_PROYEK_AKHIR);
        extraProyeAkhirId = proyekAkhir.getProyek_akhir_id();


        final TextView tv_tanggal = findViewById(R.id.act_dsn_mhs_sidang_textview_tanggal);
        final EditText et_review = findViewById(R.id.act_dsn_edittext_sidang_deskripsi);
        final EditText et_np = findViewById(R.id.dsn_sidang_nilai_proposal_1);
        final EditText et_pembimbing1 = findViewById(R.id.dsn_sidang_nilai_pembimbing1_1);
        final EditText et_penguji1 = findViewById(R.id.dsn_sidang_nilai_penguji1_1);
        final EditText et_penguji2 = findViewById(R.id.dsn_sidang_nilai_penguji2_1);
        final TextView tv_nilaiTotal = findViewById(R.id.dsn_sidang_nilai_total_1);

        tv_tanggal.setText(helper.getDateToday());

        Button btn_simpan = findViewById(R.id.act_dsn_info_button_simpan);


        et_np.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String np = et_np.getText().toString();
                if (!np.isEmpty()){
                    nilai_proposal = Integer.parseInt(np);
                    double n_proposal = (nilai_proposal*10)/100;
                    tv_nilaiTotal.setText(String.valueOf(n_proposal));
                }else {
                    tv_nilaiTotal.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_pembimbing1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String np = et_np.getText().toString();
                String nmp1 = et_pembimbing1.getText().toString();
                if (!np.isEmpty() && !nmp1.isEmpty()){
                    nilai_proposal = Integer.parseInt(np);
                    nilai_pembimbing1 = Integer.parseInt(np);
                    double n_proposal = (nilai_proposal*10)/100;
                    double n_pembimbing = (nilai_pembimbing1*50)/100;
                    nilai_total = n_proposal + n_pembimbing;
                    tv_nilaiTotal.setText(String.valueOf(nilai_total));
                }else {
                    tv_nilaiTotal.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_penguji1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String np = et_np.getText().toString();
                String nmp1 = et_pembimbing1.getText().toString();
                String uji1 = et_penguji1.getText().toString();
                if (!np.isEmpty() && !nmp1.isEmpty() && !uji1.isEmpty()){
                    nilai_proposal = Integer.parseInt(np);
                    nilai_pembimbing1 = Integer.parseInt(np);
                    nilai_penguji1 = Integer.parseInt(uji1);
                    double n_proposal = (nilai_proposal*10)/100;
                    double n_pembimbing = (nilai_pembimbing1*50)/100;
                    double n_penguji =((nilai_penguji1/2)*40)/100;
                    nilai_total = n_proposal + n_pembimbing + n_penguji;

                    tv_nilaiTotal.setText(String.valueOf(nilai_total));

                }else {
                    tv_nilaiTotal.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_penguji2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String np = et_np.getText().toString();
                String npm1 = et_pembimbing1.getText().toString();
                String uji1 = et_penguji1.getText().toString();
                String uji2 = et_penguji2.getText().toString();
                if (!np.isEmpty() && !npm1.isEmpty() && !uji1.isEmpty() && !uji2.isEmpty()) {
                    nilai_proposal = Integer.parseInt(np);
                    nilai_pembimbing1 = Integer.parseInt(npm1);
                    nilai_penguji1 = Integer.parseInt(uji1);
                    nilai_penguji2 = Integer.parseInt(uji2);

                    double n_proposal = (nilai_proposal*10)/100;
                    double n_pembimbing = (nilai_pembimbing1 * 50)/100;
                    double n_penguji = (((nilai_penguji1 + nilai_penguji2)/2)*40)/100;

                    nilai_total = n_proposal + n_pembimbing + n_penguji;
                    tv_nilaiTotal.setText(String.valueOf(nilai_total));
                }else {
                    tv_nilaiTotal.setText("");
                }
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
                nilai_pro = et_np.getText().toString();
                nilai_pem = et_pembimbing1.getText().toString();
                nilai_peng1 = et_penguji1.getText().toString();
                nilai_peng2 = et_penguji2.getText().toString();
                nilai_tot = tv_nilaiTotal.getText().toString();
                if (!nilai_pro.isEmpty() && !nilai_pem.isEmpty() && !nilai_peng1.isEmpty() && !nilai_peng2.isEmpty()) {
                    nilai_proposal = Integer.parseInt(nilai_pro);
                    nilai_pembimbing1 = Integer.parseInt(nilai_pem);
                    nilai_penguji1 = Integer.parseInt(nilai_peng1);
                    nilai_penguji2 = Integer.parseInt(nilai_peng2);
                    nilai_total = Double.parseDouble(nilai_tot);
                }
                if (nilai_proposal > 100.0){
                    et_np.setError(getString(R.string.tidak_lebih_100));
                } else if (nilai_pembimbing1 > 100.0){
                    et_pembimbing1.setError(getString(R.string.tidak_lebih_100));
                }else if (nilai_penguji1 > 100.0) {
                    et_penguji1.setError(getString(R.string.tidak_lebih_100));
                }else if (nilai_penguji2 > 100.0) {
                    et_penguji2.setError(getString(R.string.tidak_lebih_100));
                }else if (review.isEmpty()){
                    et_review.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (nilai_pro.isEmpty()){
                    et_np.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (nilai_pem.isEmpty()){
                    et_pembimbing1.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (nilai_peng1.isEmpty()){
                    et_penguji1.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (nilai_peng2.isEmpty()){
                    et_penguji2.setError(getString(R.string.text_tidak_boleh_kosong));
                }
                else {
                    presenter.createSidang(review, tanggal, nilai_proposal, nilai_pembimbing1, nilai_penguji1, nilai_penguji2, nilai_total, extraProyeAkhirId);
                }
            }
        });
    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
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
