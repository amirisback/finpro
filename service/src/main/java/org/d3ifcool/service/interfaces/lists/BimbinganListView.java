package org.d3ifcool.service.interfaces.lists;

import org.d3ifcool.service.models.Bimbingan;
import org.d3ifcool.service.models.Dosen;

import java.util.List;

public interface BimbinganListView {

    void showProgress();

    void hideProgress();

    void onGetListBimbingan(List<Bimbingan> bimbinganList);

    void onFailed(String message);

}
