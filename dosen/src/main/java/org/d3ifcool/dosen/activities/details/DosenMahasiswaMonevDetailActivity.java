package org.d3ifcool.dosen.activities.details;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.adapters.viewpagers.DosenMonevPagerAdapter;

public class DosenMahasiswaMonevDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_mahasiswa_monev_detail);

        setTitle(getString(R.string.title_mahasiswa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

//        // -----------------------------------------------------------------------------------------
//        // Deklarasi Element XML
//        TabLayout mTabLayout = findViewById(R.id.act_dsn_mhs_monev_detail_tablayout);
//        ViewPager mViewPager = findViewById(R.id.act_dsn_mhs_monev_detail_viewpager);
//        // -----------------------------------------------------------------------------------------
//        // Membuat ViewPager (SLIDER)
//        DosenMonevPagerAdapter adapter = new DosenMonevPagerAdapter(this,getSupportFragmentManager());
//        mViewPager.setAdapter(adapter);
//        mTabLayout.setupWithViewPager(mViewPager);
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
