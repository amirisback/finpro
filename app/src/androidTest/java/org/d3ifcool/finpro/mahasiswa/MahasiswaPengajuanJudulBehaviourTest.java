package org.d3ifcool.finpro.mahasiswa;

import androidx.test.rule.ActivityTestRule;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.MahasiswaMainActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
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
 * org.d3ifcool.finpro.mahasiswa
 */
public class MahasiswaPengajuanJudulBehaviourTest {

    @Rule
    public ActivityTestRule<MahasiswaMainActivity> mahasiswaActivityTestRule = new ActivityTestRule<>(MahasiswaMainActivity.class);

    @Before
    public void init(){
        mahasiswaActivityTestRule.getActivity();
    }

    @Test
    public void mahasiswaHomeTest() throws InterruptedException {
        Thread.sleep(5000);
        onView(withId(R.id.bottom_menu_mhs_proyekakhir)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.bottom_menu_mhs_judulpa)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.bottom_menu_mhs_informasi)).perform(click());
        Thread.sleep(5000);
    }

}
