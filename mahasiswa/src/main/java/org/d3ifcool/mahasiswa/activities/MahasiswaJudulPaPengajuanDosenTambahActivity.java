package org.d3ifcool.mahasiswa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.d3ifcool.mahasiswa.R;

public class MahasiswaJudulPaPengajuanDosenTambahActivity extends AppCompatActivity {


    public static final String EXTRA_JUDUL = "extra_judul";
    public static final String EXTRA_DOSEN = "extra_dosen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_judul_pa_pengajuan_dosen);
    }
}
