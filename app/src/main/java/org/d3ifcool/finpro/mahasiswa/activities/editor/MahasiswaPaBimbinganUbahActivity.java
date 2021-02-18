package org.d3ifcool.finpro.mahasiswa.activities.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.interfaces.works.ICreate;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.presenters.BimbinganPresenter;

public class MahasiswaPaBimbinganUbahActivity extends AppCompatActivity implements ICreate {

    public static final String EXTRA_BIMBINGAN = "extra_bimbingan";
    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";

    private BimbinganPresenter bimbinganPresenter;
    private ProgressDialog dialog;
    private Bimbingan extrabimbingan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_pa_bimbingan_ubah);

        bimbinganPresenter = new BimbinganPresenter(this);
        bimbinganPresenter.initContext(this);

        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.text_progress_dialog));

        extrabimbingan = getIntent().getParcelableExtra(EXTRA_BIMBINGAN);

        MethodHelper methodHelper = new MethodHelper();

        final TextView tv_tanggal = findViewById(R.id.act_mhs_bimbingan_textview_tanggal);
        final EditText et_judul = findViewById(R.id.act_mhs_info_edittext_judul_bimbingan);
        final EditText et_review = findViewById(R.id.act_mhs_info_edittext_konten);
        Button btn_simpan = findViewById(R.id.act_mhs_info_button_simpan);

        methodHelper.setDatePicker(this, tv_tanggal);

        final String tanggal = extrabimbingan.getBimbingan_tanggal();
        final String kehadiran = extrabimbingan.getBimbingan_kehadiran();
        String isi = extrabimbingan.getBimbingan_review();

        tv_tanggal.setText(tanggal);
        et_judul.setText(kehadiran);
        et_review.setText(isi);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog
                        .Builder(MahasiswaPaBimbinganUbahActivity.this)
                        .setTitle(getString(R.string.dialog_ubah_title))
                        .setMessage(getString(R.string.dialog_ubah_text))
                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String tanggal_baru = tv_tanggal.getText().toString();
                                String judul_baru = et_judul.getText().toString();
                                String isi_baru = et_review.getText().toString();
                                if (tanggal_baru.isEmpty()){
                                    tv_tanggal.setError(getString(R.string.validate_tanggal));
                                }else if (judul_baru.isEmpty()){
                                    et_judul.setError(getString(R.string.text_tidak_boleh_kosong));
                                }else if (isi_baru.isEmpty()){
                                    et_review.setError(getString(R.string.text_tidak_boleh_kosong));
                                }else{
                                    bimbinganPresenter.updateBimbingan(extrabimbingan.getBimbingan_id(), isi_baru, tanggal_baru);
                                }
                            }
                        })

                        .setNegativeButton(R.string.tidak, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

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
