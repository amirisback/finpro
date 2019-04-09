package org.d3ifcool.dosen.activities.editors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.interfaces.works.DetailMonevWorkView;
import org.d3ifcool.service.interfaces.works.MonevWorkView;
import org.d3ifcool.service.presenters.DetailMonevPresenter;
import org.d3ifcool.service.presenters.MonevPresenter;

public class DosenMonevTambahActivity extends AppCompatActivity implements DetailMonevWorkView {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";

    private ProgressDialog dialog;
    private DetailMonevPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_monev_tambah);

        setTitle(getString(R.string.title_monev_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.text_progress_dialog));
        presenter = new DetailMonevPresenter(this);

        EditText et_review_monev = findViewById(R.id.act_dsn_edittext_deskripsi);
        EditText et_nilai_monev = findViewById(R.id.act_dsn_edittext_nilai_monev);

        String review = et_review_monev.getText().toString();
        String nilai_st = et_nilai_monev.getText().toString();
//        int nilai = Integer.parseInt(nilai_st);

        if (review.isEmpty()){
            et_review_monev.setError("Review Ini Harus Di isi");
        }else if (nilai_st.isEmpty()){
            et_nilai_monev.setError("Nilai Harus di Isi");
        }else {
//            presenter.createDetailMonev(nilai);
        }
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
