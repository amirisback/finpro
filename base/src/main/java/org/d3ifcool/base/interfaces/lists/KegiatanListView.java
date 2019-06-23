package org.d3ifcool.base.interfaces.lists;

import org.d3ifcool.base.models.Judul;
import org.d3ifcool.base.models.Kegiatan;

import java.util.List;

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
 * org.d3ifcool.base.interfaces.lists
 */
public interface KegiatanListView {

    void showProgress();

    void hideProgress();

    void onGetListKegiatan(List<Kegiatan> kegiatan);

    void isEmptyListKegiatan();

    void onFailed(String message);

}
