package org.d3ifcool.finpro.core.interfaces.objects;

import org.d3ifcool.finpro.core.models.Judul;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 30/03/2019.
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
public interface JudulView {

    void showProgress();

    void hideProgress();

    void onGetObjectJudul(Judul judul);

    void isEmptyObjectJudul();

    void onFailed(String message);

}
