package org.d3ifcool.finpro.dosen.activities.editor.update;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.interfaces.works.MonevDetailWorkView;
import org.d3ifcool.finpro.core.models.DetailMonev;
import org.d3ifcool.finpro.core.presenters.MonevDetailPresenter;

public class DosenMonevUbahActivity extends AppCompatActivity implements MonevDetailWorkView {

    public static final String EXTRA_DETAIL_MONEV = "extra_detail_monev";
    private ProgressDialog progressDialog;
    private MonevDetailPresenter detailMonevPresenter;

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
        detailMonevPresenter = new MonevDetailPresenter(this);

        detailMonevPresenter.initContext(this);

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
                new AlertDialog
                        .Builder(DosenMonevUbahActivity.this)
                        .setTitle(getString(R.string.dialog_ubah_title))
                        .setMessage(getString(R.string.dialog_ubah_text))
                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String review = et_review_monev.getText().toString();
                                String nilai_st = et_nilai_monev.getText().toString();
                                int nilai = Integer.parseInt(nilai_st);
                                String tanggal = methodHelper.getDateToday();

                                if (review.isEmpty()){
                                    et_review_monev.setError(getString(R.string.text_tidak_boleh_kosong));
                                }else if (nilai_st.isEmpty()){
                                    et_nilai_monev.setError(getString(R.string.text_tidak_boleh_kosong));
                                } else {
                                    detailMonevPresenter.updateMonevDetail(extraMonevDetailId, nilai, tanggal, review);
                                }
                            }
                        })
                        .setNegativeButton(R.string.tidak, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
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