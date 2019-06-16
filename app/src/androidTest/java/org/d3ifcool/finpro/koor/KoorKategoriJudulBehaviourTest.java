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
public class KoorKategoriJudulBehaviourTest {

    @Rule
    public ActivityTestRule<KoorMainActivity> koorMainActivityTestRule = new ActivityTestRule<>(KoorMainActivity.class);

    @Before
    public void init(){
        koorMainActivityTestRule.getActivity();
    }

    @Test
    public void KoorTambahKategoriJudul() throws InterruptedException {

        String KATEGORI_WEB = "Website";
        String KATEGORI_ANDROID = "Android";
        String KATEGORI_IOT = "Internet Of Things";
        String KATEGORI_VR = "Virtual Reality";
        String KATEGORI_AR = "Augmented Reality";

        new HelperTest().openDrawerKoor("Kategori Judul");

        createKategoriJudul(KATEGORI_WEB);
        createKategoriJudul(KATEGORI_ANDROID);
        createKategoriJudul(KATEGORI_IOT);
        createKategoriJudul(KATEGORI_VR);
        createKategoriJudul(KATEGORI_AR);

    }

    private void createKategoriJudul(String KATEGORI_JUDUL) throws InterruptedException {
        onView(withId(R.id.frg_koor_info_home_fab)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.dialog_kategori_ubah)).perform(typeText(KATEGORI_JUDUL));
        pressBack();
        onView(withText("TAMBAH")).perform(click());
        Thread.sleep(3000);
    }
}
