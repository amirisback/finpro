package org.d3ifcool.finpro.views.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.views.adapters.viewpagers.MahasiswaPagerAdapter;

public class MahasiswaActivity extends AppCompatActivity {

    private MenuItem prevMenuItem = null;
    private ViewPager mViewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        setTitle(R.string.title_informasi);
        getSupportActionBar().setElevation(0);
        // -----------------------------------------------------------------------------------------
        mViewPager = findViewById(R.id.act_mhs_home_viewpager);
        bottomNavigationView = findViewById(R.id.act_mhs_home_bottom_navigation);
        MahasiswaPagerAdapter mPagerAdapter = new MahasiswaPagerAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        // -----------------------------------------------------------------------------------------
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.bottom_menu_mhs_informasi:
                        mViewPager.setCurrentItem(0);
                        setTitle(R.string.title_informasi);
                        break;
                    case R.id.bottom_menu_mhs_proyekakhir:
                        mViewPager.setCurrentItem(1);
                        setTitle(R.string.title_proyekakhir);
                        break;
                    case R.id.bottom_menu_mhs_judulpa:
                        mViewPager.setCurrentItem(2);
                        setTitle(R.string.title_judulpa);
                        break;
                }
                return false;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null)
                    prevMenuItem.setChecked(false);
                else
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

                switch (position) {
                    case 0:
                        setTitle(R.string.title_informasi);
                        break;
                    case 1:
                        setTitle(R.string.title_proyekakhir);
                        break;
                    case 2:
                        setTitle(R.string.title_judulpa);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.toolbar_menu_profil:
                Intent mIntent = new Intent(MahasiswaActivity.this, MahasiswaProfilActivity.class);
                startActivity(mIntent);
                break;
            case R.id.toolbar_menu_keluar:
                Intent i = new Intent(MahasiswaActivity.this, MainActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}