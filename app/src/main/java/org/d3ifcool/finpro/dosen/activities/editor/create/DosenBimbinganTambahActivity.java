package org.d3ifcool.finpro.dosen.activities.editor.create;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.interfaces.works.ICreate;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.BimbinganPresenter;

import java.util.ArrayList;

public class DosenBimbinganTambahActivity extends AppCompatActivity implements ICreate {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_bimbingan_tambah);

        setTitle(getString(R.string.title_bimbingan_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        final BimbinganPresenter bimbinganPresenter = new BimbinganPresenter(this);
        bimbinganPresenter.initContext(this);

        MethodHelper methodHelper = new MethodHelper();

        final TextView tv_tanggal = findViewById(R.id.act_dsn_mhs_bimbingan_textview_tanggal);
        final EditText et_judul = findViewById(R.id.act_dsn_info_edittext_judul_bimbingan);
        final EditText et_review = findViewById(R.id.act_dsn_info_edittext_konten);
        Button btn_simpan = findViewById(R.id.act_dsn_info_button_simpan);

        final ArrayList<ProyekAkhir> extraArrayProyekAkhir = getIntent().getParcelableArrayListExtra(EXTRA_PROYEK_AKHIR);

        methodHelper.setDatePicker(this, tv_tanggal);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String judul = et_judul.getText().toString();
                String review = et_review.getText().toString();
                String tanggal = tv_tanggal.getText().toString();

                if (judul.isEmpty()) {
                    et_judul.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (review.isEmpty()){
                    et_review.setError(getString(R.string.text_tidak_boleh_kosong));
                }else{
                    bimbinganPresenter.createBimbingan(review,"tidak hadir",tanggal,"pending",extraArrayProyekAkhir.get(0).getProyek_akhir_id());
                    if (extraArrayProyekAkhir.size()==2){
                        bimbinganPresenter.createBimbingan(review,"tidak hadir",tanggal,"pending",extraArrayProyekAkhir.get(extraArrayProyekAkhir.size()-1).getProyek_akhir_id());
                    }
                }

            }
        });
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