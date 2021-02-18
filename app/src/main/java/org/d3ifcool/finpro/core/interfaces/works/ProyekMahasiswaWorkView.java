package org.d3ifcool.finpro.core.interfaces.works;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 19/04/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * FrogoBox Software Industries
 */
public interface ProyekMahasiswaWorkView {

    void showProgress();

    void hideProgress();

    void onSucces();

    void onFailed(String message);


}
