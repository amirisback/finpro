package org.d3ifcool.finpro.koor;

import androidx.test.rule.ActivityTestRule;

import org.d3ifcool.finpro.HelperTest;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.KoorMainActivity;
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
 * Copyright (C) 16/06/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * org.d3ifcool.finpro.koor
 */
public class KoorJudulBehaviourTest {

    @Rule
    public ActivityTestRule<KoorMainActivity> koorMainActivityTestRule = new ActivityTestRule<>(KoorMainActivity.class);

    @Before
    public void init(){
        koorMainActivityTestRule.getActivity();
    }

    @Test
    public void KoorTambahJudul() throws InterruptedException {

        String testJudul = "Finpro - Manajemen Proyek Akhir Android Based";
        String testDeskripsi = "Finpro adalah aplikasi manajemen proyek akhir milik prodi D3 RPLA";

        new HelperTest().openDrawerKoor("Judul");

        createJudul(testJudul, testDeskripsi);
    }

    private void createJudul(String JUDUL, String DESKRIPSI) throws InterruptedException {
        onView(withId(R.id.frg_koor_judul_dsn_fab)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.act_koor_judul_pa_edittext_judul)).perform(typeText(JUDUL));
        pressBack();
        onView(withId(R.id.act_koor_judul_pa_edittext_deskripsi)).perform(typeText(DESKRIPSI));
        pressBack();
        onView(withId(R.id.act_koor_judul_pa_button_simpan)).perform(click());
        Thread.sleep(3000);
    }

}
