package org.d3ifcool.dosen.views.activities.details;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.views.adapters.viewpagers.DosenBimbinganPagerAdapter;

public class DosenMahasiswaBimbinganDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MAHASISWA = "extra_mahasiswa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_mahasiswa_bimbingan_detail);

        setTitle(getString(R.string.title_mahasiswa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        // -----------------------------------------------------------------------------------------
        // Deklarasi Element XML
        TabLayout mTabLayout = findViewById(R.id.act_dsn_mhs_bimbingan_detail_tablayout);
        ViewPager mViewPager = findViewById(R.id.act_dsn_mhs_bimbingan_detail_viewpager);
        // -----------------------------------------------------------------------------------------
        // Membuat ViewPager (SLIDER)
        DosenBimbinganPagerAdapter adapter = new DosenBimbinganPagerAdapter(this,getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        // -----------------------------------------------------------------------------------------

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

}
