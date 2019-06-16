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
 * Copyright (C) 15/06/2019.
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
public class KoorDosenBehaviourTest {

    @Rule
    public ActivityTestRule<KoorMainActivity> koorMainActivityTestRule = new ActivityTestRule<>(KoorMainActivity.class);

    @Before
    public void init(){
        koorMainActivityTestRule.getActivity();
    }

    @Test
    public void KoorTambahDosen() throws InterruptedException {

        String NIP_DSN_1 = "001";
        String NIP_DSN_2 = "002";
        String NIP_DSN_3 = "003";
        String NIP_DSN_4 = "004";
        String NIP_DSN_5 = "005";
        String NIP_DSN_6 = "006";
        String NIP_DSN_7 = "007";
        String NIP_DSN_8 = "008";

        String NAMA_DSN_1 = "Hariandi Maulid, S.T., M.Sc.";
        String NAMA_DSN_2 = "Hetti Hidayati, S.Kom., M.T.";
        String NAMA_DSN_3 = "Irman Hariman, S.T., M.T.";
        String NAMA_DSN_4 = "Fat'hah Noor Prawita, S.T., M.T.";
        String NAMA_DSN_5 = "Indra Azimi, S.T., M.T.";
        String NAMA_DSN_6 = "Rahmadi Wijaya, S.Si., M.T.";
        String NAMA_DSN_7 = "Rizza Indah Mega Mandasari, S.Kom., M.T.";
        String NAMA_DSN_8 = "Tri Brotoharsono, S.T., M.T.";

        String KODE_DSN_1 = "HMU";
        String KODE_DSN_2 = "HTT";
        String KODE_DSN_3 = "IMM";
        String KODE_DSN_4 = "NPR";
        String KODE_DSN_5 = "IZM";
        String KODE_DSN_6 = "RWJ";
        String KODE_DSN_7 = "RIM";
        String KODE_DSN_8 = "TBH";

        new HelperTest().openDrawerKoor("Dosen");

        createDosen(NIP_DSN_1, NAMA_DSN_1, KODE_DSN_1);
        createDosen(NIP_DSN_2, NAMA_DSN_2, KODE_DSN_2);
        createDosen(NIP_DSN_3, NAMA_DSN_3, KODE_DSN_3);
        createDosen(NIP_DSN_4, NAMA_DSN_4, KODE_DSN_4);
        createDosen(NIP_DSN_5, NAMA_DSN_5, KODE_DSN_5);
        createDosen(NIP_DSN_6, NAMA_DSN_6, KODE_DSN_6);
        createDosen(NIP_DSN_7, NAMA_DSN_7, KODE_DSN_7);
        createDosen(NIP_DSN_8, NAMA_DSN_8, KODE_DSN_8);
    }

    private void createDosen(String NIP_DSN, String NAMA_DSN, String KODE_DSN) throws InterruptedException {
        onView(withId(R.id.frg_koor_dosen_home_fab)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.act_koor__dosen_edittext_nipdosen)).perform(typeText(NIP_DSN));
        pressBack();
        onView(withId(R.id.act_koor_edittext_namadosen)).perform(typeText(NAMA_DSN));
        pressBack();
        onView(withId(R.id.act_koor_edittext_kodedosen)).perform(typeText(KODE_DSN));
        pressBack();
        onView(withId(R.id.act_koor_dosen_button_simpan)).perform(click());
        Thread.sleep(3000);
    }

}
