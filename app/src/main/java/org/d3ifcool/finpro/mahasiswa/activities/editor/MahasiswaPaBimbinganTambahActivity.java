package org.d3ifcool.finpro.mahasiswa.activities.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.works.ICreate;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.BimbinganPresenter;

import java.util.ArrayList;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_BIMBINGAN_PENDING;

public class MahasiswaPaBimbinganTambahActivity extends AppCompatActivity implements ICreate {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";
    private ProgressDialog progressDialog;
    private String kehadiran_1, kehadiran_2;
    private View container_view;

    private int successBool = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_pa_bimbingan_tambah);

        setTitle(getString(R.string.title_bimbingan_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        final BimbinganPresenter bimbinganPresenter = new BimbinganPresenter(this);
        bimbinganPresenter.initContext(this);

        MethodHelper methodHelper = new MethodHelper();
        SessionManager sessionManager = new SessionManager(this);

        container_view = findViewById(R.id.container_mahasiswa_2);
        Button btn_simpan = findViewById(R.id.act_mhs_info_button_simpan);
        final TextView tv_tanggal = findViewById(R.id.act_mhs_bimbingan_textview_tanggal);
        final EditText et_review = findViewById(R.id.act_mhs_info_edittext_konten);
        final TextView textviewNamaMahasiswa1 = findViewById(R.id.act_mhs_nama_1);
        final TextView textviewNamaMahasiswa2 = findViewById(R.id.act_mhs_nama_2);
        final Spinner spinnerKehadiran1 = findViewById(R.id.spinner_kehadiran_1);
        final Spinner spinnerKehadiran2 = findViewById(R.id.spinner_kehadiran_2);


        final ArrayList<ProyekAkhir> extraArrayProyekAkhir = getIntent().getParcelableArrayListExtra(EXTRA_PROYEK_AKHIR);
        if (extraArrayProyekAkhir.size() == 2){
            container_view.setVisibility(View.VISIBLE);
            for (int i = 0; i < extraArrayProyekAkhir.size(); i++){
                if (extraArrayProyekAkhir.get(i).getMhs_nim().equals(sessionManager.getSessionMahasiswaNim()) ){
                    textviewNamaMahasiswa1.setText(sessionManager.getSessionMahasiswaNama());
                } else {
                    textviewNamaMahasiswa2.setText(extraArrayProyekAkhir.get(i).getMhs_nama());
                }
            }
        } else {
            container_view.setVisibility(View.GONE);
            textviewNamaMahasiswa1.setText(sessionManager.getSessionMahasiswaNama());
        }

        spinnerKehadiran1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kehadiran_1 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerKehadiran2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kehadiran_2 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        methodHelper.setDatePicker(this, tv_tanggal);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String review = et_review.getText().toString();
                String tanggal = tv_tanggal.getText().toString();

                if (review.isEmpty()){
                    et_review.setError(getString(R.string.text_tidak_boleh_kosong));
                }else{

                    if (extraArrayProyekAkhir.size() != 2){
                        bimbinganPresenter.createBimbingan(review, kehadiran_1, tanggal, STATUS_BIMBINGAN_PENDING, extraArrayProyekAkhir.get(0).getProyek_akhir_id());

                    } else {
                        bimbinganPresenter.createBimbingan(review, kehadiran_1, tanggal, STATUS_BIMBINGAN_PENDING, extraArrayProyekAkhir.get(0).getProyek_akhir_id());
                        bimbinganPresenter.createBimbingan(review, kehadiran_2, tanggal, STATUS_BIMBINGAN_PENDING, extraArrayProyekAkhir.get(1).getProyek_akhir_id());
                    }
                }

            }
        });
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
