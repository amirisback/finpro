package org.d3ifcool.mahasiswa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.service.interfaces.lists.DosenListView;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.presenters.DosenPresenter;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaJudulPaPengajuanTambahActivity extends AppCompatActivity implements DosenListView {
    private ArrayList<Dosen> arrayListDosen = new ArrayList<>();
    private Spinner sp_dosen, sp_kategori;
    private DosenPresenter presenter;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_judul_pa_pengajuan_tambah);
        presenter = new DosenPresenter(this);
        dialog = new ProgressDialog(this);
        sp_dosen = findViewById(R.id.act_mhs_list_dosen_pengajuan);
        sp_kategori = findViewById(R.id.act_mhs_list_kategori);

        dialog.setMessage(getString(R.string.text_progress_dialog));
        presenter.getDosen();

        initSpinnerKategori();
    }
    private void initSpinnerNamaDosen(ArrayList<Dosen> arrayDosen, Spinner spinner) {
        List<String> spinnerItem = new ArrayList<>();
        for (int i = 0; i < arrayDosen.size(); i++) {
            spinnerItem.add(arrayDosen.get(i).getDsn_nama());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, spinnerItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    private void initSpinnerKategori() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.kategori_proyek_akhir,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_kategori.setAdapter(adapter);

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
    public void onGetListDosen(List<Dosen> dosen) {
        arrayListDosen.clear();
        arrayListDosen.addAll(dosen);
        initSpinnerNamaDosen(arrayListDosen,sp_dosen);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
