package org.d3ifcool.finpro.dosen;

import androidx.test.rule.ActivityTestRule;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.DosenMainActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 15/06/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * org.d3ifcool.finpro.dosen
 */


public class DosenTambahJudulBehaviorTest {

    @Rule
    public ActivityTestRule<DosenMainActivity> dosenMainActivityTestRule = new ActivityTestRule<>(DosenMainActivity.class);

    @Before
    public void init(){
        dosenMainActivityTestRule.getActivity();
    }

    @Test
    public void DosenTambahJudulSubDosenTest() throws InterruptedException {

        String testJudul = "Finpro - Manajemen Proyek Akhir Android Based";
        String testDeskripsi = "Finpro adalah aplikasi manajemen proyek akhir milik prodi D3 RPLA";

        Thread.sleep(2000);
        onView(withId(R.id.bottom_menu_dsn_judulpa)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.frg_dsn_judul_dsn_fab)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.act_dsn_judul_pa_edittext_judul)).perform(typeText(testJudul));
        Thread.sleep(1000);
        pressBack();
        onView(withId(R.id.act_dsn_judul_pa_edittext_deskripsi)).perform(typeText(testDeskripsi));
        Thread.sleep(1000);
        pressBack();
        Thread.sleep(1000);
        onView(withId(R.id.act_dsn_judul_pa_button_simpan)).perform(click());
        Thread.sleep(3000);
    }


}
