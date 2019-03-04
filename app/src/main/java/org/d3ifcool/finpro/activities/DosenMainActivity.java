package org.d3ifcool.finpro.activities;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.dosen.activities.notifications.DosenPemberitahuanActivity;
import org.d3ifcool.dosen.activities.profiles.DosenProfilActivity;
import org.d3ifcool.finpro.R;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.finpro.views.adapters.DosenPagerAdapter;

public class DosenMainActivity extends AppCompatActivity {

    private MenuItem prevMenuItem = null;
    private ViewPager mViewPager;
    private BottomNavigationView bottomNavigationView;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_main);

        setTitle(R.string.title_informasi);
        getSupportActionBar().setElevation(0);
        // -----------------------------------------------------------------------------------------
        mViewPager = findViewById(R.id.act_dsn_home_viewpager);
        bottomNavigationView = findViewById(R.id.act_dsn_home_bottom_navigation);
        DosenPagerAdapter mPagerAdapter = new DosenPagerAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        sessionManager = new SessionManager(this);
        // -----------------------------------------------------------------------------------------
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.bottom_menu_dsn_informasi:
                        mViewPager.setCurrentItem(0);
                        setTitle(R.string.title_informasi);
                        break;
                    case R.id.bottom_menu_dsn_pa:
                        mViewPager.setCurrentItem(1);
                        setTitle(R.string.title_proyekakhir);
                        break;
                    case R.id.bottom_menu_dsn_judulpa:
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
            case R.id.toolbar_menu_pemberitahuan:
                Intent intentPemberitahuan = new Intent(DosenMainActivity.this, DosenPemberitahuanActivity.class);
                startActivity(intentPemberitahuan);
                break;
            case R.id.toolbar_menu_profil:
                Intent intentProfil = new Intent(DosenMainActivity.this, DosenProfilActivity.class);
                startActivity(intentProfil);
                break;
            case R.id.toolbar_menu_keluar:
                Intent intentKeluar = new Intent(DosenMainActivity.this, LoginActivity.class);
                startActivity(intentKeluar);
                sessionManager.removeSession();
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }



}
