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
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
public class KoorKategoriMonevBehaviourTest {

    @Rule
    public ActivityTestRule<KoorMainActivity> koorMainActivityTestRule = new ActivityTestRule<>(KoorMainActivity.class);

    @Before
    public void init(){
        koorMainActivityTestRule.getActivity();
    }

    @Test
    public void KoorTambahKategoriMonev() throws InterruptedException {

        String KATEGORI_MONEV_1 = "Pra-Monev 1";
        String KATEGORI_MONEV_2 = "Pra-Monev 2";
        String KATEGORI_MONEV_3 = "Pra-Monev 3";
        String KATEGORI_MONEV_4 = "Monev 1";
        String KATEGORI_MONEV_5 = "Monev 2";
        String KATEGORI_MONEV_6 = "Monev 3";

        String MIN_BIMBINGAN_1 = "3";
        String MIN_BIMBINGAN_2 = "6";
        String MIN_BIMBINGAN_3 = "9";
        String MIN_BIMBINGAN_4 = "12";
        String MIN_BIMBINGAN_5 = "14";
        String MIN_BIMBINGAN_6 = "16";

        new HelperTest().openDrawerKoor("Kategori Monev");

        createKategoriMonev(KATEGORI_MONEV_1, MIN_BIMBINGAN_1);
        createKategoriMonev(KATEGORI_MONEV_2, MIN_BIMBINGAN_2);
        createKategoriMonev(KATEGORI_MONEV_3, MIN_BIMBINGAN_3);
        createKategoriMonev(KATEGORI_MONEV_4, MIN_BIMBINGAN_4);
        createKategoriMonev(KATEGORI_MONEV_5, MIN_BIMBINGAN_5);
        createKategoriMonev(KATEGORI_MONEV_6, MIN_BIMBINGAN_6);

    }

    private void createKategoriMonev(String KATEGORI_MONEV, String MIN_BIMBINGAN) throws InterruptedException {
        onView(withId(R.id.frg_koor_kategori_monev_fab)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.dialog_kategori_monev)).perform(typeText(KATEGORI_MONEV));
        pressBack();
        onView(withId(R.id.dialog_jumlah_bimbingan)).perform(typeText(MIN_BIMBINGAN));
        pressBack();
        onView(withText("TAMBAH")).perform(click());
        Thread.sleep(3000);
    }


}
