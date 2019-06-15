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
 * Copyright (C) 12/01/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public class DosenHomeBehaviourTest {

    @Rule
    public ActivityTestRule<DosenMainActivity> dosenMainActivityTestRule = new ActivityTestRule<>(DosenMainActivity.class);

    @Before
    public void init(){
        dosenMainActivityTestRule.getActivity();
    }

    @Test
    public void DosenHomeTest() throws InterruptedException {
        Thread.sleep(5000);
        onView(withId(R.id.bottom_menu_dsn_pa)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.bottom_menu_dsn_judulpa)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.bottom_menu_dsn_informasi)).perform(click());
        Thread.sleep(5000);
    }


}
