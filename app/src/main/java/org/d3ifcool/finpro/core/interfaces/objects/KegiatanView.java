package org.d3ifcool.finpro.core.interfaces.objects;

import org.d3ifcool.finpro.core.models.Kegiatan;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 23/06/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * org.d3ifcool.base.interfaces.objects
 */
public interface KegiatanView {

    void showProgress();

    void hideProgress();

    void onGetObjectKegiatan(Kegiatan Kegiatan);

    void isEmptyObjectKegiatan();

    void onFailed(String message);

}
