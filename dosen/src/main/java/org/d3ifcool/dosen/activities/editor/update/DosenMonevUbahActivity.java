package org.d3ifcool.dosen.activities.editor.update;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.helpers.MethodHelper;
import org.d3ifcool.service.interfaces.works.DetailMonevWorkView;
import org.d3ifcool.service.models.DetailMonev;
import org.d3ifcool.service.presenters.DetailMonevPresenter;

public class DosenMonevUbahActivity extends AppCompatActivity implements DetailMonevWorkView {

    public static final String EXTRA_DETAIL_MONEV = "extra_detail_monev";
    private ProgressDialog progressDialog;
    private DetailMonevPresenter detailMonevPresenter;

    private MethodHelper methodHelper;

    private int extraMonevDetailId;
    private DetailMonev extraMonevDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_monev_ubah);

        setTitle(getString(R.string.title_monev_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        methodHelper = new MethodHelper();
        detailMonevPresenter = new DetailMonevPresenter(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        final EditText et_review_monev = findViewById(R.id.act_dsn_edittext_deskripsi);
        final EditText et_nilai_monev = findViewById(R.id.act_dsn_edittext_nilai_monev);
        Button buttonMonev = findViewById(R.id.act_dsn_info_button_ubah);

        extraMonevDetail = getIntent().getParcelableExtra(EXTRA_DETAIL_MONEV);
        String extraMonevUlasan = extraMonevDetail.getMonev_ulasan();
        int extraMonevNilai = extraMonevDetail.getMonev_nilai();
        extraMonevDetailId = extraMonevDetail.getMonev_detail_id();

        String nilai = String.valueOf(extraMonevNilai);
        et_nilai_monev.setText(nilai);
        et_review_monev.setText(extraMonevUlasan);

        buttonMonev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String review = et_review_monev.getText().toString();
                String nilai_st = et_nilai_monev.getText().toString();
                int nilai = Integer.parseInt(nilai_st);
                String tanggal = methodHelper.getDateToday();

                if (review.isEmpty()){
                    et_review_monev.setError("Review Ini Harus Di isi");
                }else if (nilai_st.isEmpty()){
                    et_nilai_monev.setError("Nilai Harus di Isi");
                } else {
                    detailMonevPresenter.updateDetailMonev(extraMonevDetailId, nilai, tanggal, review);
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
        progressDialog.hide();
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